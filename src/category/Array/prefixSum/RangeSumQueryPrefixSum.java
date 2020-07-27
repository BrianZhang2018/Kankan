package category.Array.prefixSum;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/
 *
 * Created by brianzhang on 12/29/18.
 */
public class RangeSumQueryPrefixSum {

    public static void main(String[] args) {
        RangeSumQueryPrefixSum test = new RangeSumQueryPrefixSum(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(test.sumRange(0,2));
    }

    private int[] sums;

    public RangeSumQueryPrefixSum(int[] nums) {

        if (nums == null || nums.length == 0) return;
        for (int i = 1; i < nums.length; i++)
            nums[i] += nums[i - 1];

        this.sums = nums;
    }

    public int sumRange(int i, int j) {
        if (i == 0) return sums[j];
        return sums[j] - sums[i - 1];
    }
}
