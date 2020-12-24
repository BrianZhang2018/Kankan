package category.BacktracingDFS;

import java.util.*;
/**
 * https://leetcode.com/problems/combination-sum/
 * The same number can be used unlimited times
 *
 * Can be used to solve the "Coin Change" problem if need output the combination, but slow.
 * "CoinChangeOutputCombination.java" use the 剪枝法DFS中, make it faster.
 *
 * Created by brianzhang on 7/29/18.
 */
public class CombinationSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,5}; int target = 5;
        System.out.println(new CombinationSum().combinationSum(arr, target));
        //System.out.println(new CombinationSum().change(target, arr));
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);
        dfs(nums,0, target, new ArrayList<>(), res);
        return res;
    }

    //recursive dfs with backtracking
    private void dfs(int[] nums, int start,int target, List<Integer> temp, List<List<Integer>> res) {
        if (target < 0) return;
        else if (target == 0) {
            res.add(new ArrayList<>(temp));
        } else {
            for (int i = start; i < nums.length; i++) {
                temp.add(nums[i]);
                dfs(nums, i,target - nums[i], temp, res); // not "i + 1" here because we can reuse same element
                temp.remove(temp.size() - 1);
            }
        }
    }
}
