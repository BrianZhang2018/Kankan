package category.DFSBacktracing.tree;

import category.model.TreeNode;
import java.util.*;

/**
 * https://leetcode.com/problems/path-sum-iii/
 *
 * The path does not need to start or end at the root or a leaf, but it must
 * go downwards (i.e., traveling only from parent nodes to child nodes).
 *
 * PrefixSum + backtracking
 * Created by brianzhang on 12/9/18.
 */
public class PathSumIII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(1);
        root.right = new TreeNode(9);
        System.out.println(new PathSumIII().pathSum(root, 8));
    }

    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        return backtrack(root, prefixSumMap, 0, sum);
    }

    // Preorder traversal since the question asked that the path go downwards
    // preorder traverse whole left subtree of root, then traverse the whole right subtree of root
    public int backtrack(TreeNode node, Map<Integer, Integer> prefixSumMap, Integer currSum, int target) {
        if (node == null) return 0;

        currSum += node.val;
        // get the number of valid previous prefixSum which "prefixSum = currSum - target"
        int numPathToCurr = prefixSumMap.getOrDefault(currSum - target, 0);
        System.out.println("currSum: " + currSum);
        System.out.println("target: " + target);

        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);
        int res = numPathToCurr + backtrack(node.left, prefixSumMap, currSum,
                target) + backtrack(node.right, prefixSumMap, currSum, target);
        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);

        return res;
    }

}
