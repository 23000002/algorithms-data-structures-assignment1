package assignment20;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final IncidentManager manager = new IncidentManager();

    public static void main(String[] args) {
        loadSampleIncidents();
        boolean running = true;
        System.out.println("SECURITY INCIDENT MANAGEMENT SYSTEM");
        while (running) {
            System.out.println(
                    "\n1. Add incident\n2. View incidents\n3. Search by incident ID\n4. Sort by severity\n5. Process incident\n6. Remove incident\n7. Exit");
            System.out.print("Choose an option: ");
            try {
                switch (scanner.nextLine().trim()) {
                    case "1" -> addIncident();
                    case "2" -> manager.displayAll();
                    case "3" -> searchIncident();
                    case "4" -> {
                        manager.sortBySeverity();
                        System.out.println("Incidents sorted by severity.");
                    }
                    case "5" -> processIncident();
                    case "6" -> removeIncident();
                    case "7" -> running = false;
                    default -> System.out.println("Please choose an option from 1 to 7.");
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Input error: " + exception.getMessage());
            }
        }
        System.out.println("Goodbye.");
    }

    private static void addIncident() {
        String id = required("Incident ID: ");
        String type = required("Incident type: ");
        String description = required("Description: ");
        String reporter = required("Reporter: ");
        Severity severity = Severity.fromText(required("Severity (LOW/MEDIUM/HIGH/CRITICAL): "));
        manager.addIncident(new Incident(id, type, description, reporter, severity, Status.OPEN));
        System.out.println("Incident added.");
    }

    private static void searchIncident() {
        Incident i = manager.searchById(required("Incident ID: "));
        System.out.println(i == null ? "Incident not found." : i);
    }

    private static void processIncident() {
        System.out.println(manager.processIncident(required("Incident ID: ")) ? "Incident marked as resolved."
                : "Incident not found.");
    }

    private static void removeIncident() {
        System.out.println(
                manager.removeIncident(required("Incident ID: ")) ? "Incident removed." : "Incident not found.");
    }

    private static String required(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        if (value.isEmpty())
            throw new IllegalArgumentException("This field is required.");
        return value;
    }

    private static void loadSampleIncidents() {
        manager.addIncident(
                new Incident("INC-001", "Phishing", "Suspicious email reported", "Mulalo", Severity.HIGH, Status.OPEN));
        manager.addIncident(new Incident("INC-002", "Malware", "Malware detected on workstation", "Mpho",
                Severity.CRITICAL, Status.INVESTIGATING));
        manager.addIncident(new Incident("INC-003", "Unauthorised Access", "Repeated login failures", "Mashudu",
                Severity.MEDIUM, Status.OPEN));
    }
}
