package category.BacktracingDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brianzhang on 7/29/18.
 */
public class PermutationII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> temp = new ArrayList();
        Arrays.sort(nums);
        backTrace(nums, temp, res, new boolean[nums.length]);
        return res;
    }

    public void backTrace(int nums[], List<Integer> temp, List<List<Integer>> res, boolean[] used) {
        if (nums.length == temp.size()) {
            //deep copy
            res.add(new ArrayList<Integer>(temp));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i])
                    continue;
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) //!used[i - 1]: check previous number whether has been used
                    continue;
                used[i] = true;
                temp.add(nums[i]);
                backTrace(nums, temp, res, used);
                used[i] = false; //
                temp.remove(temp.size() - 1);
            }
        }
    }

    //We sorted the array
    //The problem for Permutation II is that different orders of duplicates should only be considered as one permutation.
    // In other words, you should make sure that when these duplicates are selected, there has to be one fixed order.
}