package category.DynamicPlanning;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * Similar approach with the BestTimeToBuyAndSellStockIII problem.
 *
 * Can be solved with Monotone stack solution.
 * https://www.cnblogs.com/grandyang/p/4402392.html
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        TrappingRainWater test = new TrappingRainWater();
        System.out.println(test.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }

    public int trap(int[] height) {
        int sum = 0;
        if (height == null || height.length == 0)
            return 0;

        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        //got the left and right max height distribution
        maxLeft[0] = height[0];
        for (int i = 1; i < height.length - 1; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }
        System.out.println(Arrays.toString(maxLeft));

        maxRight[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i]);
        }
        System.out.println(Arrays.toString(maxRight));

        // Current bar can trap water = "The minimum height of (left-max and right-max height) - current height"
        for (int i = 0; i < height.length - 1; i++) {
            int min = Math.min(maxLeft[i], maxRight[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }
}