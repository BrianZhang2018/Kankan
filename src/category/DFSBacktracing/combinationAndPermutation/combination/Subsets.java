package category.DFSBacktracing.combinationAndPermutation.combination;

import java.util.*;

/**
 * https://leetcode.com/problems/subsets/
 *
 * Time complexity: O(n * 2^n) to generate all subsets and then copy them into output list.
 * (2^N, since each element could be two situations, absent or present. 0(n) for each copy)
 * Space complexity: O(n)
 *
 * Created by brianzhang on 7/25/18.
 */
public class Subsets {
    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }
    private static void backtrack(List<List<Integer>> list, List<Integer> tempList,
                                  int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            // the 'i+1' is used to go next number
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
