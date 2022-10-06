package category.MonotoneQueueStack.descrasing;

import java.util.Stack;

/**
 * https://leetcode.com/problems/buildings-with-an-ocean-view/
 */
public class BuildingsWithOceanView {

    public int[] findBuildings(int[] heights) {
        Stack<Integer> stack = new Stack(); // decreasing stack
        for(int i=0; i<heights.length; i++) {
            int h = heights[i];
            while(!stack.isEmpty() && h >= heights[stack.peek()]) {
                stack.pop();
            }
            stack.push(i);
        }

        int[] res = new int[stack.size()];
        int j = 0;
        for(int i : stack) {
            res[j++] = i;
        }
        return res;
    }
}
