package category.tree;

public class PopulatingNextRightPointerInEachNodeII {
    public static void main(String[] args){
     
    }

    public Node connect(Node root) {
        Node dummyHead = new Node();
        Node pre = dummyHead;
        Node curr = root;
        while (curr != null) {
            if (curr.left != null) {
                pre.next = curr.left;   //link the dummyHead.next = curr.left
                pre = pre.next; // move pre pointer forward
            }
            if (curr.right != null) {
                pre.next = curr.right;
                pre = pre.next;
            }
            curr = curr.next;
            if (curr == null) {
                pre = dummyHead;
                curr = dummyHead.next;
                dummyHead.next = null;
            }
        }
        return root;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};