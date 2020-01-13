package category.DynamicPlanning.Knapsack_problems;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/discuss/90627/Java-Solution-similar-to-backpack-problem-Easy-to-understand
 *
 * Good explanation for 2 dimensional solution:
 * https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
 *
 * Actually, this is a 0/1 knapsack problem, for each number, we can pick it or not. Let us assume dp[i][j] means whether the specific sum j can be gotten from the first i numbers.
 * If we can pick such a series of numbers from 0-i whose sum is j, dp[i][j] is true, otherwise it is false.
 *
 * Base case: dp[0][0] is true; (zero number consists of sum 0 is true)

   Transition function: For each number, if we don't pick it, dp[i][j] = dp[i-1][j], which means if the first i-1 elements has made it to j,
   dp[i][j] would also make it to j (we can just ignore nums[i]). If we pick nums[i]. dp[i][j] = dp[i-1][j-nums[i]],
   which represents that j is composed of the current value nums[i] and the remaining composed of other previous numbers.
   Thus, the transition function is dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
 *
 */
public class PartitionEqualSubsetSum{

    public static void main(String[] args) {
        PartitionEqualSubsetSum test = new PartitionEqualSubsetSum();
        System.out.println(test.canPartition(new int[]{1, 5, 11, 5}));
    }

    public boolean canPartition(int[] nums) {
        if(nums == null || nums.length ==0)
            return true;
        
        int volume = 0;
        
        for(int i : nums) volume+=i;
        
        if(volume % 2 != 0) return false;
        
        volume /= 2;
        
        boolean[] dp = new boolean[volume+1];
        dp[0] = true;

        for(int i=0; i<nums.length; i++){ // i means "first" i numbers
            for(int j=volume; j>=nums[i]; j--){
                dp[j] = dp[j] || dp[j-nums[i]];// dp[j] means whether first i numbers can make a j => (0 + 1 +2....+i = j)
            }
        }
        
        return dp[volume];
    }
}