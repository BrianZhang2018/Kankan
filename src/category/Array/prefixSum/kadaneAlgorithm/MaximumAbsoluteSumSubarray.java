package category.Array.prefixSum.kadaneAlgorithm;

/**
 * https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/
 *
 * prefixSum + kadane
 * Created by brianzhang on 8/13/21.
 */
public class MaximumAbsoluteSumSubarray {

    public static void main(String[] args) {
        System.out.println(maxAbsoluteSum(new int[]{2,-5,1,-4,3,-2}));
    }

    public static int maxAbsoluteSum(int[] nums) {
        int max = 0; // not Integer.MAX_VALUE as the Kadane algorithm ignore the negative value
        int min = 0; // same like above

        int prefixSum = 0;
        for(int i : nums) {
            prefixSum += i;
            min = Math.min(min, prefixSum);
            max = Math.max(max, prefixSum);
        }

        return Math.abs(max - min);
    }
}
