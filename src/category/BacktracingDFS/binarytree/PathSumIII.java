package category.BacktracingDFS.binarytree;

import category.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/path-sum-iii/
 *
 * PrefixSum + backtracking
 * Created by brianzhang on 12/9/18.
 */
public class PathSumIII {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(1);
        root.right = new TreeNode(9);

        System.out.println(new PathSumIII().pathSum(root, 8));
    }

    // PrefixSum solution
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        return backtrack(root, prefixSumMap, 0, sum);
    }

    // Time Complexity O(n)
    // Pre-order traversal - firstly traverse whole left subtree of root, then traverse the whole right subtree of root
    public int backtrack(TreeNode node, Map<Integer, Integer> prefixSumMap, Integer currSum, int target) {
        if (node == null) return 0;
        // update the prefix sum by adding the current node val
        currSum += node.val;
        // update the map with the current sum, then pass to next
        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);
        // get the number of valid path ended by the current node (currSum-target = the sum of qualified paths)
        int numPathToCurr = prefixSumMap.getOrDefault(currSum - target, 0);

        int res = numPathToCurr + backtrack(node.left, prefixSumMap, currSum, target)
                + backtrack(node.right, prefixSumMap, currSum, target);

        //dfs: remove the current prefix sum which added current node value
        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1); // 

        return res;
    }
}
