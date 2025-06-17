import java.util.Date;

public class BookCopy {
    private final long id;
    private final Book book;
    private String shelfLocation;
    private Date addedToLibrary;
    private boolean lent;
    private Date lentDate;

    public BookCopy(final long id, final Book book, String shelfLocation, Date addedToLibrary, boolean lent, Date lentDate) {
        this.id = id;
        this.book = book;
        this.shelfLocation = shelfLocation;
        this.addedToLibrary = addedToLibrary;
        this.lent = lent;
        this.lentDate = lentDate;
    }

    public long getId() {
        return id;
    }
    public Book getBook() {
        return book;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public void setShelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    public Date getAddedToLibrary() {
        return addedToLibrary;
    }

    public void setAddedToLibrary(Date addedToLibrary) {
        this.addedToLibrary = addedToLibrary;
    }

    public boolean isLent() {
        return lent;
    }

    public void setLent(boolean lent) {
        this.lent = lent;
    }

    @Override
    public String toString() {
        return "Buchkopie " + id + " von \"" + book.getTitle() + "\" (ISBN: " + book.getIsbn() + "), Regal: " + shelfLocation + ", Ausgeliehen: " + lent;
    }

    public void setBorrower(Customer customer) {
    }
}
