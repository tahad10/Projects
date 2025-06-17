import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book {
    private final String isbn;
    private final String title;
    private final List<String> authors;
    private final int year;
    private final String city;
    private final String publisher;
    private final int edition;
    public Map<Long, BookCopy> bookCopies = new HashMap<>();

    public Book(final String isbn, final String title, final List<String> authors, final int year, final String city, final String publisher, final int edition){
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.city = city;
        this.publisher = publisher;
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public int getYear() {
        return year;
    }

    public String getCity() {
        return city;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getEdition() {
        return edition;
    }

    @Override
    public String toString() {
        return title + " von " + String.join(", ", authors) + " (" + year + ")";
    }

    public boolean bookCopyIsLent() {
        return bookCopies.values().stream().anyMatch(BookCopy::isLent);
    }
}
