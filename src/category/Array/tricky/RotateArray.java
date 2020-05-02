package category.Array.tricky;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/rotate-array/
 *
 * Created by brianzhang on 11/12/18.
 */
public class RotateArray {

    public static void main(String[] args) {

        int[] nums = {1,2,3,4,5,6,7};
        RotateArray ra = new RotateArray();
        ra.rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
    public void rotate(int[] nums, int k) {

        if (nums == null || nums.length < 2 || k == 0)
            return;

        k %= nums.length;

        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] nums, int s, int e) {

        int temp = 0;
        while (s < e) {

            temp = nums[s];
            nums[s] = nums[e];
            nums[e] = temp;

            s++;
            e--;
        }
    }
}
