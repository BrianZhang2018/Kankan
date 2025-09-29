package category.Array.slotSeparation;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/product-of-array-except-self/discuss/65638/My-simple-Java-solution
 * The idea is simple. We can divide array into two parts separated by the current positon, left <- curr -> right.
 * We use two loops to calculate, first calcualte the left, then caluclate right and meaanwhile multiple the left prodcuted value.
 *
 * e.g.
 * nums   = [1, 2, 3, 4]
 * output = product of nums[left of i] * product of nums[right of i]
   output = [
        24, left: init=1     nums[i]=1   right: 2 * 3 * 4
        12, left: 1          nums[i]=2   right: 3 * 4
        8,  left: 1 * 2      nums[i]=3   right: 4
        6,  left: 1 * 2 * 3  nums[i]=4   right: init=1]

 * Created by brianzhang on 6/15/20.
 */
public class ProductExceptSelf {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{2,2,3})));
    }
    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        // calculate left product
        for (int i = 0, tmp = 1; i < nums.length; i++) {
            result[i] = tmp;
            tmp *= nums[i];
        }
        for (int i = nums.length - 1, tmp = 1; i >= 0; i--) {
            result[i] *= tmp; // product left producted value with right producted value
            tmp *= nums[i];
        }
        return result;
    }
}
