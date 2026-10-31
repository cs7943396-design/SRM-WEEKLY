class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class CircularLinkedList {
    Node tail;

    // Add player at the end
    void addLast(String name) {
        Node newNode = new Node(name);

        if (tail == null) {
            tail = newNode;
            tail.next = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Print players for given turns
    void printTurns(int turns) {
        if (tail == null) {
            return;
        }

        Node current = tail.next;

        for (int i = 0; i < turns; i++) {
            System.out.print(current.data);

            if (i < turns - 1) {
                System.out.print(" ");
            }

            current = current.next;
        }

        System.out.println();
    }
}

public class problem5 {
    public static void main(String[] args) {

        CircularLinkedList game = new CircularLinkedList();

        // Add players
        game.addLast("Asha");
        game.addLast("Ravi");
        game.addLast("Neha");

        // Number of turns
        int turns = 7;

        // Display turns
        game.printTurns(turns);
    }
}
