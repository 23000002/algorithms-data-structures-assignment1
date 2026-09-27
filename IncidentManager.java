package assignment20;

public class IncidentManager {
    private IncidentLinkedList incidents = new IncidentLinkedList();
    public void addIncident(Incident incident) { if (searchById(incident.getIncidentId()) != null) throw new IllegalArgumentException("An incident with that ID already exists."); incidents.addLast(incident); }
    public Incident searchById(String id) { return incidents.linearSearchById(id); }
    public boolean removeIncident(String id) { return incidents.removeById(id); }
    public boolean processIncident(String id) { Incident incident = searchById(id); if (incident == null) return false; incident.setStatus(Status.RESOLVED); return true; }
    public void sortBySeverity() { incidents = incidents.sortedBySeverity(); }
    public Incident get(int index) { return incidents.get(index); }
    public int size() { return incidents.size(); }
    public void displayAll() {
        if (size() == 0) { System.out.println("No incidents found."); return; }
        System.out.printf("%-9s %-14s %-9s %-15s %-12s %s%n", "ID", "TYPE", "SEVERITY", "STATUS", "DATE", "DESCRIPTION");
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < size(); i++) System.out.println(get(i));
    }
}
