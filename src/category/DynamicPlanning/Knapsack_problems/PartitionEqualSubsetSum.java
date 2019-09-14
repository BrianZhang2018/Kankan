package category.DynamicPlanning.Knapsack_problems;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * https://leetcode.com/problems/partition-equal-subset-sum/discuss/90627/Java-Solution-similar-to-backpack-problem-Easy-to-understand
 */
public class PartitionEqualSubsetSum{

    public boolean canPartition(int[] nums) {
        if(nums == null || nums.length ==0)
            return true;
        
        int volume = 0;
        
        for(int i : nums){
            volume+=i;
        }
        
        if(volume % 2 != 0)
            return false;
        
        volume /=2;
        
        boolean[] dp = new boolean[volume+1];
        dp[0] = true;
        
        
        for(int i=0; i<nums.length; i++){
            for(int j=volume; j>=nums[i]; j--){
                dp[j] = dp[j] || dp[j-nums[i]];
            }
        }
        
        return dp[volume];
    }
}