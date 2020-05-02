package category.BacktracingDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * T: O(n*2^n)
 * S: O(n)
 * Created by brianzhang on 7/25/18.
 */
public class Subsets {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(subsets(nums));
    }
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        //'i' is used to go to next level
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            //the 'i+1' is used to go next number
            backtrack(list, tempList, nums, i + 1);
            //backTrace
            tempList.remove(tempList.size() - 1);
        }
    }
}
