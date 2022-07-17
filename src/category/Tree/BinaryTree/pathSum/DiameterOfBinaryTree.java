package category.Tree.BinaryTree.pathSum;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 *
 * So although the problem mentioned longest path doesn't have to go through the root node,
 * but the reality is the longest path has to go through the root node
 *
 * Created by brianzhang on 8/19/21.
 */
public class DiameterOfBinaryTree {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root ==null) return 0;
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root) {
        if(root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}
