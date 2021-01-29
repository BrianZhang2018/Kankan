package category.BacktracingDFS.parititonToKEqualSum;

/**
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 *
 * TC: O(k * 2^n) Because it takes the inner recursion 2^n time to find a good subset.
 * Once the 1st subset is found, we go on to find the second, which would take 2^n roughly
 * (because some numbers have been marked as visited). So T = 2^n + 2^n + 2^n + ... = k * 2^n.
 *
 * Created by brianzhang on 1/25/21.
 */
public class PartitionToKEqualSumSubsets {

    public static void main(String[] args) {
        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums.length < k) return false;

        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % k != 0) return false;

        return canPartition(nums, new boolean[nums.length], 0, k, 0, sum / k);
    }

    private static boolean canPartition(int[] nums, boolean[] used, int start, int k, int curSum, int subSum) {
        if (k == 1) return true;
        if (curSum > subSum) return false;

        if (curSum == subSum) return canPartition(nums, used, 0, k - 1, 0, subSum);

        for (int i = start; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            if (canPartition(nums, used, i + 1, k, curSum + nums[i], subSum)) return true;
            used[i] = false;
        }

        return false;
    }
}
