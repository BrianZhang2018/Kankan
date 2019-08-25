package category.tree.bondary;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 * 
 */
public class PopulatingNextRightPointerInEachNodeII {

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

    //lever order traverse
    public Node connectMySolution(Node root) {
        if(root == null)
            return root;
    
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            
            int size = queue.size();
            Node pre = null;
            for(int i=0; i<size; i++){
                Node curr = queue.poll();
                if(i == 0){
                    pre = curr;
                }
                if(i > 0){
                    pre.next = curr;
                    pre = pre.next;
                }
                if(curr.left != null){
                    queue.add(curr.left);
                }
                if(curr.right != null){
                    queue.add(curr.right);
                }
            }
            pre = null;
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