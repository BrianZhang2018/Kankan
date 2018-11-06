package category.divideAndConquer;

import category.model.TreeNode;

/**
 * Created by brianzhang on 8/5/18.
 */
public class MaximumDepthBinarySearchTree {

    public int isBalancedBTree(TreeNode root) {

        return maxDepth(root);
    }

    private int maxDepth(TreeNode root) {

        if (root == null)
            return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }
}
