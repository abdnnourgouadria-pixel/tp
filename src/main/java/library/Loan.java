package library;

import java.time.LocalDate;

// Represents a loan of one copy to one member.
public class Loan {
    private Member member;
    private Copy copy;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(Member member, Copy copy, LocalDate startDate) {
        this.member = member;
        this.copy = copy;
        this.startDate = startDate;
        this.dueDate = startDate.plusDays(14); // Return after 14 days.
        this.returnDate = null; // null means not returned yet.
    }

    public Member getMember() {
        return member;
    }

    public Copy getCopy() {
        return copy;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isOpen() {
        return returnDate == null;
    }

    public boolean isLate(LocalDate today) {
        return isOpen() && today.isAfter(dueDate);
    }

    public void returnBook(LocalDate today) {
        returnDate = today;
    }
}
