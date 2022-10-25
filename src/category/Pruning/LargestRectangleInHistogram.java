package category.Pruning;

import java.math.BigInteger;
import java.util.*;
/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * Solutions:
 * 1. Pruning
 * 2. Monotone stack
 * 3. DP solution which is the similar with TrappingRainWater, and this is fast
 */
public class LargestRectangleInHistogram{
    public static void main(String[] args){
        System.out.println(largestRectangleAreaPruning(new int[]{1,2,3,4,5,6}));
        //System.out.println(largestRectangleAreaDP(new int[]{2,1,5,6,2,3}));

        BigInteger bi = new BigInteger("11");
        System.out.println(bi.multiply(new BigInteger("11")));
    }

     // Pruning - timeout now
     // Find each peak bar, and then reversely walk to get the largest rectangle
    public static int largestRectangleAreaPruning(int[] heights) {
        int n = heights.length;
        if (n == 0) return 0;
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            // Pruning step, find the peak bar
            if (i + 1 < n  && heights[i] <= heights[i + 1])
                continue;
            
            int minVal = heights[i];

            // j starts from the peak, and reversely walk to get largest rectangle
            for (int j = i; j >= 0; j--) {
                minVal = Math.min(minVal, heights[j]);
                res = Math.max(res, (i - j + 1) * minVal);
            }
        }
        
        return res;
    }

    // DP solution which is the similar with TrappingRainWater question
    public static int largestRectangleAreaDP(int[] heights) {
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
        System.out.println(Arrays.toString(left));

        int[] right = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            int p = i;
            while (p < len - 1 && heights[p + 1] >= heights[i]) {
                p = right[++p];
            }
            right[i] = p;
        }
        System.out.println(Arrays.toString(right));

        int max = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, (right[i] - left[i] + 1) * heights[i]);
        }
        return max;
    }

    // Monotone stack solution, create an increase stack
    public static int largestRectangleAreaMonotone(int[] heights) {
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
            //create a "increase stack", store the index rather than the value into the stack - (monotone stack feature, but not always)
            s.push(i);
        }
        
        return maxArea;
     }

    //my modified version
    public int largestRectangleArea2(int[] heights) {
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

}