package category.DFSBacktracing.combinationAndPermutation.permutation.II;

import java.util.*;

/**
 * https://leetcode.com/problems/3sum/
 *
 * Similar to Permutation II
 * Created by brianzhang on 5/3/20.
 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();
        List<List<Integer>> res = ts.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for(List<Integer> list : res){
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);  // sort array
        dfs(nums, new ArrayList<>(), 0, 0, new boolean[nums.length], res);
        return res;
    }

    public void dfs(int[] nums, List<Integer> temp, int target, int start, boolean[] used, List<List<Integer>> res){
        if(target == 0 && temp.size() == 3){
            res.add(new ArrayList(temp));
            return;
        }

        for(int i=start; i<nums.length; i++){
            if(used[i]) continue;
            // makes sure when duplicates are selected, we only use the ascending (used[i-1] = true) order
            // so that duplicate number only use once
            if ((i > 0 && nums[i] == nums[i-1]) && !used[i-1]) continue;

            target += nums[i];
            used[i] = true;
            temp.add(nums[i]);
            dfs(nums, temp, target, i+1, used, res);
            target -= nums[i];
            used[i] = false;
            temp.remove(temp.size()-1);
        }
    }
}
