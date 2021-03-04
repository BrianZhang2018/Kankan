package category.Array.prefixSum;

import category.model.TreeNode;
import java.util.*;

/**
 * https://leetcode.com/problems/path-sum-iii/
 *
 * prefixSum + backtracking
 *
 * Time Complexity O(n)
 * Created by brianzhang on 12/9/18.
 */
public class PathSumIII {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left = new TreeNode(3);

        System.out.println(new PathSumIII().pathSumBacktrack(root, 8));
    }

    public int pathSumBacktrack(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        return backtrack(root, prefixSumMap, 0, sum);
    }

    // Pre-order traversal - firstly traverse whole left subtree of root, then traverse the whole right subtree of root
    public int backtrack(TreeNode node, Map<Integer, Integer> prefixSumMap, Integer currSum, int target) {
        if (node == null) return 0;

        currSum += node.val;
        // get the number of valid previous prefix-sum which target + prefix-sum = currSum
        int numPathToCurr = prefixSumMap.getOrDefault(currSum - target, 0);

        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);

        int res = numPathToCurr + backtrack(node.left, prefixSumMap, currSum, target) + backtrack(node.right, prefixSumMap, currSum, target);

        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);

        return res;
    }
}
