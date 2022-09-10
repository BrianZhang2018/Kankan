package category.MonotoneQueueStack.descrasing;

import java.util.*;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * Created by brianzhang on 4/11/20.
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3)));
    }

    // Monotonic Queue
    public static int[] maxSlidingWindow(int[] arr, int k) {
        int[] res = new int[arr.length - k + 1];

        Deque<Integer> indexDeque = new ArrayDeque<>(); // store index
        for (int i = 0; i < arr.length; i++) {
            // remove the first element from queue as it's already out of the window
            if (!indexDeque.isEmpty() && indexDeque.peek() == i - k) {
                indexDeque.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!indexDeque.isEmpty() && arr[i] > arr[indexDeque.peekLast()]) {
                indexDeque.pollLast();
            }
            indexDeque.offer(i);
            if (i >= k - 1) {
                res[i - k + 1] = arr[indexDeque.peekFirst()];
            }
        }
        return res;
    }

    // DP: similar with TrippedWater
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int [] left = new int[n];
        left[0] = nums[0];
        int [] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }
}
