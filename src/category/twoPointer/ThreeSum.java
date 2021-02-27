package category.twoPointer;

import java.util.*;

/**
 * Sort the array, iterate through the list, and use another two pointers to approach the target.
 * 
 * https://leetcode.com/problems/3sum/submissions/
 */
public class ThreeSum {

    public static void main(String[] args){
        System.out.println(threeSum(new int[]{-2,0,1,1,2}).size());

        HashSet<List<Integer>> set = new HashSet<>();
        set.add(Arrays.asList(1, 2, 3));
        set.add(Arrays.asList(1, 2, 3));
        System.out.println(set.size());
    }

    // Easy solution with HashSet to filter out the duplicate result, performance not best.
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        HashSet<List<Integer>> set = new HashSet<>();

        for(int i=0; i<nums.length; i++) {
            int low = i + 1;
            int high = nums.length - 1;

            while(low < high){
                if (nums[i] + nums[low] + nums[high] == 0){
                    set.add(Arrays.asList(nums[i], nums[low], nums[high])); // Important: will override the same list in hashSet since the Integer List compare "value" in equals method.
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
