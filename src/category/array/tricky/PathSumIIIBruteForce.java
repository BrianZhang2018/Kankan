package category.array.tricky;

import category.model.TreeNode;

/**
 * Created by brianzhang on 12/9/18.
 */
public class PathSumIIIBruteForce {

    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;

        return getPathFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    //Typical recursive DFS, no backtrack
    public int getPathFrom(TreeNode node, int sum) {
        if (node == null)
            return 0;

        return (node.val == sum ? 1 : 0) + getPathFrom(node.left, sum - node.val) + getPathFrom(node.right, sum - node.val);
    }
}
