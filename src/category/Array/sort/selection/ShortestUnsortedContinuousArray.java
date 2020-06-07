package category.Array.sort.selection;

/**
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 * https://leetcode.com/articles/shortest-unsorted-continous-subarray/
 *
 * logic: get leftmost and rightmost boundary
 *
 * Created by brianzhang on 4/12/20.
 */
public class ShortestUnsortedContinuousArray {
    public static void main(String[] args) {
        System.out.println(findUnsortedSubArray(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }

    public static int findUnsortedSubArray(int[] nums) {

        int l = nums.length, r = 0;

        for(int i=0; i<nums.length -1; i++){
            for(int j = i+1; j< nums.length; j++){
                if(nums[j]<nums[i]){
                    r = Math.max(j, r);
                    l = Math.min(i, l);
                }
            }
        }

        return r-l<0 ? 0 : r - l + 1;
    }
}
