package category.DFSBacktracing.combination;

import java.util.*;
/**
 * https://leetcode.com/problems/combination-sum/
 * The same number can be used "unlimited times"
 *
 * Can be used to solve the "Coin Change" problem if need output the combination, but slow.
 * "CoinChangePrintOutCombination.java" use the 剪枝法DFS中, make it faster.
 *
 * Time complexity is O(N^target) where N is a length of candidates array.
 * Space complexity is O(target).
 *
 * This is worst case and without any optimization, like moving position forward and sorting to stop early.
 * Just assuming that each recursive step we go over all existing candidates, so base N.
 * And go as deep as target in our recursive calls (if candidates are close to 1), so power of target.
 * You can mention that this is worst case and optimizations can make time complexity a little better, for interview I think this should be enough.
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

    // recursive dfsHelper with backtracking
    private void dfs(int[] nums, int start, int target, List<Integer> temp, List<List<Integer>> res) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            res.add(new ArrayList<>(temp));
        } else {
            for (int i = start; i < nums.length; i++) { // permutation, i=0
                temp.add(nums[i]);
                dfs(nums, i,target - nums[i], temp, res); // not "i + 1" here since can reuse same element
                temp.remove(temp.size() - 1);
            }
        }
    }
}
