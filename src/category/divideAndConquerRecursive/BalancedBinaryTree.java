package category.divideAndConquerRecursive;

import category.model.TreeNode;

/**
 * Created by brianzhang on 8/5/18.
 */
public class BalancedBinaryTree {

    public boolean isBalancedBTree(TreeNode root) {

        return maxDepth(root) != -1;
    }

    private int maxDepth(TreeNode root) {

        if (root == null)
            return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        //-1 flag means it's a unbalanced tree
        if (left == -1 || right == -1 || Math.abs(right - left) > 1)
            return -1;

        return Math.max(left, right) + 1;
    }
}
