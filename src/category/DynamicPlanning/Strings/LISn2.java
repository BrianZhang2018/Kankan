package category.DynamicPlanning.Strings;

import java.util.Arrays;

/**
 * 递推的求解方法, 从subset推出答案
 * 1 -> 1
 * 1,3 -> 2
 * ...
 * 1, 3, 6, 7, 9 -> LIS = 5
 * 1, 3, 6, 7, 9, 4 -> 相对于 4，前面3<4, so LIS(4) = LIS(3) + 1 = 3
 *
 * Created by brianzhang on 12/31/18.
 */
public class LISn2 {

    public static void main(String[] args) {
        int[] input = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(lengthOfLIS(input));
    }

    public static int lengthOfLIS(int[] nums) {

        if (nums.length == 0)
            return 0;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return dp[nums.length - 1];
    }
}
