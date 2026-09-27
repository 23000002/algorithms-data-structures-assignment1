package assignment20;

import java.util.Scanner;

// Main driver class providing a console menu interface for the incident management system
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final IncidentManager manager = new IncidentManager();

    public static void main(String[] args) {
        // Load initial sample data
        loadSampleIncidents();

        boolean running = true;
        System.out.println("=================================================");
        System.out.println("   SECURITY INCIDENT MANAGEMENT SYSTEM - COM 2224 ");
        System.out.println("=================================================");

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add new incident");
            System.out.println("2. View all incidents");
            System.out.println("3. Search incident by ID (Linear Search)");
            System.out.println("4. Sort incidents by severity (Merge Sort)");
            System.out.println("5. Process incident (Mark as Resolved)");
            System.out.println("6. Remove incident");
            System.out.println("7. Exit");
            System.out.print("Enter choice (1-7): ");

            try {
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1" -> addIncident();
                    case "2" -> manager.displayAll();
                    case "3" -> searchIncident();
                    case "4" -> {
                        manager.sortBySeverity();
                        System.out.println("Incidents sorted by severity (highest priority first).");
                    }
                    case "5" -> processIncident();
                    case "6" -> removeIncident();
                    case "7" -> {
                        running = false;
                        System.out.println("Exiting application.");
                    }
                    default -> System.out.println("Invalid option. Please enter a number between 1 and 7.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    // Handles user input for creating a new incident
    private static void addIncident() {
        System.out.println("\n-- Add New Incident --");
        String id = readRequired("Incident ID (e.g., INC-004): ");
        String type = readRequired("Threat Type (e.g., Phishing, Malware): ");
        String description = readRequired("Description: ");
        String reporter = readRequired("Reporter Name: ");
        Severity severity = Severity.fromText(readRequired("Severity (LOW, MEDIUM, HIGH, CRITICAL): "));

        manager.addIncident(new Incident(id, type, description, reporter, severity, Status.OPEN));
        System.out.println("Incident added successfully.");
    }

    // Searches for an incident using Linear Search
    private static void searchIncident() {
        System.out.println("\n-- Search Incident --");
        String id = readRequired("Enter Incident ID to search: ");
        Incident incident = manager.searchById(id);

        if (incident != null) {
            System.out.println("\nIncident Found:");
            System.out.printf("%-9s %-16s %-10s %-15s %-12s %s%n",
                    "ID", "TYPE", "SEVERITY", "STATUS", "DATE", "DESCRIPTION");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println(incident);
        } else {
            System.out.println("Incident not found with ID: " + id);
        }
    }

    // Resolves an incident by ID
    private static void processIncident() {
        System.out.println("\n-- Process Incident --");
        String id = readRequired("Enter Incident ID: ");
        if (manager.processIncident(id)) {
            System.out.println("Incident marked as RESOLVED.");
        } else {
            System.out.println("Incident not found.");
        }
    }

    // Removes an incident by ID
    private static void removeIncident() {
        System.out.println("\n-- Remove Incident --");
        String id = readRequired("Enter Incident ID: ");
        if (manager.removeIncident(id)) {
            System.out.println("Incident removed successfully.");
        } else {
            System.out.println("Incident not found.");
        }
    }

    // Helper method to ensure inputs are not blank
    private static String readRequired(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Field cannot be empty.");
        }
        return input;
    }

    // Initial sample incidents for demonstration
    private static void loadSampleIncidents() {
        manager.addIncident(new Incident("INC-001", "Phishing", "Suspicious login link sent via email", "Mulalo", Severity.HIGH, Status.OPEN));
        manager.addIncident(new Incident("INC-002", "Malware", "Malware detected on reception PC", "Mpho", Severity.CRITICAL, Status.INVESTIGATING));
        manager.addIncident(new Incident("INC-003", "Unauthorised Access", "Multiple failed login attempts detected", "Mashudu", Severity.MEDIUM, Status.OPEN));
        manager.addIncident(new Incident("INC-004", "Data Leak", "Confidential payroll sheet shared on public drive", "Ndivhuwo", Severity.HIGH, Status.INVESTIGATING));
        manager.addIncident(new Incident("INC-005", "DDoS Attack", "High traffic spike causing portal latency", "Dakalo", Severity.CRITICAL, Status.OPEN));
        manager.addIncident(new Incident("INC-006", "Policy Violation", "Unauthorized USB storage device plugged into lab PC", "Rabelani", Severity.LOW, Status.RESOLVED));
        manager.addIncident(new Incident("INC-007", "Ransomware", "Encrypted file extensions detected on shared server", "Gundo", Severity.CRITICAL, Status.INVESTIGATING));
        manager.addIncident(new Incident("INC-008", "Social Engineering", "Phone call requesting employee credentials", "Tshilidzi", Severity.MEDIUM, Status.OPEN));
        manager.addIncident(new Incident("INC-009", "Software Bug", "Expired SSL certificate on staging domain", "Takalani", Severity.LOW, Status.RESOLVED));
    }
}
