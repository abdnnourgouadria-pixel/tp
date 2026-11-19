package library;

import java.time.LocalDate;

public class Loan {
    static final int LOAN_DAYS = 14;

    private final Member member;
    private final Copy copy;
    private final LocalDate startDate;
    private final LocalDate dueDate;
    private LocalDate returnDate; // null tant que le prêt est en cours

    Loan(Member member, Copy copy, LocalDate startDate) {
        if (member == null || copy == null || startDate == null)
            throw new IllegalArgumentException("Member, copy and start date are required");
        this.member = member;
        this.copy = copy;
        this.startDate = startDate;
        this.dueDate = startDate.plusDays(LOAN_DAYS);
    }

    Member getMember()        { return member; }
    Copy getCopy()            { return copy; }
    LocalDate getStartDate()  { return startDate; }
    LocalDate getDueDate()    { return dueDate; }
    LocalDate getReturnDate() { return returnDate; }

    boolean isOpen() { return returnDate == null; }

    /** En retard : prêt en cours et date du jour strictement après l'échéance. */
    boolean isLate(LocalDate today) { return isOpen() && today.isAfter(dueDate); }

    void close(LocalDate date) {
        if (!isOpen()) throw new IllegalStateException("Loan already closed");
        if (date.isBefore(startDate)) throw new IllegalArgumentException("Return date before start date");
        this.returnDate = date;
    }

    @Override
    public String toString() {
        return member.getName() + " <- " + copy.getInventoryNumber()
                + " (from " + startDate + ", due " + dueDate
                + (isOpen() ? ", open)" : ", returned " + returnDate + ")");
    }
}
