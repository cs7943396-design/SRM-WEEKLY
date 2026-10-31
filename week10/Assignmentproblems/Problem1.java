class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;

    // Insert at beginning
    void addFirst(int token) {
        Node newNode = new Node(token);

        newNode.next = head;
        head = newNode;
    }

    // Insert at end
    void addLast(int token) {
        Node newNode = new Node(token);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }

    // Insert at given index
    void insertAt(int index, int token) {
        if (index == 0) {
            addFirst(token);
            return;
        }

        Node newNode = new Node(token);
        Node temp = head;

        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }

        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Display linked list
    void display() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }
}

public class problem1{
    public static void main(String[] args) {

        SinglyLinkedList list = new SinglyLinkedList();

        // Starting list
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        // Operations
        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        // Print final list
        list.display();
    }
}
