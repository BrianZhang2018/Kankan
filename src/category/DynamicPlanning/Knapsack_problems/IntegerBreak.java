package category.DynamicPlanning.Knapsack_problems;

/**
 * https://leetcode.com/problems/integer-break/discuss/80707/easy-java-dp-solution-with-explanation-typical-knapsack-problem
 */
public class IntegerBreak{

    public int integerBreak(int n) {
        
        int[] dp = new int[n+1];
        dp[0] = 1;
        
        int max = 0;
        for(int i=1; i<n; i++){
            for(int j=i; j<=n; j++){
                dp[j] = Math.max(dp[j], dp[j-i] * i);
                if(j==n && dp[j]>max){
                    max = dp[j];
                }
            }
        }
        
        return max;
    }
}