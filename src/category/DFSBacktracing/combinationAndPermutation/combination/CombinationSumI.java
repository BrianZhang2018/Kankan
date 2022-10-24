package category.DFSBacktracing.combinationAndPermutation.combination;

import java.util.*;
/**
 * https://leetcode.com/problems/combination-sum/
 * 首先clarify的问题和interviewer,下面都是clarify的点, 对于解题很重要
 * Assumptions: 1. The same number can be used "unlimited times"
 *              2. unique combination
 *              3. no duplicate number
 *
 * (refer the screenshot: CombinationSumITimeComplexity.png)
 * Time complexity: loosely speaking is O(T/M * N^(T/M)), N: the number of candidates, T: target value, M: minimal value among the candidates
 * N^(T/M) is total nodes (combinations), for each node (combination) which take T/M times combine to reach.
 * Same tc idea for permutation
 *
 * Created by brianzhang on 7/29/18.
 */
public class CombinationSumI {
    public static void main(String[] args) {
        System.out.println(new CombinationSumI().combinationSum(new int[]{1,2,5}, 5));
        // System.out.println(new CombinationSum().change(target, arr));
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums,0, target, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int start, int target, List<Integer> temp, List<List<Integer>> res) {
         if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < nums.length; i++) { // but, permutation i=0
            if(nums[i] > target) break; // why we need "Sort the Number", so we can skip the greater number here on I right side.

            temp.add(nums[i]);
            dfs(nums, i,target - nums[i], temp, res); // not "i + 1" here since can reuse same element
            temp.remove(temp.size() - 1);
        }
    }
}
