package category.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/next-greater-element-ii/
 *
 * Note: Use % to solve the circular array issue
 *
 * Created by brianzhang on 11/24/19.
 */
public class NextGreaterElementII {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1,2,1})));
    }

    public static int[] nextGreaterElements(int[] nums) {
        if(nums == null || nums.length ==0)
            return nums;

        int[] res = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            res[i] = -1;
            for(int j=1; j<nums.length; j++){
                if(nums[(i+j) % nums.length] > nums[i]){
                    res[i] = nums[(i+j) % nums.length];
                    break;
                }
            }
        }

        return res;
    }
}
