package category.DivideAndConquerRecursive;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 *
 * Created by brianzhang on 8/5/18.
 */
public class BalancedBinaryTree {

    public static void main(String[] args) {
        BalancedBinaryTree test = new BalancedBinaryTree();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(2);
        root.right = new TreeNode(11);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(1);

        System.out.println(test.isBalancedBTree(root));
    }

    public boolean isBalancedBTree(TreeNode root) {

        return maxDepth(root) != -1;
    }

    private int maxDepth(TreeNode root) {

        if (root == null)
            return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        //-1 flag means it's a unbalanced binaryTree
        if (left == -1 || right == -1 || Math.abs(right - left) > 1)
            return -1;

        return Math.max(left, right) + 1;
    }
}
