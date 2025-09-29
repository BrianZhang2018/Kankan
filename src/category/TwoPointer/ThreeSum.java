package category.TwoPointer;

import java.util.*;
/**
 * https://leetcode.com/problems/3sum
 * Sort the array, iterate through the list, and use another two-pointers loop search the combination
 * 
 * why not use binary search:
 * 1. We're looking for all triplets that sum to zero, not just one
 */
public class ThreeSum {
    public static void main(String[] args){
        System.out.println(threeSum(new int[]{-2,0,1,1,2}));
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            int low = i + 1;
            int high = nums.length - 1;
             // tow-pointer search, O(n)
            while(low < high){
                int currSum = nums[i] + nums[low] + nums[high];
                if (currSum == 0){
                    set.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    low++;
                    high--;
                } else if (currSum > 0){
                    high--; // not binary search
                } else {
                    low++;
                }
            }
        }

        return new ArrayList<>(set);
    }

}
