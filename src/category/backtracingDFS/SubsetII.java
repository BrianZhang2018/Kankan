package category.backtracingDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brianzhang on 7/29/18.
 */
public class SubsetII {

    public static void main(String[] args) {
        SubsetII subsetII = new SubsetII();
        int[] test = {1, 2, 3};
        System.out.println(subsetII.subsetsWithDup(test));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> res = new ArrayList();
        //sort the nums so that the nums[i] == nums[i - 1] can be used to check the duplicate number
        Arrays.sort(nums);
        backTrack(nums, 0, res, new ArrayList<Integer>());
        return res;
    }

    private void backTrack(int[] nums, int start, List<List<Integer>> res, List<Integer> tmpList) {
        res.add(new ArrayList<Integer>(tmpList));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1])
                continue;

            tmpList.add(nums[i]);
            backTrack(nums, i + 1, res, tmpList);
            tmpList.remove(tmpList.size() - 1);
        }
    }
}
