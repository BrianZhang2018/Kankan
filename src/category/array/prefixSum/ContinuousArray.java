package category.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/contiguous-array/
 *
 * prefixsum + hashmap
 *
 * The idea is to change 0 in the original array to -1. then, the SUM[i, j] == 0
 * Also put the sum to index mapping to a HashMap to make search faster.
 * (the subarray between two same prefixsum on index (stat+1, end) is an answer)
 *
 * Created by brianzhang on 4/18/20.
 */
public class ContinuousArray {

    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{0,1,0,0,1,1,0}));
    }

    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxLen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                System.out.println(count);
                maxLen = Math.max(maxLen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxLen;
    }
}
