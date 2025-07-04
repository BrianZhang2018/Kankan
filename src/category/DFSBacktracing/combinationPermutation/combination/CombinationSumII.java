package category.DFSBacktracing.combinationPermutation.combination;

import java.util.*;
/**
 * https://leetcode.com/problems/combination-sum-ii/
 * 1. contain duplicate number
 * 2. each number in candidates may "only be used once" in the combination.
 *
 * Time Complexity is O(2^n), which is the number of combinations.
 * e.g. [1, 2],  [3,4], the time complexity is 2*2 = 4
 * Space Complexity: O(N)
 * Created by brianzhang on 3/17/19.
 */
public class CombinationSumII {
    public static void main(String[] args) {
        System.out.println(new CombinationSumII().combinationSum(new int[]{10,1,2,7,6,1,5}, 8));
    }
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums); // 剪枝
        dfs(nums, target, 0, new ArrayList(), res);
        return res;
    }
    // backtracking
    private void dfs(int[] nums, int target, int start, List<Integer> temp, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if(nums[i] > target) break; // optimization (剪枝), that's why we sort the number in the beginning

            // avoid duplicate combination, skip duplicate "nums[i]" in current loop since duplicate nums[i-1] already used
            if (i > start && nums[i] == nums[i - 1]) continue;

            temp.add(nums[i]);
            dfs(nums, target - nums[i], i+1, temp, res); // i+1, each number since only can be used once
            temp.remove(temp.size() - 1);
        }
    }
}
