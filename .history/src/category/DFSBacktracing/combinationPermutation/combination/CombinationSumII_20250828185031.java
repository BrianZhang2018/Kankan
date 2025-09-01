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
        System.out.println(new CombinationSumII().combinationSum(new int[]{1,1,2}, 3));
    }
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);
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

            // avoid duplicate combination, skip later duplicate "nums[i]" in current loop since  nums[i-1] already used, refer bleow example
            if (i > start && nums[i] == nums[i - 1]) continue;

            temp.add(nums[i]);
            dfs(nums, target - nums[i], i+1, temp, res); // i+1, since each number can only be used once
            temp.remove(temp.size() - 1);
        }
    }
}



/*
Visual Example
Without the line (generates duplicates):

[1₁, 1₂, 2] target=3
├── Use 1₁: [1₁, 2] ✓
└── Use 1₂: [1₂, 2] ✗ (same as above)

With the line (no duplicates):

[1₁, 1₂, 2] target=3
├── Use 1₁: [1₁, 2] ✓
└── Skip 1₂ (duplicate in same level)
*/
