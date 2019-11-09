import java.util.Stack;

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
        // Here you want to check whether the current node is the one that is null
        // and not whether head.jump or head.next is, why is that?
        // It's because the job of the current node is to not worry about the edge case, but instead
        // it would be passed to the next one
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
    Now we will attempt to create order iteratively
    So in this approach we have to essentially create a stack
    So it knows when the stack is full
     */
    public static void OrderNodeIterative(Node head) {
        // Let's initialize the stack first
        Stack<Node> stack = new Stack<>();

        // There's actually no need to store the order value in
        // we can just have a variable since all the update will happen in the method itself
        int order = 0;

        // First add the head to the stack
        stack.push(head);

        // We will keep traversing until (DFS) we have reached the end
        while (!stack.isEmpty()) {
            // Extract the top node
            Node node = stack.pop();
            // If the node is null or has already been set,
            // then we don't need to process it
            if (node == null || node.val != -1) continue;

            // set the node's val to the current order
            node.val = order;

            // update the current order
            order++;

            // add the node.next first to the stack since it is last in first out
            stack.push(node.next);
            stack.push(node.jump);
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

        OrderNodeIterative(head);

        print(head);
    }
}
