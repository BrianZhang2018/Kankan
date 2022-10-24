package category.DFSBacktracing.combinationAndPermutation.permutation.II;

import java.util.*;

/**
 * https://leetcode.com/problems/permutations-ii/
 * contain duplicate number
 *
 *  The problem for Permutation II is that different orders of duplicates should only be considered as one permutation.
 *  In other words, you should make sure that when these duplicates are selected, there has to be "one fixed order".
 *  if (i > 0 && nums[i] == nums[i-1] && !used[i - 1]), the used[i - 1] = false means "i-1" hasn't been used,
 *  so not an ascending order, we will skip it.
 *
 * need use the boolean used[] since duplicate that you can't use the temp.contains(key) to determine used or not
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
        Arrays.sort(nums); // 剪枝
        backTracking(nums, new ArrayList<>(), res, new boolean[nums.length]);
        return res;
    }
    public void backTracking(int nums[], List<Integer> temp, List<List<Integer>> res, boolean[] used) {
        if (nums.length == temp.size()) {
            res.add(new ArrayList<>(temp)); // deep copy
        } else {
            for (int index = 0; index < nums.length; index++) {
                // used[] to identify the index used or not. the temp.contains(nums[i]) can only identify number whether used, but it contains duplicate number, so can't use.
                if (used[index]) continue;
                // makes sure when duplicates are selected, the order is ascending
                if (index > 0 && nums[index] == nums[index - 1] && !used[index - 1]) continue;

                used[index] = true;
                temp.add(nums[index]);
                backTracking(nums, temp, res, used);
                used[index] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }
}