package category.DFSBacktracing.tree;

import category.model.TreeNode;
import java.util.*;

/**
 * https://leetcode.com/problems/path-sum-ii/
 *
 * return all root-to-leaf paths where the sum of the node values in the path equals targetSum
 *
 * Created by brianzhang on 12/4/18.
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> pathsList = new ArrayList<>();
        List<Integer> pathNodes = new ArrayList<>();
        this.dfs(root, sum, pathNodes, pathsList);
        return pathsList;
    }

    private void dfs(TreeNode node, int remainingSum, List<Integer> pathNodes, List<List<Integer>> pathsList) {
        if (node == null) {
            return;
        }
        pathNodes.add(node.val);
        // Check if the current node is a leaf and also if it equals the remaining sum. If it does, we add the path to our list of paths
        if (remainingSum == node.val && node.left == null && node.right == null) {
            pathsList.add(new ArrayList<>(pathNodes));
        } else {
            this.dfs(node.left, remainingSum - node.val, pathNodes, pathsList);
            this.dfs(node.right, remainingSum - node.val, pathNodes, pathsList);
        }
        // pop the node once we are done processing ALL of it's subtrees.
        pathNodes.remove(pathNodes.size() - 1);
    }
}
/* time complexity:
    Consider the tree like below, not balanced:
     A
    / \
    B   C
        / \
        D   E
            / \
             and so on...
    Let the number of nodes = n
    Therefore depth of tree is approx n/2 and number of leaves are also approx n/2
    Now, potential correct paths are of "length": 2, 3, ... n/2
    Copying these n/2 paths n/2 times to the answer arr gives us the complexity O(n^2)
    (note: copy numbers to a new list which can take up to O(n) time)
 */
