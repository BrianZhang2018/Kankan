package category.tree.BinaryTree.pathSum;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * 
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/39875/Elegant-Java-solution
 * 
 * you can imagine the dfsHelper() function goes from the bottom of the binaryTree to the top, it's in "post-order" manner.
 * At every node, we need to make a decision, if the sum comes from the left path larger than the right path,
 * we pick the left path and plus the current node's value, this recursion goes all the way up to the root node.
 */
public class BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(new BinaryTreeMaximumPathSum().maxPathSum(root));
    }

    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        
        dfs(root);
        return maxSum;
    }

    // it's actually a post-order traversal
    public int dfs(TreeNode root) {
        if(root == null) return 0;

        int left = Math.max(dfs(root.left), 0);   // avoid the negative value, it's Kadane algorithm idea
        int right = Math.max(dfs(root.right), 0);
        maxSum = Math.max(maxSum, root.val + left + right);
        
        return root.val + Math.max(left, right);
    }

    // open-question: get maximum path sum for n-ary tree
}