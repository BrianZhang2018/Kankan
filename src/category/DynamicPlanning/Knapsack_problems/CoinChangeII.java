package category.DynamicPlanning.Knapsack_problems;

/**
 * https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space
 *
 * Created by brianzhang on 5/2/20.
 */
public class CoinChangeII {

    public static void main(String[] args) {
        System.out.println(change(11, new int[]{1, 2, 5}));
    }
    /**
     *  dp[i][j]: the number of combinations to make up amount j by using the first i types of coins
     *
     *  dp[i - 1][j]: 完全不用当前硬币组成j有多少种组合
     *  dp[i][j - coins[i - 1]]: 使用至少一个当前硬币（与上面一条是互斥事件）组成j有多少组合 (因为用了当前的coin_i, 所以再知道amount=j-coin_i-1_value，就Ok了)
     */
    public int change2D(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        dp[0][0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }

    // We can see that dp[i][j] only rely on dp[i-1][j] and dp[i][j-coins[i]], so we can optimize the space by using one-dimension array.
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;

        for(int coin : coins){
            for(int j=coin; j<=amount; j++){
                dp[j] += dp[j-coin]; // dp[j] 不用当前的coin, dp[j-coin] 用当前的coin，所以只要知道不用的
            }
        }
        return dp[amount];
    }

    // Another interesting solution, 自底向上解法，比上面的更直观
    public static int change1(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int coin: coins){
            for(int i = 1; i < dp.length; i++){ // loop the amount
                if(i - coin >= 0)
                    dp[i] += dp[i-coin];
                // System.out.println(Arrays.toString(dp));
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[amount];
    }
}
