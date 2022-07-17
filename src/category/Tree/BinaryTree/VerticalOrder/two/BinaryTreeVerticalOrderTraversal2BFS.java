package category.Tree.BinaryTree.VerticalOrder.two;

import category.model.TreeNode;
import java.util.*;

/**
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 *
 * This is similar as https://leetcode.com/problems/binary-tree-vertical-order-traversal/
 * the difference is if there may be multiple nodes in the same row and same column.
 * In such a case, sort these nodes by their values.
 *
 * Created by brianzhang on 7/28/21.
 */
public class BinaryTreeVerticalOrderTraversal2BFS {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        for(List<Integer> l : new BinaryTreeVerticalOrderTraversal2BFS().verticalTraversal(root)){
            System.out.println(l);
        }
    }

    // BFS
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Map<Integer,List<Node>> tMap = new TreeMap(); // (col : nodes): col is vertical order level, alternatively think it like x-coordinate
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(root,0,0));

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            tMap.putIfAbsent(n.col, new ArrayList());
            tMap.get(n.col).add(n);

            if (n.node.left != null) queue.add(new Node(n.node.left,n.col -1,n.row +1));
            if (n.node.right != null) queue.add(new Node(n.node.right,n.col +1,n.row +1));
        }

        tMap.forEach((col,list) -> {
            Collections.sort(list, (n1,n2) -> { // replace NodeComparator
                if(n1.row == n2.row) { // if the row is same and in the same col, sort by values
                    return  n1.node.val - n2.node.val;
                }
                return  n1.row - n2.row;
            });
            List<Integer> currLevel = new ArrayList();
            for(Node n : list) {
                currLevel.add(n.node.val);
            }
            res.add(currLevel);
        });
        return res;
    }

    // If horizontal row is same, make sure the lower number is first
/*    public class NodeComparator implements Comparator<Node> {
        public int compare(Node n1, Node n2){
            if(n1.row != n2.row)
                return n1.row - n2.row;
            else
                return n1.node.val - n2.node.val;
        }
    }*/

    class Node {
        private TreeNode node;
        private int col; // Vertical Level (left decreasing, right increasing)
        private int row; // Horizontal distance from root (top to bottom increasing)

        public Node(TreeNode node, int vLevel, int depth){
            this.node = node;
            this.col = vLevel;
            this.row = depth;
        }
    }
}
