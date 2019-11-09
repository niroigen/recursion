public class JumpReference {
    private static class Node {
        Node jump;
        Node next;
        int val;

        Node() {
            val = -1;
        }
    }

    static int order = 0;

    /*
    Let's make a driver function that will make all the necessary setup
    Which would initialize an output variable to keep track of the call order
     */
    public static void OrderNode(Node head) {
        if (head == null || head.val != -1) return;

        head.val = order++;
        OrderNode(head.jump);
        OrderNode(head.next);
    }

    static void print(Node head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    /*
    Here we are trying to implement an order of what the calls for a linked list
    The node is very similar to a regular linked list except that this would have a
    jump pointer, which would go to any other node
     */
    public static void main(String[] args) {
        Node head = new Node();
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();

        head.jump = node1;
        head.next = node1;

        node1.jump = node3;
        node1.next = node2;

        node2.jump = node1;
        node2.next = node3;

        OrderNode(head);

        print(head);
    }
}
