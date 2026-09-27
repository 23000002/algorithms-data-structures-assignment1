# COM 2224 Algorithms & Data Structures — Assignment 1

**Module:** COM 2224 Algorithms & Data Structures  
**Institution:** University of Venda  
**Date:** 25 September 2026  
**Project Topic:** Cybersecurity Incident Management System  

### Group Members:
1. [Student Name 1] - [Student Number 1]
2. [Student Name 2] - [Student Number 2]
3. [Student Name 3] - [Student Number 3]
4. [Student Name 4] - [Student Number 4]
5. [Student Name 5] - [Student Number 5]

---

## 1. Project Overview & Problem Description

Organizations face different types of cyber threats every day, such as phishing attacks, unauthorized login attempts, and malware infections. A Security Operations Center (SOC) needs a system to record these incidents as they occur, look them up by ID, prioritize them by severity so urgent issues are handled first, and mark them as resolved once fixed.

Our application is a Java program that manages security incident records. It uses a **custom Singly Linked List** built from scratch (without Java's built-in `LinkedList` or `ArrayList`), uses **Linear Search** to find records, and uses **Merge Sort** to organize incidents from highest to lowest severity.

---

## 2. Technical Justifications

### 2.1 Why Singly Linked List is Appropriate (2.2)
We implemented a custom Singly Linked List (`IncidentLinkedList.java`) for the following reasons:
1. **Dynamic Size:** Security incidents arrive unpredictably during the day. A linked list grows and shrinks as needed without requiring fixed array sizes or resizing copies.
2. **Efficient Deletions and Insertions:** Deleting or inserting nodes only requires updating reference pointers (`next`), which avoids shifting elements in memory like an array does.
3. **Low Memory Overhead:** A singly linked list only stores one pointer (`next`) per node, saving memory compared to a doubly linked list which requires both `prev` and `next` pointers.
4. **Natural fit for Merge Sort:** Linked lists can be split and merged by rewiring node pointers without creating extra arrays in memory.

---

### 2.2 Why Linear Search is Appropriate (2.3)
We implemented Linear Search (`linearSearchById`) to search for incidents by ID:
1. **Data is Unsorted by ID:** Incidents are added chronologically as they happen, meaning the list is unsorted by ID. Linear search works directly on unsorted data.
2. **No Direct Random Access:** Unlike arrays, linked lists do not support index lookups like `list[i]`. Because we cannot jump directly to the middle element in O(1) time, Binary Search would require traversing nodes anyway ($O(n \log n)$ total). Therefore, Linear Search is the most appropriate search algorithm for a singly linked list.
3. **Performance & Complexity:**
   - **Best Case — $O(1)$:** The incident being searched is at the head of the list (found on the 1st check).
   - **Average Case — $O(n)$:** On average, we inspect about half the list ($n/2$ steps).
   - **Worst Case — $O(n)$:** The incident is at the very end of the list or does not exist ($n$ steps).
   - **Space Complexity — $O(1)$:** No extra memory is allocated.

---

### 2.3 Why Merge Sort is Appropriate (2.4)
We implemented Merge Sort (`sortedBySeverity`) to rank incidents by severity (CRITICAL $\rightarrow$ HIGH $\rightarrow$ MEDIUM $\rightarrow$ LOW):
1. **Guaranteed $O(n \log n)$ Performance:** Merge Sort guarantees $O(n \log n)$ time complexity across all cases (best, average, and worst). It never degrades to $O(n^2)$ like QuickSort can on poor pivot choices, or like Bubble/Insertion sort.
2. **No Extra Memory for Linked Lists:** When Merge Sort is done on arrays, it requires $O(n)$ extra memory for temporary helper arrays. On a linked list, we only change the node `next` pointers in place, which uses $O(1)$ extra heap data space.
3. **Stability:** Merge Sort preserves the original order of incidents that have the same severity level (first-come, first-served within the same priority).
4. **Complexity:**
   - **Time Complexity:** $O(n \log n)$ (Best, Average, and Worst).
   - **Space Complexity:** $O(\log n)$ call stack space due to recursion, with $O(1)$ heap space for pointer rewiring.

---

## 3. How the Implementation Meets Functional Requirements (3)

| Requirement | Implementation in Code | Description |
| :--- | :--- | :--- |
| **1. Data Insertion** | `IncidentManager.addIncident(Incident)` | Validates that the ID is unique and appends the incident to the linked list. |
| **2. Data Removal** | `IncidentManager.removeIncident(String)` | Finds the node matching the ID and removes it by bypassing its pointer. |
| **3. Searching** | `IncidentManager.searchById(String)` | Uses Linear Search to find and return an incident by ID. |
| **4. Sorting** | `IncidentManager.sortBySeverity()` | Uses Merge Sort to sort all incidents by severity rank (CRITICAL first). |
| **5. Processing** | `IncidentManager.processIncident(String)` | Finds the incident and updates its status to `RESOLVED`. |
| **6. Display** | `IncidentManager.displayAll()` | Prints all stored incidents in a clean table format. |
| **7. Custom Structure** | `IncidentLinkedList.java` | Singly Linked List implemented completely from scratch with a custom `Node` class. |

---

## 4. Complexity Summary Table

| Operation | Best Case (Time) | Average Case (Time) | Worst Case (Time) | Space Complexity |
| :--- | :---: | :---: | :---: | :---: |
| **Insert (`addLast`)** | $O(n)$ | $O(n)$ | $O(n)$ | $O(1)$ |
| **Search (`linearSearchById`)** | $O(1)$ | $O(n)$ | $O(n)$ | $O(1)$ |
| **Delete (`removeById`)** | $O(1)$ | $O(n)$ | $O(n)$ | $O(1)$ |
| **Sort (`sortedBySeverity`)** | $O(n \log n)$ | $O(n \log n)$ | $O(n \log n)$ | $O(\log n)$ stack |

---

## 5. How to Compile and Run

### Step 1: Compile all Java files
From the project folder, run:
```bash
javac assignment20/*.java
```

### Step 2: Run the Main program
```bash
java assignment20.Main
```
