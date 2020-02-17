package category.DynamicPlanning.TrappingRainWaterSimilarProblems;
/**
 * https://leetcode.com/problems/product-of-array-except-self/s
 *
 * Similar question: https://leetcode.com/problems/trapping-rain-water/
 */
public class ProductOfArrayExceptSelf{

    public static void main(String[] args){
        ProductOfArrayExceptSelf test = new ProductOfArrayExceptSelf();
        test.productExceptSelf(new int[]{1,2,3,4});
    }

    public int[] productExceptSelf(int[] nums) {
        if(nums == null)
            return null;
        
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int[] product = new int[nums.length];
        
        left[0] = 1;
        right[nums.length-1]=1;
        
        for(int i=1; i<nums.length; i++){
            left[i] =left[i-1] * nums[i-1];
        }
        
        for(int i=nums.length-2; i >=0; i--){
            right[i] = right[i+1] * nums[i+1];
        }
        
        for(int i=0; i<nums.length; i++){
            product[i] = left[i] * right[i];
        }
        
        return product;
    }

    //count "0" solutions, https://www.1point3acres.com/bbs/thread-584451-1-1.html
    public int[] productExceptSelf2(int[] nums) {

        int product = 1;
        int zeros = 0;
        for (int num : nums) {
            if (num == 0) {
                zeros++;
            } else {
                product *= num;
            }
        }
        if (product == 0) {
            return nums;
        }
        int size = nums.length - 1;
        for(int i = 0; i < nums.length; i++) {
            if (zeros == 0) {
                nums[i] = product / nums[i];
            } else if (zeros == 1 && nums[i] == 0) {
                nums[i] = product;
            } else {
                nums[i] = 0;
            }
        }
        return nums;
    }

}