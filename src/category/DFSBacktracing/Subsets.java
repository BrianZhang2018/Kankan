package category.DFSBacktracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Subsets: 2^N , since each element could be absent or present.
 *
 * So, if N numbers, the T: O(n*2^n)
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

        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            //the 'i+1' is used to go next number
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}