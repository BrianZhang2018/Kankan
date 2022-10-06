package category.DFSBacktracing.combinationAndPermutation.combination;

import java.util.*;
/**
 * https://leetcode.com/problems/combination-sum-ii/
 * Each number in candidates may "only be used once" in the combination.
 *
 * Time Complexity is O(2^n), which is the number of combinations.
 * e.g. [1, 2],  [3,4], the time complexity is 2*2 = 4
 *
 * Created by brianzhang on 3/17/19.
 */
public class CombinationSumII {
    public static void main(String[] args) {
        System.out.println(new CombinationSumII().combinationSum(new int[]{10,1,2,7,6,1,5}, 8));
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);
        dfs(nums, target, 0, new ArrayList(), res);
        return res;
    }

    // backtracking
    private void dfs(int[] nums, int target, int start, List<Integer> temp, List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // avoid duplicate combination

            temp.add(nums[i]);
            dfs(nums, target - nums[i], i+1, temp, res);
            temp.remove(temp.size() - 1);
        }
    }
}
