package category.MonotoneQueueStack.increasing;

import java.util.Stack;
/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * 
 * Monotone stack solution
 * DP solution is fast and same with TrappingRainWater
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2,1,2}));
        System.out.println(largestRectangleAreaDP(new int[]{4, 5, 6, 2, 3}));
    }

    // Solution-1: Monotone stack solution, maintain an increasing stack
    public static int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) return 0;
    
        Stack<Integer> stack = new Stack<>();
        int len = heights.length;
        int maxRectangle = 0;
        for (int i = 0; i <= len; i++) {
            // add "0" height to the end of array so that the last histogram bar can be included into calculation
            int curHeight = (i == len ? 0 : heights[i]);
            // (height < heights[stack.peek()]) is the "trigger" to calculate the rectangle area
            while(!stack.isEmpty() && curHeight < heights[stack.peek()]){
                int height = heights[stack.pop()];
                int prevIndex = stack.isEmpty() ? -1 : stack.peek();
                // "i-prevIndex-1" is the width of histogram, start from 1
                maxRectangle = Math.max(maxRectangle, height * (i - prevIndex - 1));
            }
            // store the index rather than the value - (monotone stack feature, but not always)
            stack.push(i);
        }
        
        return maxRectangle;
     }

    // another way to write the monotone stack solution - one for loop
    public int largestRectangleArea1(int[] heights) {
        int len = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= len;) {
            int h = (i == len ? 0 : heights[i]);
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            }else {
                int curHeight = heights[stack.pop()];
                int rightBoundary = i - 1;
                int leftBoundary = stack.isEmpty() ? 0 : stack.peek() + 1;
                int width = rightBoundary - leftBoundary + 1;
                maxArea = Math.max(maxArea, (curHeight * width));
            }
        }
        return maxArea;
    }

    // Solution-2: DP solution which is the same with TrappingRainWater question
    // For any bar i the maximum rectangle is of width r - l - 1 where "r" is the last coordinate of the bar to the right with height h[r] >= h[i]
    // and "l" is the last coordinate of the bar to the left which height h[l] >= h[i]
    public static int largestRectangleAreaDP(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        int len = heights.length;
        int[] left = new int[len];
        // find the left-most greater number for each element
        for (int i = 0; i < len; i++) {
            int p = i;
            while (p > 0 && heights[p - 1] >= heights[i]) {
                p = left[--p];
            }
            left[i] = p;
        }
        // find the right-most greater number for each element
        int[] right = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            int p = i;
            while (p < len - 1 && heights[p + 1] >= heights[i]) {
                p = right[++p];
            }
            right[i] = p;
        }
        
        int max = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, (right[i] - left[i] + 1) * heights[i]);
        }
        return max;
    }

}