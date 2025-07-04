package category.DynamicPlanning.easyDP;

/**
 * https://leetcode.com/problems/maximum-subarray/
 *
 * Easy
 * Can be solved using Kadane's algorithm in linear time and without using additional space. The main ideas are:
     - Use the input vector nums to store the candidate subarrays sum (i.e. the greatest contiguous sum so far).
     - "Ignore cumulative negatives", as they don't contribute positively to the sum.

 Example:
 Given nums = [-2, 1, -3, 4].
 Compare all elements with the cumulative sum stored in the previous index.

     1. Since -2 < 0, value -2 doesn't contribute to the sum. Thus, ignore it and proceed to the next index.
     2. Since 1 > 0, value 1 does contribute. Hence, compute -3+1 = -2 and store it in index 2.
     3. The result vector is so far: [-2, 1, -2, 4]. Last element to evaluate is 4.
     4. Since -2 < 0, -2 does not contribute positively to the sum. Thus, ignore it.
     5. Having checked all elements, the final result vector is: [-2, 1, -2, 4].
     6. The maximum subarray is max(num)=4.

 * Created by brianzhang on 6/17/20.
 */
public class MaximumSubArray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    // DP
    public static int maxSubArrayDP(int[] nums) {
        int dp = nums[0];
        int max = nums[0];

        for(int i=1; i<nums.length; i++){
            dp = Math.max(dp + nums[i], nums[i]);
            max = Math.max(dp, max);
        }

        return max;
    }

    // Kadane's algorithm
    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        for(int i=1; i<nums.length; i++){
            if(nums[i-1] > 0){
                nums[i] = nums[i-1] + nums[i];
            }
            max = Math.max(nums[i], max);
        }
        return max;
    }
}
