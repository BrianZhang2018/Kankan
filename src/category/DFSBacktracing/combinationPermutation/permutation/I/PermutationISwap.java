package category.DFSBacktracing.combinationPermutation.permutation.I;

import java.util.*;

/**
 * https://www.youtube.com/watch?v=KBHFyg2AcZ4
 * DFS + BackTracing
 *
 * Created by brianzhang on 7/25/18.
 */
public class PermutationISwap {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        System.out.println(new PermutationISwap().permute(arr));
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        List<List<Integer>> result = new ArrayList();
        backTracing(nums, 0, result);
        return result;
    }

    /**
     * Don't need a tempList to store the result since we swap the number to get the different permutation
     */
    private void backTracing(int[] nums, int start, List<List<Integer>> result) {
        if (start == nums.length - 1)
            result.add(convertToIntegerArr(nums));
        else {
            //i is used to loop the all numbers to swap with the fixed number (nums[start])
            for (int i = start; i <= nums.length - 1; i++) {
                //swap the value
                int tmp = nums[start];
                nums[start] = nums[i];
                nums[i] = tmp;
                //start is used to go next number which is next swap number with others
                backTracing(nums, start + 1, result);
                //backTracing: return to last level
                tmp = nums[start];
                nums[start] = nums[i];
                nums[i] = tmp;
            }
        }
    }

    private List<Integer> convertToIntegerArr(int[] arr) {
        List<Integer> result = new ArrayList();
        for (int i : arr) {result.add(i);}
        return result;
    }
}
/*
    remove the last element, so you can iterate numbers in that position. For example, you need to get the permutations of [1,2,3,4,5].
    And you have already got [1,2,3] as tempList. then you add 4, and dfsHelper(list, [1,2,3,4], nums); you will get [1,2,3,4,5]
    then, you REMOVE LAST element of tempList, it will be [1,2,3], and the outer "for" will add 5 to tempList, then you will get [1,2,3,5,4] .
*/

