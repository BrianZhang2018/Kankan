package category.BacktracingDFS.binarytree;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/path-sum-iii/
 *
 * Created by brianzhang on 12/9/18.
 */
public class PathSumIIIBruteForce {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(1);
        root.right = new TreeNode(9);

        System.out.println(new PathSumIIIBruteForce().pathSum(root, 8));
    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;

        return getPathFrom(root, sum)+pathSum(root.left, sum)+pathSum(root.right, sum);
    }

    // Typical recursive DFS, no dfs
    public int getPathFrom(TreeNode node, int sum) {
        if (node == null)
            return 0;

        return (node.val == sum ? 1 : 0)+getPathFrom(node.left, sum - node.val)+getPathFrom(node.right, sum - node.val);
    }
}
