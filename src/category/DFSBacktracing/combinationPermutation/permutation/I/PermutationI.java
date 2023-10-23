package category.DFSBacktracing.combinationPermutation.permutation.I;

import java.util.*;

/**
 * https://leetcode.com/problems/permutations/
 *
 * Time complexity: O(N*N!), N is the number of elements in input
 * We have n! permutation. And, for each permutation, you run exact n recursive call to reach it.
 * (note: time complexity for "recursive" function is O(nodes^depth+1) * (amount of work at each node, e.g. copy) in the recursive call tree)
 *
 * Space complexity: O(N), store the value during the recursive call. where N is the length of input.
 *
 * Not counting space used for the output, the extra space (memory) we use relative to input size
 * is the space occupied by the recursion call stack. It will only go as deep as
 * the number of digits in the input since whenever we reach that depth, we backtrack.
 *
 * (Auxiliary Space Complexity ^^) + input space complexity is O(n), and won't be changed = O(2n), or simplified to O(n)
 *
 * output space complexity required: O(n!*n) :  N! subArrays that have a length of N.
 * since Space Complexity = Auxiliary Space + Space used for input values, so output space complexity is not one for performance evaluation
 *
 * https://www.youtube.com/watch?v=KukNnoN-SoY
 * Created by brianzhang on 7/26/18.
 */
public class PermutationI {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }
    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 0; i < nums.length; i++) { // i start with 0
            if(tempList.contains(nums[i])) continue;

            tempList.add(nums[i]);
            backtrack(list, tempList, nums);      System.out.println("remove before: " + tempList);
            tempList.remove(tempList.size() - 1);
        }
    }
}

/*
 * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 * brute force solution using DFS & dfsHelper
 * 深度搜素到底，然后在回溯，遍历每一层，重复这个动作
 **/
