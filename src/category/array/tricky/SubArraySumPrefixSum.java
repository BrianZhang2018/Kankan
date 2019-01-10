package category.array.tricky;

import java.util.HashMap;

/**
 * PrefixSum
 * <p>
 * Created by brianzhang on 12/8/18.
 */
public class SubArraySumPrefixSum {

    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> presum = new HashMap();
        presum.put(0, 1);
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            res += presum.getOrDefault(sum - k, 0);
            presum.put(sum, presum.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
