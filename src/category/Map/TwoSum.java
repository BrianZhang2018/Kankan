package category.Map;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap solution
 * 
 * Created by brianzhang on 7/22/18.
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] input = {3, 3, 4};
        int[] result = twoSum(input, 6);
        System.out.println(result[0] + ", " + result[1]);

    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int index=0; index<nums.length; index++){
            if(map.containsKey(target - nums[index])){
                return new int[]{ map.get(target-nums[index]), index};
            }else{
                map.put(nums[index], index);
            }
        }

        return new int[]{};
    }
}