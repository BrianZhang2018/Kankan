package category.javabasic;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-triangle-number/
 *
 * Created by brianzhang on 2/4/21.
 */
public class ValidTriangleNumber {

    public static void main(String[] args) {
        System.out.println(triangleNumber(new int[]{2,2,3,4}));
    }

    public static int triangleNumber(int[] nums) {
        int res = 0;
        int n = nums.length;
        Arrays.sort(nums);

        for(int i= n-1; i>=2; i--){
            int left = 0, right = i-1;
            while(left < right){
                if(nums[left] + nums[right] > nums[i]){
                    res += right - left;
                    right--;
                }else{
                    left++;
                }
            }
        }

        return res;
    }
}
