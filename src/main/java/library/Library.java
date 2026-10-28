package library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Main class: stores books, copies, members, and loans.
public class Library {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Copy> copies = new HashMap<>();
    private Map<String, Member> members = new HashMap<>();
    private List<Loan> loans = new ArrayList<>();

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public void addCopy(String isbn, String inventoryNumber) {
        if (!books.containsKey(isbn)) {
            throw new IllegalArgumentException("Unknown book");
        }

        if (copies.containsKey(inventoryNumber)) {
            throw new IllegalArgumentException("Copy already exists");
        }

        Copy copy = new Copy(inventoryNumber, books.get(isbn));
        copies.put(inventoryNumber, copy);
    }

    public void registerMember(Member member) {
        members.put(member.getNumber(), member);
    }

    public Copy findCopy(String inventoryNumber) {
        return copies.get(inventoryNumber);
    }

    public Loan lend(String memberNumber, String inventoryNumber,
                     LocalDate today) {
        Member member = members.get(memberNumber);
        Copy copy = copies.get(inventoryNumber);

        if (member == null || copy == null) {
            throw new IllegalArgumentException("Unknown member or copy");
        }

        if (!copy.isAvailable()) {
            throw new IllegalStateException("Copy is not available");
        }

        int numberOfOpenLoans = 0;

        for (Loan loan : loans) {
            if (loan.isOpen()
                    && loan.getMember().getNumber().equals(memberNumber)) {
                numberOfOpenLoans++;
            }
        }

        if (numberOfOpenLoans >= 5) {
            throw new IllegalStateException("Member already has 5 open loans");
        }

        Loan loan = new Loan(member, copy, today);
        loans.add(loan);
        copy.setAvailable(false);

        return loan;
    }

    public void returnCopy(String inventoryNumber, LocalDate today) {
        Copy copy = copies.get(inventoryNumber);

        if (copy == null) {
            throw new IllegalArgumentException("Unknown copy");
        }

        for (Loan loan : loans) {
            if (loan.getCopy() == copy && loan.isOpen()) {
                loan.returnBook(today);
                copy.setAvailable(true);
                return;
            }
        }

        throw new IllegalStateException("Copy is not on loan");
    }

    // Finds books whose titles contain the keyword (case-insensitive).
    public List<Book> searchByTitle(String keyword) {
        List<Book> result = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase()
                    .contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }
}
