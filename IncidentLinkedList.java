package assignment20;

public class IncidentLinkedList {
    private static class Node { Incident incident; Node next; Node(Incident incident) { this.incident = incident; } }
    private Node head;
    private int size;

    public void addLast(Incident incident) {
        if (incident == null) throw new IllegalArgumentException("Incident cannot be null.");
        Node node = new Node(incident);
        if (head == null) head = node;
        else { Node current = head; while (current.next != null) current = current.next; current.next = node; }
        size++;
    }

    public Incident get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index);
        Node current = head; for (int i = 0; i < index; i++) current = current.next; return current.incident;
    }

    public Incident linearSearchById(String id) {
        Node current = head;
        while (current != null) { if (current.incident.getIncidentId().equalsIgnoreCase(id)) return current.incident; current = current.next; }
        return null;
    }

    public boolean removeById(String id) {
        Node previous = null, current = head;
        while (current != null) {
            if (current.incident.getIncidentId().equalsIgnoreCase(id)) {
                if (previous == null) head = current.next; else previous.next = current.next;
                size--; return true;
            }
            previous = current; current = current.next;
        }
        return false;
    }

    public int size() { return size; }

    public IncidentLinkedList sortedBySeverity() {
        head = mergeSort(head);
        return this;
    }

    private Node mergeSort(Node node) {
        if (node == null || node.next == null) return node;
        Node slow = node, fast = node.next;
        while (fast != null && fast.next != null) { slow = slow.next; fast = fast.next.next; }
        Node right = slow.next;
        slow.next = null;
        return merge(mergeSort(node), mergeSort(right));
    }

    private Node merge(Node left, Node right) {
        Node dummy = new Node(null);
        Node tail = dummy;
        while (left != null && right != null) {
            if (left.incident.getSeverity().getRank() >= right.incident.getSeverity().getRank()) {
                tail.next = left; left = left.next;
            } else {
                tail.next = right; right = right.next;
            }
            tail = tail.next;
        }
        tail.next = left != null ? left : right;
        return dummy.next;
    }
}
