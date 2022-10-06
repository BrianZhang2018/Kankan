package category.MonotoneQueueStack.descrasing;

import java.util.Stack;

/**
 * https://leetcode.com/problems/trapping-rain-water/discuss/244167/A-general-stack-approach-you-can-solve-42-84-85
 * https://www.cnblogs.com/grandyang/p/4402392.html
 *
 * Grubhub onsite
 */
public class TrappingRainWaterMonotonicStack {
    public static void main(String[] args) {
        System.out.println(trap(new int[]{2,1,0,1,2}));
    }

    public static int trap(int[] height) {
        int cumulativeWater = 0;
        // decreasing stack that hold the index of bar
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            int currHeight = height[i];
            while (!stack.isEmpty() && currHeight > height[stack.peek()]) { // template
                int valley = stack.pop();
                int start = stack.isEmpty() ? i - 1 : stack.peek();
                int curWater = (Math.min(height[start], currHeight) - height[valley]) * (i - start - 1);
                cumulativeWater += curWater;
            }
            stack.push(i);
        }
        return cumulativeWater;
    }
}