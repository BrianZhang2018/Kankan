package category.BacktracingDFS.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations-ii/
 * Given a collection of numbers that might contain duplicates, return all possible "unique" permutations.
 *
 * Created by brianzhang on 7/29/18.
 */
public class PermutationII {

    public static void main(String[] args) {
        PermutationII test = new PermutationII();
        for(List<Integer> l : test.permuteUnique(new int[]{1,1,2})){
            System.out.println(Arrays.toString(l.toArray()));
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        backTracking(nums, temp, res, new boolean[nums.length]);
        return res;
    }

    public void backTracking(int nums[], List<Integer> temp, List<List<Integer>> res, boolean[] used) {
        if (nums.length == temp.size()) {
            res.add(new ArrayList<>(temp)); //deep copy
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i])
                    continue;
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) //makes sure when duplicates are selected, the order is ascending.
                    continue;
                used[i] = true;
                temp.add(nums[i]);
                backTracking(nums, temp, res, used);
                used[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }
    // The problem for Permutation II is that different orders of duplicates should only be considered as one permutation.
    // In other words, you should make sure that when these duplicates are selected, there has to be one fixed order.
    // if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
    // so used[i - 1] = false means "i-1" hasn't been used, so not an ascending order, we will skip it.
}