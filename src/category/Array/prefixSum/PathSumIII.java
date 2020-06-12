package category.Array.prefixSum;

import category.model.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/path-sum-iii/
 *
 * prefixSum + backtracking
 * Created by brianzhang on 12/9/18.
 */
public class PathSumIII {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left = new TreeNode(3);
        PathSumIII test = new PathSumIII();
        System.out.println(test.pathSumBacktrack(root, 8));
        System.out.println(test.pathSumRecursive(root, 8));
    }

    // Brute force - Time Complexity: O(nlogn)
    public int pathSumRecursive(TreeNode root, int sum) {
        if (root == null) return 0;
        // Get the paths start from each node
        return getPathFrom(root, sum) + pathSumRecursive(root.left, sum) + pathSumRecursive(root.right, sum);
    }

    public int getPathFrom(TreeNode node, int sum) { //Typical recursive DFS, no backtrack
        if (node == null) return 0;

        int count = node.val == sum ? 1 : 0;
        return count + getPathFrom(node.left, sum - node.val) + getPathFrom(node.right, sum - node.val);
    }

    // PrefixSum solution
    public int pathSumBacktrack(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        return backtrack(root, prefixSumMap, 0, sum);
    }

    // Time Complexity O(n)
    // Pre-order traversal - firstly traverse whole left subtree of root, then traverse the whole right subtree of root
    public int backtrack(TreeNode node, Map<Integer, Integer> prefixSumMap, Integer currSum, int target) {
        if (node == null) return 0;

        currSum += node.val;
        // get the number of valid previous prefix-sum which target + prefix-sum = currSum
        int numPathToCurr = prefixSumMap.getOrDefault(currSum - target, 0);
        // update the map with the current sum
        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);

        int res = numPathToCurr + backtrack(node.left, prefixSumMap, currSum, target) + backtrack(node.right, prefixSumMap, currSum, target);

        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1); //backtrack: remove the current prefix sum which added current node value

        return res;
    }
}
