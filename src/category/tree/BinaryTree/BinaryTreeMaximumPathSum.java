package category.tree.BinaryTree;

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
        TreeNode root = new TreeNode(5); // -10
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

    // post-order traversal
    public int dfs(TreeNode root) {
        if(root == null) return 0;

        // avoid the negative value
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        
        maxSum = Math.max(maxSum, root.val + left + right);
        
        return root.val + Math.max(left, right); // A node can only appear in the sequence at most once mentioned in problem. so if return both, the root node will be appeared twice in your further path
    }

    // open-question: get maximum path sum for n-ary tree
}