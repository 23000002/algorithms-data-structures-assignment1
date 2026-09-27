package assignment20;

public enum Severity {
    LOW(1), MEDIUM(2), HIGH(3), CRITICAL(4);

    private final int rank;
    Severity(int rank) { this.rank = rank; }
    public int getRank() { return rank; }
    public static Severity fromText(String value) { return valueOf(value.trim().toUpperCase()); }
}
