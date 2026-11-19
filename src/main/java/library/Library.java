package library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Library {
    static final int MAX_LOANS = 5;

    private final Map<String, Book> books = new LinkedHashMap<>();
    private final Map<String, Copy> copies = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();
    private final List<Loan> loans = new ArrayList<>();

    void addBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book is required");
        if (books.containsKey(book.getIsbn()))
            throw new IllegalArgumentException("Book already registered: " + book.getIsbn());
        books.put(book.getIsbn(), book);
    }

    void addCopy(String isbn, String inventoryNumber) {
        Book book = books.get(isbn);
        if (book == null) throw new IllegalArgumentException("Unknown ISBN: " + isbn);
        if (copies.containsKey(inventoryNumber))
            throw new IllegalArgumentException("Copy already registered: " + inventoryNumber);
        copies.put(inventoryNumber, new Copy(inventoryNumber, book));
    }

    void registerMember(Member member) {
        if (member == null) throw new IllegalArgumentException("Member is required");
        if (members.containsKey(member.getNumber()))
            throw new IllegalArgumentException("Member already registered: " + member.getNumber());
        members.put(member.getNumber(), member);
    }

    Book findBook(String isbn)             { return books.get(isbn); }
    Copy findCopy(String inventoryNumber)  { return copies.get(inventoryNumber); }
    Member findMember(String number)       { return members.get(number); }

    /** Nombre de prêts en cours d'un membre. */
    long openLoanCount(String memberNumber) {
        return loans.stream()
                .filter(l -> l.isOpen() && l.getMember().getNumber().equals(memberNumber))
                .count();
    }

    Loan lend(String memberNumber, String inventoryNumber, LocalDate today) {
        Member member = members.get(memberNumber);
        if (member == null) throw new IllegalArgumentException("Unknown member: " + memberNumber);
        Copy copy = copies.get(inventoryNumber);
        if (copy == null) throw new IllegalArgumentException("Unknown copy: " + inventoryNumber);
        if (!copy.isAvailable()) throw new IllegalStateException("Copy not available: " + inventoryNumber);
        if (openLoanCount(memberNumber) >= MAX_LOANS)
            throw new IllegalStateException("Member already has " + MAX_LOANS + " loans in progress");

        Loan loan = new Loan(member, copy, today);
        copy.setAvailable(false);
        loans.add(loan);
        return loan;
    }

    Loan returnCopy(String inventoryNumber, LocalDate today) {
        Copy copy = copies.get(inventoryNumber);
        if (copy == null) throw new IllegalArgumentException("Unknown copy: " + inventoryNumber);
        Loan loan = loans.stream()
                .filter(l -> l.isOpen() && l.getCopy().equals(copy))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Copy is not on loan: " + inventoryNumber));
        loan.close(today);
        copy.setAvailable(true);
        return loan;
    }

    /** Livres dont le titre contient le mot-clé, sans tenir compte de la casse. */
    List<Book> searchByTitle(String keyword) {
        if (keyword == null) throw new IllegalArgumentException("Keyword is required");
        String k = keyword.toLowerCase();
        List<Book> result = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(k)) result.add(b);
        }
        return result;
    }

    List<Loan> getLoans() { return new ArrayList<>(loans); }
}
