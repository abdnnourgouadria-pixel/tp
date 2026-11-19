package library;

public class Copy {
    private final String inventoryNumber;
    private final Book book;
    private boolean available = true;

    Copy(String inventoryNumber, Book book) {
        if (inventoryNumber == null || inventoryNumber.isBlank())
            throw new IllegalArgumentException("Inventory number is required");
        if (book == null) throw new IllegalArgumentException("Book is required");
        this.inventoryNumber = inventoryNumber;
        this.book = book;
    }

    String getInventoryNumber() { return inventoryNumber; }
    Book getBook()              { return book; }
    boolean isAvailable()       { return available; }
    void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return inventoryNumber + " - " + book.getTitle() + (available ? " [available]" : " [on loan]");
    }
}
