package category.MonotoneStack;

import java.util.Stack;

/**
 * Try to solve it as Monotone stack solution
 * https://www.cnblogs.com/grandyang/p/4402392.html
 */
public class TrappingRainWater{

    public int trap(int[] height) {
        int sum = 0;
        if (height == null || height.length == 0)
            return 0;

        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        //got the left and right height distribution
        maxLeft[0] = height[0];
        for (int i = 1; i < height.length - 1; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }

        maxRight[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i]);
        }
        
        //calculate tripping the water base on the above left right height distribution
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(maxLeft[i], maxRight[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }
}