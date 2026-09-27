package assignment20;

// Manages operations on the incident list (adding, searching, deleting, sorting, and display)
public class IncidentManager {

    private IncidentLinkedList incidents = new IncidentLinkedList();

    // Adds a new incident after checking that the ID does not already exist
    public void addIncident(Incident incident) {
        if (incident == null) {
            throw new IllegalArgumentException("Incident cannot be null.");
        }
        if (searchById(incident.getIncidentId()) != null) {
            throw new IllegalArgumentException("An incident with ID '" + incident.getIncidentId() + "' already exists.");
        }
        incidents.addLast(incident);
    }

    // Searches for an incident by ID using Linear Search
    public Incident searchById(String id) {
        return incidents.linearSearchById(id);
    }

    // Removes an incident by ID
    public boolean removeIncident(String id) {
        return incidents.removeById(id);
    }

    // Marks an incident status as RESOLVED
    public boolean processIncident(String id) {
        Incident incident = searchById(id);
        if (incident == null) {
            return false;
        }
        incident.setStatus(Status.RESOLVED);
        return true;
    }

    // Sorts all incidents from highest to lowest severity using Merge Sort
    public void sortBySeverity() {
        incidents = incidents.sortedBySeverity();
    }

    public Incident get(int index) {
        return incidents.get(index);
    }

    public int size() {
        return incidents.size();
    }

    // Prints all stored incidents in a formatted table
    public void displayAll() {
        if (size() == 0) {
            System.out.println("No incidents recorded in the system.");
            return;
        }
        System.out.printf("%-9s %-16s %-10s %-15s %-12s %s%n",
                "ID", "TYPE", "SEVERITY", "STATUS", "DATE", "DESCRIPTION");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < size(); i++) {
            System.out.println(get(i));
        }
    }
}
