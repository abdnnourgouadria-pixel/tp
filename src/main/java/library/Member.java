package library;

public class Member {
    private final String number;
    private final String name;

    Member(String number, String name) {
        if (number == null || number.isBlank()) throw new IllegalArgumentException("Member number is required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Member name is required");
        this.number = number;
        this.name = name;
    }

    String getNumber() { return number; }
    String getName()   { return name; }

    @Override
    public String toString() { return number + " - " + name; }
}
