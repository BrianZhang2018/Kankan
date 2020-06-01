package category.Sort;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/wiggle-sort-ii/
 */
public class WiggleSortII {
    public static void main(String[] args){
        int[] matrix = new int[]{1, 5, 1, 1, 6, 4};
        WiggleSortII test = new WiggleSortII();
        test.wiggleSort(matrix);
        System.out.println(Arrays.toString(matrix));
    }

    public void wiggleSort(int[] nums) {
        if(nums == null || nums.length ==0)
            return;
        
        Arrays.sort(nums);
        int[] temp = new int[nums.length];
        int mid = nums.length%2 == 0 ? nums.length/2 -1: nums.length/2;
        int index = 0;
        
        for(int i=0; i<=mid; i++){
            //"mid-i" rather than "i" is for avoiding the two mid number is equal
            // e.g. 1,1,2,2,3,3
            temp[index] = nums[mid - i];
            if(index+1<nums.length){
                temp[index+1] = nums[nums.length - i -1];
            }
            index = index +2;
        }
        
        for(int i=0;i<nums.length;i++){
            nums[i] = temp[i];
        }
        
    }
}