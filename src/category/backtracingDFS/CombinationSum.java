package category.BacktracingDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 * https://leetcode.com/problems/combinations/
 *
 * Created by brianzhang on 7/29/18.
 */
public class CombinationSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 2, 2};
        int target = 4;
        System.out.println(new CombinationSum().combinationSum(arr, target));
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, target, 0, temp, res);
        return res;
    }

    //recursive dfs with backtracking
    private void dfs(int[] nums, int target, int start, List<Integer> temp, List<List<Integer>> res) {
        if (target < 0)
            return;
        else if (target == 0) {
            res.add(new ArrayList<>(temp));  //need deep copy as the 'temp' list is reference which will be changed in recursive
        } else {
            for (int i = start; i < nums.length; i++) {
                temp.add(nums[i]);
                dfs(nums, target - nums[i], i, temp, res);
                temp.remove(temp.size() - 1);
            }
        }
    }

}
