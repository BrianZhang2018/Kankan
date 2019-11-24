package category.array.BitSet;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/first-missing-positive/
 */
public class FindMissingPositive{
    public static void main(String[] args){
        System.out.println(firstMissingPositive1(new int[]{2,1,0}));
    }

    public static int firstMissingPositive1(int[] nums) {
        if(nums == null || nums.length ==0){
            return 1;
        }

        Set<Integer> set = new HashSet<>();
        for(int n : nums){
            set.add(n);
        }
        for(int i=1; i<=nums.length; i++){
            if(!set.contains(i)) return i;
        }
        return nums.length+1;
    }

    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 1;

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }
}