package category.twoPointer;

import java.util.*;

/**
 * https://leetcode.com/problems/3sum
 * Sort the array, iterate through the list, and use another two pointers to approach the target.
 *
 * O(n^2)
 */
public class ThreeSum {
    public static void main(String[] args){
        System.out.println(threeSum(new int[]{-2,0,1,1,2}));
    }

    // Easy solution with HashSet to filter out the duplicate result, performance not best.
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();

        for(int i=0; i<nums.length; i++) {
            int low = i + 1;
            int high = nums.length - 1;
            // binary search
            while(low < high){
                if (nums[i] + nums[low] + nums[high] == 0){ // Catch
                    set.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    low++;
                    high--;
                } else if (nums[i] + nums[low] + nums[high] > 0){
                    high--;
                } else {
                    low++;
                }
            }
        }

        return new ArrayList<>(set);
    }

}
