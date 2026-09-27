package assignment20;

// Represents the urgency level of a security incident
public enum Severity {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    CRITICAL(4);

    // Numeric rank used for sorting (higher rank = higher priority)
    private final int rank;

    Severity(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    // Converts text input from user into a Severity enum
    public static Severity fromText(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Severity cannot be empty.");
        }
        return Severity.valueOf(value.trim().toUpperCase());
    }
}
