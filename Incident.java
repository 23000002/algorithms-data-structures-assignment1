package assignment20;

import java.time.LocalDate;

// Model class representing a single security incident record
public class Incident {
    private final String incidentId;
    private final String type;
    private final String description;
    private final String reporter;
    private final LocalDate dateReported;
    private final Severity severity;
    private Status status;

    // Constructor used when adding a new incident (defaults to today's date)
    public Incident(String incidentId, String type, String description, String reporter,
                    Severity severity, Status status) {
        this(incidentId, type, description, reporter, LocalDate.now(), severity, status);
    }

    // Full constructor with explicit date
    public Incident(String incidentId, String type, String description, String reporter,
                    LocalDate dateReported, Severity severity, Status status) {
        if (isBlank(incidentId) || isBlank(type) || isBlank(description) || isBlank(reporter)) {
            throw new IllegalArgumentException("Incident fields cannot be empty.");
        }
        if (dateReported == null || severity == null || status == null) {
            throw new IllegalArgumentException("Date, severity, and status are required.");
        }
        this.incidentId = incidentId.trim();
        this.type = type.trim();
        this.description = description.trim();
        this.reporter = reporter.trim();
        this.dateReported = dateReported;
        this.severity = severity;
        this.status = status;
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public String getIncidentId() {
        return incidentId;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getReporter() {
        return reporter;
    }

    public LocalDate getDateReported() {
        return dateReported;
    }

    public Severity getSeverity() {
        return severity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%-9s %-16s %-10s %-15s %-12s %s",
                incidentId, type, severity, status, dateReported, description);
    }
}
