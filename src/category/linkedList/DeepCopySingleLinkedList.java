package category.linkedList;

public class DeepCopySingleLinkedList {
    public static void main(String[] args) {
        Node node1 = new  Node(1);
        Node node2 = new  Node(2);
        Node node3 = new  Node(3);
        Node node4 = new  Node(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        Node to = deepCopyRecursive(node1);
        while(to != null) {
            System.out.println(to.val);
            to = to.next;
        }
    }

    // public Node deepCopyIterative(Node from) {
    //     Node to = new Node(from.val);
    //     Node dummyNode = to;
    //     from = from.next;
    //     while(from != null) {
    //         Node n = new Node(from.val);
    //         from = from.next;
    //         dummyNode.next = n;
    //         dummyNode = n;
    //     }

    //     return to;
    // }

    static Node deepCopyRecursive(Node from) {
        if(from == null) return null;

        Node n = new Node(from.val);
        n.next = deepCopyRecursive(from.next);
        return n;
    }

    static class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }
}

