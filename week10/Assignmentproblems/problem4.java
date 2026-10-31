class Node {
    String data;
    Node prev;
    Node next;

    Node(String data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    Node head;
    Node tail;

    // Add coach at the end
    void addLast(String name) {
        Node newNode = new Node(name);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Remove coach by name
    void remove(String name) {
        Node temp = head;

        // Find the coach
        while (temp != null && !temp.data.equals(name)) {
            temp = temp.next;
        }

        // If coach not found
        if (temp == null) {
            return;
        }

        // Update previous node
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        } else {
            head = temp.next;
        }

        // Update next node
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        } else {
            tail = temp.prev;
        }
    }

    // Print forward
    void printForward() {
        Node temp = head;

        System.out.print("Forward: ");

        while (temp != null) {
            System.out.print(temp.data);

            if (temp.next != null) {
                System.out.print(" <-> ");
            }

            temp = temp.next;
        }

        System.out.println();
    }

    // Print backward
    void printBackward() {
        Node temp = tail;

        System.out.print("Backward: ");

        while (temp != null) {
            System.out.print(temp.data);

            if (temp.prev != null) {
                System.out.print(" <-> ");
            }

            temp = temp.prev;
        }

        System.out.println();
    }
}

public class problem4 {
    public static void main(String[] args) {

        DoublyLinkedList train = new DoublyLinkedList();

        // Add coaches
        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        // Remove coach
        train.remove("B1");

        // Display train
        train.printForward();
        train.printBackward();
    }
}
