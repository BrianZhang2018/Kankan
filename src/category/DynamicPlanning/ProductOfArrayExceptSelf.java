package category.DynamicPlanning;
/**
 * 
 * Similar question: https://leetcode.com/problems/trapping-rain-water/
 * 
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

}