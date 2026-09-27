package assignment20;

import java.time.LocalDate;

public class Incident {
    private final String incidentId;
    private final String type;
    private final String description;
    private final String reporter;
    private final LocalDate dateReported;
    private final Severity severity;
    private Status status;

    public Incident(String incidentId, String type, String description, String reporter,
            Severity severity, Status status) {
        this(incidentId, type, description, reporter, LocalDate.now(), severity, status);
    }

    public Incident(String incidentId, String type, String description, String reporter,
            LocalDate dateReported, Severity severity, Status status) {
        if (blank(incidentId) || blank(type) || blank(description) || blank(reporter)) {
            throw new IllegalArgumentException("Incident details cannot be blank.");
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

    private static boolean blank(String value) {
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
        if (status == null)
            throw new IllegalArgumentException("Status cannot be null.");
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%-9s %-14s %-9s %-15s %-12s %s", incidentId, type, severity, status, dateReported,
                description);
    }
}
