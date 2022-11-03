package category.TwoPointer;
/**
 * https://leetcode.com/problems/sort-colors/
 *
 * O(n) in-place solution
 * another solution is the counting sort in two pass
 */
public class SortColors {
    public static void main(String[] args) {
        int[] arr = new int[]{2,0,2,1,1,0};
        sortColors(arr);
        for(int i : arr)System.out.println(i);
    }

    public static void sortColors(int[] nums) {
        // lt is the pointer for less than `1`, on the left of `1`
        // gt is the pointer for great than `1`, on the right of `1`
        int lt=0, i=0, gt=nums.length-1;
        
        while(i<=gt){
            if(nums[i] < 1){
                swap(nums, lt++, i++);
            } else if(nums[i] > 1){
                swap(nums, i, gt--); // "i" don't `++` here as the swap value to the left can be `0` or `1`, if '0' will need swap again.
            } else {
                i++;
            }
        }
    }

    // tricky way to swap two numbers
    public static void swap(int[] nums, int i, int j){
        nums[i] = nums[j] + nums[i] - (nums[j] = nums[i]);
    }
}