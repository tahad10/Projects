import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<BookCopy> bookCopies = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public Library() {
        addSampleData();
    }

    public boolean deleteBook(final String isbn) {
        Book book = books.get(Integer.parseInt(isbn));
        if (book == null) {
            throw new IllegalArgumentException("The book has no valid ISBN.");
        }
        if (book.bookCopyIsLent()) {
            throw new IllegalArgumentException("Book has currently been lent.");
        }
        books.remove(isbn);
        book.bookCopies.values().forEach(bookCopy -> bookCopies.remove(bookCopy.getId()));
        return false;
    }

    public void deleteBookCopy(final long id) {
        BookCopy bookCopy = bookCopies.get((int) id);
        if (bookCopy == null) {
            throw new IllegalArgumentException("Book has no valid ID.");
        }
        if (bookCopy.isLent()) {
            throw new IllegalArgumentException("Bookcopy has been lent.");
        }
        bookCopies.remove(id);
        bookCopy.getBook().bookCopies.remove(bookCopy.getId());
    }

    public void deleteCustomer(long id) {
        Customer customer = customers.get((int) id);
        if (customer == null) {
            throw new IllegalArgumentException("Customer with the given ID doesnt exist.");
        }
        if (!customer.getBookCopies().isEmpty()) {
            throw new IllegalArgumentException("Customer has currently a book copy.");
        }
        customers.remove(id);
    }

    public boolean deleteBookByIsbn(String isbn) {
        for (BookCopy copy : bookCopies) {
            if (copy.getBook().getIsbn().equals(isbn) && copy.isLent()) {
                System.out.println("Buch ist ausgeliehen und kann nicht gelöscht werden.");
                return false;
            }
        }

        bookCopies.removeIf(copy -> copy.getBook().getIsbn().equals(isbn));

        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                books.remove(book);
                return true;
            }
        }

        System.out.println("Buch mit der ISBN " + isbn + " wurde nicht gefunden.");
        return false;
    }


    public boolean deleteBookCopyById(long id) {
        for (BookCopy copy : bookCopies) {
            if (copy.getId() == id) {
                if (copy.isLent()) {
                    System.out.println("Buchkopie ist ausgeliehen und kann nicht gelöscht werden.");
                    return false;
                }
                return bookCopies.remove(copy);
            }
        }
        return false;
    }

    public boolean deleteCustomerById(long id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                if (!customer.getBookCopies().isEmpty()) {
                    System.out.println("Kunde hat noch ausgeliehene Bücher.");
                    return false;
                }
                return customers.remove(customer);
            }
        }
        return false;
    }

    public void printBooks() {
        System.out.println("--- Bücher ---");
        books.forEach(System.out::println);
    }

    public void printBookCopies() {
        System.out.println("--- Buchkopien ---");
        bookCopies.forEach(System.out::println);
    }

    public void printCustomers() {
        System.out.println("--- Kunden ---");
        customers.forEach(System.out::println);
    }

    private void addSampleData() {
        Book b1 = new Book("123-A", "Effi Briest", List.of("Theodor Fontane"), 1896, "Leipzig", "Reclam", 1);
        Book b2 = new Book("456-B", "Der Hobbit", List.of("J.R.R. Tolkien"), 1937, "Berlin", "Klett", 2);
        books.add(b1);
        books.add(b2);

        BookCopy bc1 = new BookCopy(1L, b1, "A1", new java.util.Date(), false, null);
        BookCopy bc2 = new BookCopy(2L, b2, "A2", new java.util.Date(), false, null);
        bookCopies.add(bc1);
        bookCopies.add(bc2);

        Customer c1 = new Customer(101L, "Müller", "Anna", "Musterweg 1", "12345", "Musterstadt", true);
        Customer c2 = new Customer(102L, "Schmidt", "Paul", "Beispielstraße 2", "54321", "Beispielstadt", true);
        customers.add(c1);
        customers.add(c2);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void importBooksFromCsv(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 7) {
                    System.out.println("Ungültige Zeile übersprungen (zu wenig Felder): " + Arrays.toString(parts));
                    continue;
                }

                String isbn = parts[0];
                String title = parts[1];
                List<String> authors = Arrays.asList(parts[2].split(" und "));
                int year = Integer.parseInt(parts[3]);
                String city = parts[4];
                String publisher = parts[5];
                int edition = Integer.parseInt(parts[6]);

                Book book = new Book(isbn, title, authors, year, city, publisher, edition);
                books.add(book);
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen der Datei: " + e.getMessage());
        }
    }

    public void importBookCopiesFromCsv(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 5) {
                    System.out.println("Ungültige Zeile übersprungen (zu wenig Felder): " + Arrays.toString(parts));
                    continue;
                }

                long copyId = Long.parseLong(parts[0]);
                String isbn = parts[1];
                String shelfLocation = parts[2];
                Date purchaseDate = java.sql.Date.valueOf(parts[3]);
                boolean lent = Boolean.parseBoolean(parts[4]);

                // Buch anhand ISBN finden
                Book book = books.stream()
                        .filter(b -> b.getIsbn().equals(isbn))
                        .findFirst()
                        .orElse(null);

                if (book == null) {
                    System.out.println("Kein Buch mit ISBN " + isbn + " gefunden, Kopie übersprungen.");
                    continue;
                }

                BookCopy copy = new BookCopy(copyId, book, shelfLocation, purchaseDate, lent, null);
                bookCopies.add(copy);
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen der Datei: " + e.getMessage());
        }
    }

    public void importCustomersFromCsv(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 7) {
                    System.out.println("Ungültige Zeile übersprungen (zu wenig Felder): " + Arrays.toString(parts));
                    continue;
                }

                long id = Long.parseLong(parts[0]);
                String name = parts[1];
                String firstName = parts[2];
                String address = parts[3];
                String zipCode = parts[4];
                String city = parts[5];
                boolean feesPayed = Boolean.parseBoolean(parts[6]);

                Customer customer = new Customer(id, name, firstName, address, zipCode, city, feesPayed);
                customers.add(customer);
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen der Datei: " + e.getMessage());
        }
    }

    public List<BookCopy> searchBookCopies(String keyword) {
        String lowerKeyword = keyword.toLowerCase();

        return bookCopies.stream()
                .filter(copy -> {
                    Book book = copy.getBook();
                    return book.getIsbn().toLowerCase().contains(lowerKeyword)
                            || book.getTitle().toLowerCase().contains(lowerKeyword)
                            || book.getAuthors().stream()
                            .anyMatch(author -> author.toLowerCase().contains(lowerKeyword));
                })
                .collect(Collectors.toList());
    }

    public boolean lendBookCopy(long customerId, String isbn) {
        Customer customer = customers.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst()
                .orElse(null);

        if (customer == null) {
            System.out.println("Kunde nicht gefunden.");
            return false;
        }

        Optional<BookCopy> availableCopy = bookCopies.stream()
                .filter(copy -> !copy.isLent() && copy.getBook().getIsbn().equals(isbn))
                .findFirst();

        if (availableCopy.isEmpty()) {
            System.out.println("Keine verfügbare Kopie des Buches gefunden.");
            return false;
        }

        BookCopy copy = availableCopy.get();
        copy.setLent(true);
        copy.setBorrower(customer);
        return true;
    }

    public boolean returnBookCopy(long copyId) {
        BookCopy copy = bookCopies.stream()
                .filter(c -> c.getId() == copyId)
                .findFirst()
                .orElse(null);

        if (copy == null) {
            System.out.println("Buchkopie nicht gefunden.");
            return false;
        }

        if (!copy.isLent()) {
            System.out.println("Diese Kopie ist nicht ausgeliehen.");
            return false;
        }

        copy.setLent(false);
        copy.setBorrower(null);
        return true;
    }
}
