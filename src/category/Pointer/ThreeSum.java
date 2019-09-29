package category.Pointer;

import java.util.*;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * Sort the array, iterate through the list, and use another two pointers to approach the target.
 * 
 * https://leetcode.com/problems/3sum/submissions/
 */
public class ThreeSum{

    public static void main(String[] args){
        ThreeSum test = new ThreeSum();
        test.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList();
        for(int i=0; i<nums.length; i++){
            if(i==0 || (i>0 && nums[i] != nums[i-1])){
                int low = i+1;
                int high = nums.length-1;
                int sum = 0-nums[i];
                while(low < high){
                    if(nums[low] + nums[high] == sum){
                        res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while(low<high && nums[low]==nums[low+1])
                            low++;
                        while(low<high && nums[high]==nums[high-1])
                            high--;
                        
                        low++; high--;
                    } else if(nums[low] + nums[high]>sum){
                        high--;
                    } else{
                        low++;
                    }
                }
            }
        }
        return res;
    }

    //Easy solution with HashSet to fliter out the duplicate result, performance not best.
    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        HashSet<List<Integer>> set = new HashSet<>();
        
        for(int i=0; i<nums.length; i++){
                int low = i+1;
                int high = nums.length-1;
                int sum = 0-nums[i];
                while(low < high){
                    if(nums[low] + nums[high] == sum){
                        set.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        
                        low++; high--;
                    } else if (nums[low] + nums[high]>sum){
                        high--;
                    } else {
                        low++;
                    }
                }
        }
       
        return new ArrayList<List<Integer>>(set);
    }
}
