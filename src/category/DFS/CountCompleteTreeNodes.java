package category.DFS;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/
 *
 * Created by brianzhang on 2/8/20.
 */
public class CountCompleteTreeNodes {
    public static void main(String[] args) {
    }

    public int countNodes(TreeNode root) {
        return root != null ? 1 + countNodes(root.left) + countNodes(root.right): 0;
    }
}
