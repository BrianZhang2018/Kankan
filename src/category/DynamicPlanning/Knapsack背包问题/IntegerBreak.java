package category.DynamicPlanning.Knapsack背包问题;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/integer-break/discuss/80707/easy-java-dp-solution-with-explanation-typical-knapsack-problem
 *
 * This is a typical knapsack problem. We can assume that the volume of the knapsack is n.
 */
public class IntegerBreak{
    public static void main(String[] args) {
        IntegerBreak ib = new IntegerBreak();
        System.out.println(ib.integerBreak1(5));
    }

    // 更容易理解
    public int integerBreak1(int n) {
        if(n<=3) return n-1;

        int[] dp= new int[n+1];
        for(int i=1; i<=n; i++){ // 问题的规模 (range)
            dp[i]=i; // initiate the base case, e.g. dp[3] = 3, means the base case dp[3] = 3 * 1.
            for(int j=1; j<=i; j++){
                dp[i]=Math.max(dp[i], dp[i-j]*j);
                // dp[i] means the maximum product value of i (i need to be divided into at least two positive parts)
            }   // so, dp[i-j]*j is the divide logic. "j" loop is to use the number less than the i, then divide i to (j and i-j), then calculate the dp[i-j]*j
        }
        return dp[n];
    }

    // 我推导的方法，但是不怎么好理解
    public int integerBreak(int n) {
        
        int[] dp = new int[n+1];
        Arrays.fill(dp, 1); //initiate the dp array: if n=1, then dp[0]->dp[5]=1, which means you only 1 as selected item.

        for(int i=2; i<n; i++){ // 问题的规模
            for(int j=i; j<=n; j++){
                dp[j] = Math.max(dp[j], dp[j-i] * i);// e.g. when i=2, j=5, then dp[5] = max(dp[5], dp[3] * 2), 背包选择item的时候，要dp[5],还是要dp[3] * 2
            }
        }
        
        return dp[n];
    }
}