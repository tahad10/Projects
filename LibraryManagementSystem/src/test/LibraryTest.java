import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library library;

    @BeforeEach
    public void setup() {
        library = new Library();
    }

    @Test
    public void testDeleteExistingBookWithoutLentCopies() {
        boolean deleted = library.deleteBookByIsbn("123-A");
        assertTrue(deleted, "Buch sollte gelöscht werden können.");
    }

    @Test
    public void testDeleteBookThatDoesNotExist() {
        boolean deleted = library.deleteBookByIsbn("999-X");
        assertFalse(deleted, "Buch sollte nicht gefunden werden.");
    }

    @Test
    public void testDeleteBookWithLentCopyFails() {
        for (BookCopy copy : library.getBookCopies()) {
            if (copy.getBook().getIsbn().equals("123-A")) {
                copy.setLent(true);
                break;
            }
        }

        boolean deleted = library.deleteBookByIsbn("123-A");
        assertFalse(deleted, "Löschen muss scheitern, wenn Kopie ausgeliehen ist.");
    }

    @Test
    public void testImportBooksFromCsv() {
        Library library = new Library();
        library.importBooksFromCsv("src/testdata/books.csv");

        // Suche gezielt das Buch mit ISBN 123-A
        Book target = library.getBooks().stream()
                .filter(book -> book.getIsbn().equals("123-A"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Buch mit ISBN 123-A nicht gefunden."));

        assertEquals("Effi Briest", target.getTitle());
        assertEquals(List.of("Theodor Fontane"), target.getAuthors());
    }
}
