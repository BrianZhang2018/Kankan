package category.Slidingwindow;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 * Created by brianzhang on 11/27/21.
 */
public class MinimumSizeSubArraySum {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println(0.7/10);
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int left=0;
        int res = Integer.MAX_VALUE;
        int sum = 0;
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            while(sum >= target) {
                res = Math.min(res, i-left+1);
                sum-=nums[left++];
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
