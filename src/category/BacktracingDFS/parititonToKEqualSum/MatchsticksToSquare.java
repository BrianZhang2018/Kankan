package category.BacktracingDFS.parititonToKEqualSum;

/**
 * https://leetcode.com/problems/matchsticks-to-square/
 *
 * Exactly same problem with https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 * Created by brianzhang on 1/25/21.
 */
public class MatchsticksToSquare {

    public static void main(String[] args) {
        System.out.println(new MatchsticksToSquare().makesquare(new int[]{1,1,2,2,2}));
    }

    public boolean makesquare(int[] nums) {
        return canPartitionKSubsets(nums, 4);
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % k != 0) return false;
        if (nums.length < k) return false;

        return canPartition(nums, new boolean[nums.length], 0, k, 0, sum / k);
    }

    private boolean canPartition(int[] nums, boolean[] used, int start, int k, int curSum, int subSum) {
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
