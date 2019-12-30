package category.MonotoneStack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * solution: https://leetcode.com/problems/trapping-rain-water/discuss/244167/A-general-stack-approach-you-can-solve-42-84-85
 *
 * Similar with the BestTimeToBuyAndSellStockIII
 * https://www.cnblogs.com/grandyang/p/4402392.html
 */
public class TrappingRainWater{

    public static void main(String[] args) {
        TrappingRainWater test = new TrappingRainWater();
        System.out.println(test.trap(new int[]{2,1,0,1,2}));
    }

    public int trap(int[] height) {
        int cumulativeWater = 0;
        Stack<Integer> stack = new Stack<>(); //decreasing stack that hold the index of bar
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            while (!stack.isEmpty() && h > height[stack.peek()]) {
                int valley = stack.pop();
                int start = stack.isEmpty()? i-1: stack.peek();
                int curWater = (Math.min(height[start], h) - height[valley]) * (i - start - 1);
                cumulativeWater += curWater;
            }
            stack.push(i);
        }
        return cumulativeWater;
    }
}