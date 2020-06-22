package category.Array.slotSeparation;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * Created by brianzhang on 6/14/20.
 */
public class MaximumProductSubArray {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,3,-2,4}));
        System.out.println(maxProduct(new int[]{-2,0,-1}));
    }

    // Slot separation solution - similar with ProductExceptSelf.java
    public static int maxProduct(int[] nums) {
        int l = 0, r=0; int max = Integer.MIN_VALUE;
        for(int i=0; i< nums.length; i++){
            l = (l==0? 1 : l) * nums[i]; // from left to right: 1-5
            r = (r==0? 1 : r) * nums[nums.length-i-1]; //from right to left which solve the product of subArray, e.g 5-3 (3-5)
            max = Math.max(max, Math.max(r, l));
        }
        return max;
    }


    //My DP solution - time exceed limit
    public static int maxProductDP(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;

        if(nums.length == 1)
            return nums[0];

        int max = Integer.MIN_VALUE;
        int[][] dp = new int[nums.length][nums.length];

        for(int i=0; i<nums.length; i++){
            dp[i][i] = nums[i];
            max = Math.max(dp[i][i], max);
            for(int j=i+1; j<nums.length; j++){
                dp[i][j] = dp[i][j-1] * nums[j]; // dp calculate all the scenarios
                max=Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

}
