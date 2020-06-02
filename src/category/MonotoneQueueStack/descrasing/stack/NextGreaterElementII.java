package category.MonotoneQueueStack.descrasing.stack;

import java.util.*;

/**
 * https://leetcode.com/problems/next-greater-element-ii/
 * Keep the indexes of the decreasing subsequence value
 *
 * Created by brianzhang on 5/12/20.
 */
public class NextGreaterElementII {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1,2,1})));
    }

    // monotone stack solution
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);

        Stack<Integer> descStack = new Stack<>(); // keep the indexes of the decreasing subsequence
        for (int i = 0; i < n * 2; i++) {  // circular array, so valid index range will be 0-5
            int num = nums[i % n];

            while (!descStack.isEmpty() && nums[descStack.peek()] < num)
                next[descStack.pop()] = num;

            if (i < n)
                descStack.push(i);
        }
        return next;
    }

    public int[] nextGreaterElementsArraySolution(int[] nums) {
        if(nums == null || nums.length ==0)
            return nums;

        int[] res = new int[nums.length];
        //Arrays.fill(res, -1);
        for(int i=0; i<nums.length; i++){
            res[i] = -1;
            for(int j=1; j<nums.length; j++){
                if(nums[(i+j) % nums.length] > nums[i]){
                    res[i] = nums[(i+j)%nums.length];
                    break;
                }
            }
        }

        return res;
    }
}
