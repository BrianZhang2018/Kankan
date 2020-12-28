package category.BinaryTree.boundaryView;
/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * 
 * level order traverse - leftmost way rather than the queue
 */
public class PopulatingNextRightPointerInEachNodeI { 
    
    public Node connect(Node root) {
        if(root == null)
            return root;
        
        Node leftMost = root;
        
        //level order traverse
        while(leftMost != null){
            Node curr = leftMost;
            while(curr != null){
                if(curr.left  != null)
                    curr.left.next = curr.right;
                if(curr.right != null)
                    curr.right.next = (curr.next == null ? null : curr.next.left);
                
                curr = curr.next; // go the next node of in this level
            }
            //start from left most
            leftMost = leftMost.left;
        }
        return root;
    }
}

/*
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
};*/
