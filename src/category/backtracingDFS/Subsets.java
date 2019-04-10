package category.backtracingDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brianzhang on 7/25/18.
 */
public class Subsets {
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
            //the forth parameter is used to go next number
            backtrack(list, tempList, nums, i + 1);
            //backTrace
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(subsets(nums));
    }
}

/*
    remove the last element, so you can iterate numbers in that position. For example, you need to get the permutations of [1,2,3,4,5].
    And you have already got [1,2,3] as tempList. then you add 4, and backtrack(list, [1,2,3,4], nums); you will get [1,2,3,4,5]
    then, you REMOVE LAST element of tempList, it will be [1,2,3], and the outer "for" will add 5 to tempList, then you will get [1,2,3,5,4] .
*/
