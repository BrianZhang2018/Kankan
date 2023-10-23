package category.BFSOrDFS;

import category.model.TreeNode;
import java.util.*;

/**
 * https://leetcode.com/problems/range-sum-of-bst/description/
 */
public class RangeSumOfBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);
        System.out.println(rangeSumBST(root, 7, 15));
        System.out.println(rangeSumBST1(root, 7, 15));
    }

    // DFS
    public static int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null) return 0;
        int sum = 0;
        if(root.val >= low) {
            sum += rangeSumBST(root.left, low, high);
        }
        if(root.val <= high) {
            sum += rangeSumBST(root.right, low, high);
        }
        if(root.val >= low && root.val <= high) {
            sum += root.val;
        }

        return sum;
    }

    // BFS
    public static int rangeSumBST1(TreeNode root, int low, int high) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        int res = 0;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.val >= low && node.val <= high){
                res+=node.val;
            }
            if(node.val > low && node.left != null) queue.add(node.left);
            if(node.val < high && node.right != null) queue.add(node.right);
        }

        return res;
    }

}
