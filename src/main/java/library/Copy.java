package library;

// Represents one physical copy of a book.
public class Copy {
    private String inventoryNumber;
    private Book book;
    private boolean available;

    public Copy(String inventoryNumber, Book book) {
        this.inventoryNumber = inventoryNumber;
        this.book = book;
        this.available = true; // A new copy is available.
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public Book getBook() {
        return book;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
