package category.DynamicPlanning.MiniMaximumPathToReachATarget;

/**
 * https://leetcode.com/problems/coin-change-2
 *
 * https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space
 * Created by brianzhang on 5/2/20.
 */
public class CoinChangeII {

    public static void main(String[] args) {
        CoinChangeII test = new CoinChangeII();
        int[] coins = {1, 2, 5};
        System.out.println(test.change(11, coins));
    }


    //Compare this with CoinChangeI
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;

        for(int coin : coins){
            for(int j=coin; j<=amount; j++){
                dp[j] = dp[j] + dp[j-coin];
            }
        }

        return dp[amount];
    }


    // another interesting solution, 自底向上解法，比上面的更直观
    public int change1(int amount, int[] coins) {
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
