package category.DFSBacktracing.combination.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets-ii/
 *
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Created by brianzhang on 7/29/18.
 */
public class SubsetsII {
    public static void main(String[] args) {
        SubsetsII subsetsII = new SubsetsII();
        int[] test = {1, 2, 2};
        System.out.println(subsetsII.subsetsWithoutDup(test));
    }

    public List<List<Integer>> subsetsWithoutDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //sort the nums so that the nums[i] == nums[i - 1] can be used to check the duplicate number
        Arrays.sort(nums);
        backTrack(nums, 0, res, new ArrayList<>());
        return res;
    }

    private void backTrack(int[] nums, int start, List<List<Integer>> res, List<Integer> temp) {
        res.add(new ArrayList<>(temp));
        System.out.println(Arrays.toString(temp.toArray()));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            backTrack(nums, i + 1, res, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
