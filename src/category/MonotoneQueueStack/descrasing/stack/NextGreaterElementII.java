package category.MonotoneQueueStack.descrasing.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/next-greater-element-ii/
 *
 * keep the indexes of the decreasing subsequence value
 *
 * Created by brianzhang on 5/12/20.
 */
public class NextGreaterElementII {

    public static void main(String[] args) {

    }

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>(); // keep the indexes of the decreasing subsequence
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                next[stack.pop()] = num;
            if (i < n) stack.push(i);
        }
        return next;
    }
}
