import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final long id;
    private final String name;
    private final String firstName;
    private String address;
    private final String zipCode;
    private String city;
    private boolean feesPayed;
    private final List<BookCopy> bookCopies;

    public Customer(final long id, final String name, final String firstName, String address, final String zipCode, String city, boolean feesPayed) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.feesPayed = feesPayed;
        this.bookCopies = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isFeesPayed() {
        return feesPayed;
    }

    public void setFeesPayed(boolean feesPayed) {
        this.feesPayed = feesPayed;
    }

    public List<BookCopy> getBookCopies() {
        return bookCopies;
    }

    @Override
    public String toString() {
        return firstName + " " + name + " (ID: " + id + ")";
    }
}
