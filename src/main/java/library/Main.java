package library;

import java.time.LocalDate;

// zdt main mn 3ndi ostada
public class Main {
    public static void main(String[] args) {
        LocalDate day = LocalDate.of(2026, 10, 1);
        Library library = new Library();

        library.addBook(new Book("978-0-00-000001-1", "Software Engineering", 2016));
        library.addBook(new Book("978-0-00-000002-2", "Clean Code", 2008));
        library.addCopy("978-0-00-000001-1", "C-001");
        library.addCopy("978-0-00-000002-2", "C-002");
        library.registerMember(new Member("M-01", "First member"));

        System.out.println("Search \"software\": " + library.searchByTitle("software"));

        Loan loan = library.lend("M-01", "C-001", day);
        System.out.println("Loan created : " + loan);
        System.out.println("Copy         : " + library.findCopy("C-001"));
        System.out.println("Late on " + day.plusDays(15) + " ? " + loan.isLate(day.plusDays(15)));

        try {
            library.lend("M-01", "C-001", day);
        } catch (IllegalStateException e) {
            System.out.println("Refused      : " + e.getMessage());
        }

        library.returnCopy("C-001", day.plusDays(10));
        System.out.println("After return : " + loan);
        System.out.println("Copy         : " + library.findCopy("C-001"));
    }
}
