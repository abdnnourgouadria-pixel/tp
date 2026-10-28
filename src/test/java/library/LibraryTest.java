package library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// Tests the main rules of the library.
class LibraryTest {
    private static final String ISBN = "978-0-00-000001-1";
    private static final LocalDate DAY = LocalDate.of(2026, 10, 1);
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
        library.addBook(new Book(ISBN, "Software Engineering", 2016));
        library.addCopy(ISBN, "C-001");
        library.registerMember(new Member("M-01", "First member"));
    }

    @Test
    void lendingMakesCopyUnavailable() {
        Loan loan = library.lend("M-01", "C-001", DAY);

        assertFalse(library.findCopy("C-001").isAvailable());
        assertEquals(DAY.plusDays(14), loan.getDueDate());
    }

    @Test
    void returningMakesCopyAvailable() {
        library.lend("M-01", "C-001", DAY);
        library.returnCopy("C-001", DAY.plusDays(5));

        assertTrue(library.findCopy("C-001").isAvailable());
    }

    @Test
    void cannotLendUnavailableCopy() {
        library.lend("M-01", "C-001", DAY);

        assertThrows(IllegalStateException.class,
                () -> library.lend("M-01", "C-001", DAY));
    }

    @Test
    void unknownMemberCausesException() {
        assertThrows(IllegalArgumentException.class,
                () -> library.lend("UNKNOWN", "C-001", DAY));
    }

    @Test
    void loanIsLateAfterDueDate() {
        Loan loan = library.lend("M-01", "C-001", DAY);

        assertFalse(loan.isLate(DAY.plusDays(14)));
        assertTrue(loan.isLate(DAY.plusDays(15)));
    }

    @Test
    void memberCannotHaveMoreThanFiveLoans() {
        for (int i = 1; i <= 6; i++) {
            String isbn = "ISBN-" + i;
            library.addBook(new Book(isbn, "Book " + i, 2020));
            library.addCopy(isbn, "C-" + i);
        }

        for (int i = 1; i <= 5; i++) {
            library.lend("M-01", "C-" + i, DAY);
        }

        assertThrows(IllegalStateException.class,
                () -> library.lend("M-01", "C-6", DAY));
    }

    @Test
    void searchIgnoresUpperAndLowerCase() {
        assertEquals(1, library.searchByTitle("software").size());
        assertEquals(1, library.searchByTitle("SOFTWARE").size());
    }

    @Test
    void booksWithSameIsbnAreEqual() {
        Book first = new Book(ISBN, "First title", 2000);
        Book second = new Book(ISBN, "Another title", 2020);

        assertEquals(first, second);
    }
}
