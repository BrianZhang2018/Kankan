package category.MonotoneStack;

import java.util.Stack;
/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * 
 * Monotone stack solution
 * DP solution same with TrappingRainWater, and this is fast
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args){
       //System.out.println(largestRectangleArea4(new int[]{1,2,3,4,5,6}));
       System.out.println(largestRectangleArea4(new int[]{2,1,5,6,2,3}));
    }

    // Pruning to find peak. Best solution for this problem
    public static int largestRectangleArea4(int[] heights) {
        int n = heights.length;
        if (n == 0) return 0;
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            //find the peak position
            if (i + 1 < n  && heights[i] <= heights[i + 1])
                continue;

            int minVal = heights[i];

            // j starts from the peek (i), then "look back" each unit to find the largest rectangle
            for (int j = i; j >= 0; j--) {
                minVal = Math.min(minVal, heights[j]);
                res = Math.max(res, (i - j + 1) * minVal);
            }
        }
        
        return res;
    }

    //Monotone stack solution, create an increase stack
    public static int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0)
        return 0;
    
        Stack<Integer> s = new Stack<>();
        int len = heights.length;
        int maxArea = 0;
        
        for(int i=0; i<=len; i++){
            //add 0 height to the end of array so that the last bucket bar can be calculated by itself
            int h = (i == len ? 0 : heights[i]);
            
            // (height < the top value of stack) is the trigger to calculate the area
            while(!s.isEmpty() && h < heights[s.peek()]){
                int currHeight = heights[s.pop()];
                int prevIndex = s.isEmpty() ? -1 : s.peek();
                maxArea = Math.max(maxArea, currHeight * (i - prevIndex - 1)); //i - prevIndex -1 = width
            }
            //store the index rather than the value into the stack - (monotone stack feature, but not always)
            s.push(i);
        }
        
        return maxArea;
     }

    //my modified version
    public int largestRectangleArea11(int[] heights) {
        if(heights == null || heights.length == 0)
            return 0;
        
        Stack<Integer> s = new Stack<>();
        int len = heights.length;
        int maxArea = 0;
        
        for(int i=0; i<=len;){
            //add 0 height to the end of array so that the last bucket can be calculated by itself
            int h = (i == len ? 0 : heights[i]);
            //create a increase stack
            if(s.isEmpty() || h >= heights[s.peek()]){
                //store the index rather than the value into the stack
                s.push(i);
                i++;
            }else{
                // "h" < the top value is the trigger to calculate the area
                while(!s.isEmpty() && h < heights[s.peek()]){
                  int currHeight = heights[s.pop()];
                  int prevIndex = s.isEmpty() ? -1 : s.peek();
                  maxArea = Math.max(maxArea, currHeight * (i - prevIndex - 1));
                }
            }
        }
        
        return maxArea;
    }

    //DP solution which is the same with TrappingRainWater question
    public int largestRectangleArea3(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        int len = heights.length;
        // towards-left-most which is larger than current
        int[] left = new int[len];
        for (int i = 0; i < len; i++) {
            int p = i;
            while (p > 0 && heights[p - 1] >= heights[i]) {
                p = left[--p];
            }
            left[i] = p;
        }

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