package assignment20;

// Custom Singly Linked List implemented from scratch to store incident records
public class IncidentLinkedList {

    // Node representing each element in the linked list
    private static class Node {
        Incident incident;
        Node next;

        Node(Incident incident) {
            this.incident = incident;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public IncidentLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Appends a new incident to the end of the list (Time Complexity: O(n))
    public void addLast(Incident incident) {
        if (incident == null) {
            throw new IllegalArgumentException("Incident cannot be null.");
        }
        Node newNode = new Node(incident);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Retrieves an incident at a given index by traversing the nodes
    public Incident get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.incident;
    }

    // Linear search to find an incident by its ID (Time Complexity: Best O(1), Worst O(n))
    public Incident linearSearchById(String id) {
        if (id == null) {
            return null;
        }
        Node current = head;
        while (current != null) {
            if (current.incident.getIncidentId().equalsIgnoreCase(id.trim())) {
                return current.incident; // Found
            }
            current = current.next;
        }
        return null; // Not found
    }

    // Removes an incident by ID by updating the node pointers (Time Complexity: O(n))
    public boolean removeById(String id) {
        if (id == null || head == null) {
            return false;
        }
        Node previous = null;
        Node current = head;

        while (current != null) {
            if (current.incident.getIncidentId().equalsIgnoreCase(id.trim())) {
                if (previous == null) {
                    // Removing the first (head) node
                    head = current.next;
                } else {
                    // Unlink the current node by skipping it
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    // Sorts the linked list by severity in descending order using Merge Sort (Time Complexity: O(n log n))
    public IncidentLinkedList sortedBySeverity() {
        head = mergeSort(head);
        return this;
    }

    // Recursive Merge Sort for linked list
    private Node mergeSort(Node node) {
        // Base case: if list is empty or has only 1 node, it is already sorted
        if (node == null || node.next == null) {
            return node;
        }

        // Split the list into two halves using slow and fast pointers
        Node slow = node;
        Node fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node right = slow.next;
        slow.next = null; // Break the link to separate into two sublists

        // Recursively sort both halves
        Node leftSorted = mergeSort(node);
        Node rightSorted = mergeSort(right);

        // Merge the two sorted halves
        return merge(leftSorted, rightSorted);
    }

    // Merges two sorted sublists by comparing severity ranks
    private Node merge(Node left, Node right) {
        Node dummy = new Node(null);
        Node current = dummy;

        while (left != null && right != null) {
            // Higher rank comes first (descending order)
            if (left.incident.getSeverity().getRank() >= right.incident.getSeverity().getRank()) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        // Attach any remaining nodes
        current.next = (left != null) ? left : right;

        return dummy.next;
    }
}
