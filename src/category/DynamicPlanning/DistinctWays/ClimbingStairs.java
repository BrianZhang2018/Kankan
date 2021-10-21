package category.DynamicPlanning.DistinctWays;

/**
 * https://leetcode.com/problems/climbing-stairs/
 *
 * The key intuition to solve the problem is that given a number of stairs n, if we know the number ways to get to the points [n-1] and [n-2] respectively, denoted as n1 and n2 , then the total ways to get to the point [n] is n1 + n2. Because from the [n-1] point, we can take one single step to reach [n]. And from the [n-2] point, we could take two steps to get there.
 *
 * basically it's a fibonacci problem
 */
public class ClimbingStairs{

    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] =1;
        dp[1] =1;
        
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        return dp[n];
    }
}