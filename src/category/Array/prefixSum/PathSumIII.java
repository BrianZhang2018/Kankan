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

    }

//Brute force solution - Time Complexity: O(nlogn)
    public int pathSum1(TreeNode root, int sum) {
        if (root == null)
            return 0;

        return getPathFrom1(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    //Typical recursive DFS, no backtrack
    public int getPathFrom1(TreeNode node, int sum) {
        if (node == null)
            return 0;

        return (node.val == sum ? 1 : 0) + getPathFrom1(node.left, sum - node.val) + getPathFrom1(node.right, sum - node.val);
    }

// prefixSum solution
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        return backtrack(root, prefixSumMap, 0, sum);
    }

    //Time Complexity O(n)
    public int backtrack(TreeNode node, Map<Integer, Integer> prefixSumMap, Integer currSum, int target) {
        if (node == null)
            return 0;
        // update the prefix sum by adding the current val
        currSum += node.val;
        // update the map with the current sum, so the map is good to be passed to the next recursion
        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);
        // get the number of valid path, ended by the current node
        int numPathToCurr = prefixSumMap.getOrDefault(currSum - target, 0);  //currSum-target = the sum of qualified paths

        int res = numPathToCurr + backtrack(node.left, prefixSumMap, currSum, target) + backtrack(node.right, prefixSumMap, currSum, target);

        //backtrack step: remove the current node from map
        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);
        //currSum -= node.val;

        return res;
    }

}
