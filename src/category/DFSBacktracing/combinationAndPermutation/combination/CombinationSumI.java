package category.DFSBacktracing.combinationAndPermutation.combination;

import java.util.*;
/**
 * https://leetcode.com/problems/combination-sum/
 * (zillow) 首先clarify的问题和interviewer,下面都是clarify的点, 对于解题很重要
 * Assumptions: 1. The same number can be used "unlimited times"
 *              2. unique combination
 *              3. no duplicate number
 *
 * Refer the screenshot: CombinationSumITimeComplexity.png
 *
 * Time complexity: loosely speaking is O(T/M * N^(T/M)), N: the number of candidates, T: target value, M: minimal value among the candidates.
 * N^(T/M) is total nodes (combinations), for each node (combination) which take T/M times combine to reach.
 * Same tc idea for permutation
 *
 * Created by brianzhang on 7/29/18.
 */
public class CombinationSumI {
    public static void main(String[] args) {
        System.out.println(new CombinationSumI().combinationSum(new int[]{2,3,6,7}, 7));
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
        for (int index = start; index < nums.length; index++) { // but, permutation i=0
            if(nums[index] > target)
                break; // optimization (剪枝), that's why we sort the number in the beginning to avoid calculate the impossible larger candidates

            temp.add(nums[index]);
            dfs(nums, index,target - nums[index], temp, res); // not "i + 1" here since can reuse same element
            temp.remove(temp.size() - 1);
        }
    }
}
