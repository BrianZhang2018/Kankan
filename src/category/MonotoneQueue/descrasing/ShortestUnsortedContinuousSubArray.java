package category.MonotoneQueue.descrasing;

import java.util.Stack;

/**
 * https://leetcode.com/articles/shortest-unsorted-continous-subarray/
 *
 * Created by brianzhang on 4/12/20.
 */
public class ShortestUnsortedContinuousSubArray {

    public static void main(String[] args) {
        System.out.println(findUnsortedSubArray(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }

    public static int findUnsortedSubArray(int[] nums) {
        Stack< Integer > stack = new Stack <> ();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }
}
