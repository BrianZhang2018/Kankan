package category.Array.slotSeparation;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/product-of-array-except-self/discuss/65638/My-simple-Java-solution
 * The idea is simple. We can divide each slot of result array to two parts: left and right.
 *    For example:
 *    the left of result[i] means the product of all elements with index less than i or 1
 *    the right of result[i] means the product of all elements with index larger than i or 1
 * so, we use two loops to calculate left and right. Then we store the result in the result array in the meanwhile.
 *
 * e.g.
 * nums   = [1, 2, 3, 4]
 * output = product of nums[left of i] * product of nums[right of i]
   output = [
        24, // left: init=1     nums[i]=1   right: 2 * 3 * 4
        12, // left: 1          nums[i]=2   right: 3 * 4
        8,  // left: 1 * 2      nums[i]=3   right: 4
        6,  // left: 1 * 2 * 3  nums[i]=4   right: init=1]

 * Created by brianzhang on 6/15/20.
 */
public class ProductExceptSelf {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{2,2,3})));
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0, tmp = 1; i < nums.length; i++) {
            result[i] = tmp;  // note here
            tmp *= nums[i];
        }
        for (int i = nums.length - 1, tmp = 1; i >= 0; i--) {
            result[i] *= tmp; // product left with right
            tmp *= nums[i];
        }
        return result;
    }
}
