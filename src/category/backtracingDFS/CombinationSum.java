package category.BacktracingDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 * the same number can be used unlimited times
 *
 * Created by brianzhang on 7/29/18.
 */
public class CombinationSum {
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,6,7}; int target = 7;
        System.out.println(new CombinationSum().combinationSum(arr, target));
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums,0, target, new ArrayList<>(), res);
        return res;
    }

    //recursive dfs with backtracking
    private void dfs(int[] nums, int start,int target, List<Integer> temp, List<List<Integer>> res) {
        if (target < 0)
            return;
        else if (target == 0) {
            res.add(new ArrayList<>(temp));  //deep copy as the 'temp' is reference type which will be changed in recursive
        } else {
            for (int i = start; i < nums.length; i++) {
                temp.add(nums[i]);
                dfs(nums, i,target - nums[i], temp, res); //not i + 1 because we can reuse same element
                temp.remove(temp.size() - 1);
            }
        }
    }
}
