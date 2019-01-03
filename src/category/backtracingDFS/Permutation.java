package category.backtracingDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 最易懂的解法 of permutation
 * <p>
 * Created by brianzhang on 7/25/18.
 * <p>
 * https://www.youtube.com/watch?v=KBHFyg2AcZ4
 * <p>
 * DFS + BackTracing
 */

public class Permutation{

    public List<List<Integer>> permute(int[] nums) {

        if (nums == null || nums.length == 0)
            return null;

        List<List<Integer>> result = new ArrayList();
        backTracing(nums, 0, result);
        return result;
    }

    /**
     * Don't need involve another tempList to store the result, like other solution
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
        for (int i : arr) {
            result.add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};

        System.out.println(new Permutation().permute(arr));
    }
}
