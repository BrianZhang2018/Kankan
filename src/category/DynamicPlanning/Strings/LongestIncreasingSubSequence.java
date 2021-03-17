package category.DynamicPlanning.Strings;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * 
 * Created by brianzhang on 9/7/18.
 */
public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lengthOfLISDP(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    // DP solution
    public static int lengthOfLISDP(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;

        for(int i=1; i<nums.length; i++){
            for(int j=0; j<i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] +1);
                }
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }

    // DP + binarySearch solution
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int len = 0;
        for (int i = 1; i < nums.length; i++) {
            int pos = binarySearch(dp, len, nums[i]);
            if (nums[i] < dp[pos])
                dp[pos] = nums[i];
            if (pos > len) {
                len = pos;
                dp[len] = nums[i];
            }
        }
        return len + 1;
    }

    private static int binarySearch(int[] dp, int len, int val) {
        int left = 0, right = len;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (dp[mid] == val) {
                return mid;
            }

            if (dp[mid] < val) {
                left = mid;
            }
            else {
                right = mid;
            }
        }

        if (dp[right] < val) return len + 1;
        else if (dp[left] >= val) return left;
        else return right;
    }

}
