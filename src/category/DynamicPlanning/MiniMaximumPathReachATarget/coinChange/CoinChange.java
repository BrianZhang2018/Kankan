package category.DynamicPlanning.MiniMaximumPathReachATarget.coinChange;

import java.util.*;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/coin-change/
 * Get the minimalCoins number of coins that you need to make up that amount.
 *
 * 看图, https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-322-coin-change/
 *
 * DP explanation:
 * Def: dp[i][j]-min coins to make up j amount using first i types (denominations) of coins.
 * Transition: dp[i][j] = min(dp[i][j], dp[i][j - coin_i] + 1)
 *
 * Knowledge：1. DP with 降维
 *            2. 剪枝法
 *
 * Output the res: we can use the CombinationSum.java (backtracking) which will print out all the combinations.
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1,2,5};
        System.out.println(coinChangeDP(coins, 11));
        System.out.println(coinChangeDFS(coins, 11));
    }
    /**
     * Solution-1: DP
     *
     * 降维: dp[i][j] which only rely on dp[i] and dp[i-1], so 可以降维成一维数组
     * dp[p] (p is the amount): the least coins to make "p" amount
     */
    public static int coinChangeDP(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) return -1;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // loop for the min coins for amount 'i', finally got the dp[amount].
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    // e.g. i=2, min(dp[2], dp[2-2] + 1),b
                    // "dp[2-2]" means the least num of coins need for previous amount (j - coin_i), "+ 1" means plus current coin
                    dp[i] = Integer.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /**
     * Solution-2: DFS + 剪枝法
     * huahua 称其为剪枝法，the idea is 先用最大的面值的硬币去算，因为得出的结果可能是 需要最少的硬币的数目，因为用的面值最大.
     * 然后，在比较小的面值，跟最大面值需要的硬币数目比较, 多的都去掉（剪枝）
     */
    static int res = Integer.MAX_VALUE;

    public static int coinChangeDFS(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) return -1;

        Integer[] boxedArr = IntStream.of(coins).boxed().toArray(Integer[]::new);
        Arrays.sort(boxedArr, Collections.reverseOrder());
        dfsHelper(boxedArr, 0, amount, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static void dfsHelper(Integer[] coins, int start, int amount, int count) {
        if (amount == 0) {
            res=Integer.min(res, count);
            return;
        }
        if (start == coins.length) return;

        int coin = coins[start];
        for (int k = amount / coin; k >= 0 && count + k < res; k--) {
            dfsHelper(coins, start + 1, amount - k * coin, count + k);
        }
    }
}