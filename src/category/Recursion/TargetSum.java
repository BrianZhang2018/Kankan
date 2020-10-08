package category.Recursion;

/**
 * https://leetcode.com/problems/target-sum/
 * Time: O(2^n), Space: O(n^2)
 *
 * Created by brianzhang on 2/23/20.
 */
public class TargetSum {

    public static void main(String[] args) {
        TargetSum ts = new TargetSum();
        System.out.println(ts.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }

    private int count =0;

    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) return 0;

        helper(nums, 0, 0, S);
        return count;
    }

    public void helper(int[] nums, int index, int sum, int target){
        if(index == nums.length){
            if(sum == target){
                count++;
            }
            return;
        }

        helper(nums, index+1, sum+nums[index], target);
        helper(nums, index+1, sum-nums[index], target);
    }
}
