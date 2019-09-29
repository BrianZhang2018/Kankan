package category.easy;

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
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }

}