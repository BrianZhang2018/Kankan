package category.MonotoneQueueStack.descrasing;

import java.util.*;
/**
 * https://leetcode.com/problems/next-greater-element-i/discuss/97595/java-10-lines-linear-time-complexity-on-with-explanation
 *
 * https://liuzhenglaichn.gitbook.io/algorithm/monotonic-stack
 * A monotonic stack is a stack whose elements are monotonically increasing or decreasing.
 * Sometimes we store the index of the elements in the stack and make sure the elements
 * corresponding to those indexes in the stack forms a mono-sequence.
 *
 * Key observation:
 * Suppose we have a decreasing sequence followed by a greater number:
 * For example [5, 4, 3, 2, 1, 6] then the greater number 6 is the next greater element for all previous numbers in the sequence
 *
 * We use a stack to keep a decreasing sub-sequence, whenever we see a number x greater than stack.peek()
 * we pop all elements less than x and for all the popped ones, their next greater element is x
 * For example [9, 8, 7, 3, 2, 1, 6]:
 * The "stack" will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is greater than 1, then we pop 1 2 3 whose next greater element should be 6
 *
 * LinkedIn
 */
public class NextGreaterElementI {
    public static void main(String[] args){
        System.out.println(Arrays.toString(nextGreaterElement1(new int[]{4, 1, 2}, new int[]{1,3,4,2})));
    }

    public static int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        Stack<Integer> desStack = new Stack<>();
        Map<Integer, Integer> nextGreaterElementMap = new HashMap<>();
        // leverage the decreasing stack algorithm to build a next greater element map, key(num) -> val(next greater element)
        for (int num : nums2) {
            while (!desStack.isEmpty() && num > desStack.peek()) {
                nextGreaterElementMap.put(desStack.pop(), num); // means the next greater element of stack.pop()'s element is "num"
            }
            desStack.push(num); // Monotone decrease stack
        }
        // retrieve the result from nextGreaterElementMap
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = nextGreaterElementMap.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }
}