package category.MonotoneQueueStack.descrasing;

import java.util.*;

/**
 * https://leetcode.com/problems/buildings-with-an-ocean-view/
 *
 * decreasing stack if you need pop the small element out from stack
 */
public class BuildingsWithOceanView {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findBuildings(new int[]{4,2,3,1})));
    }

    public static int[] findBuildings(int[] heights) {
        Stack<Integer> stack = new Stack(); // decreasing stack
        for(int i=0; i<heights.length; i++) {
            while(!stack.isEmpty() && heights[i] >= heights[stack.peek()]) {
                stack.pop();
            }
            stack.push(i);
        }

        int[] res = new int[stack.size()];
        int j = 0;
        for(int i : stack) { // this can get value from bottom to top in stack, so can't use stack.pop() here
            res[j++] = i;
        }
        return res;
    }
}
