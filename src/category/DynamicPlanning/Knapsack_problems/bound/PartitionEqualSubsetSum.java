package category.DynamicPlanning.Knapsack_problems.bound;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/discuss/90627/Java-Solution-similar-to-backpack-problem-Easy-to-understand
 *
 * Good explanation for 2 dimensional solution:
 * https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
 *
 * 解题思路：
 * This problem is essentially let us to find whether there are several numbers in a set which are able to sum to a specific value (in this problem, the value is sum/2).
 * (如果一个数列存在几个数字相加等于 sum/2, which means 这个数列存在 partition equal subsets sum)
 *
 * Actually, this is a 0/1 knapsack problem, for each number, we can pick it or not.
 * Let us assume dp[i][j] means whether the specific sum j can be gotten from the first i numbers.
 * If we can pick such a series of numbers from 0-i whose sum is j, dp[i][j] is true, otherwise it is false.
 *
 * Base case: dp[0][0] is true; (zero number consists of sum 0 is true)
 *
 * Transition function: For each number, if we don't pick it, dp[i][j] = dp[i-1][j], which means if the first i-1 elements has made it to j,
 * dp[i][j] would also make it to j (we can just ignore nums[i]). If we pick nums[i]. dp[i][j] = dp[i-1][j-nums[i]],
 * which represents that j is composed of the current value nums[i] and the remaining composed of other previous numbers.
 * Thus, the transition function is dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
 */
public class PartitionEqualSubsetSum{

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
        System.out.println(canPartition2D(new int[]{1, 5, 11, 5}));
    }

    // solution-1
    // This problem is essentially let us to find whether there are several numbers in a set which are able to sum to a specific value (in this problem, the value is sum/2).
    // cause 如果一个数列存在几个数字相加等于sum/2, which means这个数列可以做partition equal subsets sum
    public static boolean canPartition(int[] nums) {
        if(nums == null || nums.length ==0) return true;
        
        int sum = 0;
        for(int i : nums) sum+=i;
        
        if(sum%2 != 0) return false;
        
        sum/=2;
        
        boolean[] dp = new boolean[sum+1];
        dp[0] = true;

        for(int i=0; i<nums.length; i++){ // i means "first" i numbers
            for(int j=sum; j>=nums[i]; j--){
                dp[j] = dp[j] || dp[j-nums[i]];// dp[j] means whether first "i" numbers can make a "j" => (0 + 1 +2....+i = j)
            }
        }
        
        return dp[sum];
    }

    // solution-2: 2D solution
    public static boolean canPartition2D(int[] nums) {
        if(nums == null || nums.length ==0) return true;
        int sum = 0;
        for(int i : nums) sum+=i;
        if(sum%2 != 0) return false;

        sum/=2; // 解题思路: whether there are several numbers in the set which are able to sum to "sum/2".

        int n = nums.length;
        boolean[][] dp = new boolean[n+1][sum+1];
        dp[0][0] = true;

        for (int i = 1; i < n+1; i++) {
            dp[i][0] = true;
        }
        for (int j = 1; j < sum+1; j++) {
            dp[0][j] = false;
        }

        for(int i=1; i<nums.length; i++) {
            for (int j = 1; j < sum+1; j++) {
                if(j < nums[i-1]) { // if the sum target "j" less then the previous number which means we don't need include current nums[i] as it will the "sum" greater, so just take the previous dp result (dp[i-1][j]).
                    dp[i][j] = dp[i-1][j];
                }
                else if (j >= nums[i-1]) {
                    dp[i][j] = (dp[i][j] || dp[i-1][j-nums[i-1]]);
                }
            }
        }

        return dp[n][sum];
    }
}