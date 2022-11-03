package category.MonotoneQueueStack.descrasing;

import java.util.*;
/**
 * https://leetcode.com/problems/next-greater-element-i/discuss/97595/java-10-lines-linear-time-complexity-on-with-explanation
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a "subset of" nums2.
 *
 * Key observation:
 * Suppose we have a decreasing sequence followed by a greater number:
 * For example [5, 4, 3, 2, 1, 6] then the greater number 6 is the next greater element for all previous numbers in the sequence
 *
 * We use a stack to keep a decreasing sub-sequence, whenever we see a number x greater than stack.peek()
 * we pop all elements less than x and for all the popped ones, their next greater element is x
 * For example [9, 8, 7, 3, 2, 1, 6]
 * The "stack" will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is greater than 1 so we pop 1 2 3 whose next greater element should be 6
 *
 * LinkedIn
 */
public class NextGreaterElementI {
    public static void main(String[] args){
        System.out.println(Arrays.toString(nextGreaterElement1(new int[]{4, 1, 2}, new int[]{1,3,4,2})));
        //System.out.println(Arrays.toString(nextGreaterElement2(new int[]{4, 1, 2}, new int[]{1,3,4,2})));
    }

// in-sequence traversal (start from the left of array)
    public static int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums2) { // Monotone decrease stack
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        // above code get the next greater element for num2,
        // Since nums1 is subset of nums2, so just need get value from result map
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        
        return res;
    }

// reverse-sequence traversal (start from the end of array)
    public static int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums2.length;
        for (int i = n - 1; i >= 0; i--) {  //reverse-sequence
            while (!stack.isEmpty()) {
                if (nums2[i] < stack.peek()) {
                    map.put(nums2[i], stack.peek());
                    break;
                } else {
                    stack.pop();
                }
            }

            stack.push(nums2[i]);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }

        return res;
    }
}