package category.DynamicPlanning.TrappingRainWaterSimilarProblems;
/**
 * https://leetcode.com/problems/product-of-array-except-self/discuss/65632/My-solution-beats-100-java-solutions
 *
 * The product basically is calculated using the numbers before the current number and the numbers after the current number.
 * Thus, we can scan the array twice. First, we calculate the running product of the part before the current number.
 * Second, we calculate the running product of the part after the current number through scanning from the end of the array.
 *
 * Similar question: https://leetcode.com/problems/trapping-rain-water/
 */
public class ProductOfArrayExceptSelf{
    public static void main(String[] args){
       productExceptSelf(new int[]{1,2,3,4});
    }

    public static int[] productExceptSelf(int[] nums) {
        if(nums == null) return null;

        int[] left = new int[nums.length];  // Left is an array containing the left->right products
        int[] right = new int[nums.length]; // Right is an array containing the right->left products
        int[] res = new int[nums.length];

        int leftProductPrefix = 1;
        for(int i=0; i<nums.length; i++){
            left[i] = leftProductPrefix;
            leftProductPrefix *= nums[i];
        }

        int rightProductPrefix = 1;
        for(int i=nums.length-1; i>=0; i--){
            right[i] = rightProductPrefix;
            rightProductPrefix *= nums[i];
        }

        for(int i=0; i<nums.length; i++){
            res[i] = left[i] * right[i];
        }

        return res;
    }

}