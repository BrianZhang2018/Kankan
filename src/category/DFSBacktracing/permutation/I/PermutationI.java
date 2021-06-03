package category.DFSBacktracing.permutation.I;

import java.util.*;

/**
 * https://leetcode.com/problems/permutations/
 *
 * Time complexity: O(n!*n)
 * Space complexity: O(n!*n)
 *
 * For any recursive function, the time complexity is O(branches^depth) * (amount of work at each node, e.g. for-loop) in the recursive call tree.
 * However, in this case, we have n*(n-1)*(n-2)*(n-3)*...*1 branches at the corresponding level, so the total recursive calls is O(n!).
 * We also do "n-amount" of work in each node of the recursive call tree, the for-loop. So this is a total of O(n) additional work per node.
 * The time complexity is O(n! * n).
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
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if(used[i]) continue;

                tempList.add(nums[i]);
                used[i] = true;
                backtrack(list, tempList, nums, used);      System.out.println("remove before: " + tempList);
                tempList.remove(tempList.size() - 1);
                used[i] = false;                            System.out.println("               remove after: " + tempList);
            }
        }
    }
}

/*
 * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 * brute force solution using DFS & dfsHelper
 * 深度搜素到底，然后在回溯，遍历每一层，重复这个动作
 **/
