import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        Library library = new Library();

        System.out.println("Bitte geben Sie Ihre Kunden-ID ein:");
        long customerId = scanner.nextLong();
        scanner.nextLine();

        boolean running = true;

        while (running) {
            System.out.println("\n=== Hauptmenü ===");
            System.out.println("1: Ausleihen");
            System.out.println("2: Rückgabe");
            System.out.println("3: Importieren");
            System.out.println("4: Löschen");
            System.out.println("5: Suche");
            System.out.println("6: Bücherregister");
            System.out.println("0: Beenden");

            System.out.print("Auswahl: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Puffer leeren

            switch (choice) {
                case 1:
                    System.out.println("→ Ausleihen (noch nicht implementiert)");
                    break;

                case 2:
                    System.out.println("→ Rückgabe (noch nicht implementiert)");
                    break;

                case 3:
                    System.out.println("→ Import (noch nicht implementiert)");
                    break;

                case 4:
                    System.out.println("Was möchten Sie löschen?");
                    System.out.println("1: Buch (ISBN)");
                    System.out.println("2: Buchkopie (ID)");
                    System.out.println("3: Kunde (ID)");
                    int deleteChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (deleteChoice == 1) {
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();
                        boolean deleted = library.deleteBookByIsbn(isbn);
                        System.out.println(deleted ? "Buch gelöscht." : "Löschen nicht möglich.");
                    } else if (deleteChoice == 2) {
                        System.out.print("Buchkopie-ID: ");
                        long id = scanner.nextLong();
                        boolean deleted = library.deleteBookCopyById(id);
                        System.out.println(deleted ? "Kopie gelöscht." : "Löschen nicht möglich.");
                    } else if (deleteChoice == 3) {
                        System.out.print("Kunden-ID: ");
                        long id = scanner.nextLong();
                        boolean deleted = library.deleteCustomerById(id);
                        System.out.println(deleted ? "Kunde gelöscht." : "Löschen nicht möglich.");
                    }
                    break;

                case 5:
                    System.out.println("→ Suche (noch nicht implementiert)");
                    break;

                case 6:
                    library.printBooks();
                    library.printBookCopies();
                    library.printCustomers();
                    break;

                case 0:
                    running = false;
                    System.out.println("Programm beendet.");
                    break;

                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }

        scanner.close();
    }
}

