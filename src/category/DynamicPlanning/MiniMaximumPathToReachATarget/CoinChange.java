package category.DynamicPlanning.MiniMaximumPathToReachATarget;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * Created by brianzhang on 11/17/18
 *
 * 看图, https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-322-coin-change/
 *
 * DP explanation:
 * Def: dp[i][j]-min coins to make up j amount using first i types of coins.
 * Transition: dp[i][j] = min(dp[i][j], dp[i][j - coin_i] + 1)
 *
 * Knowledge：1. DP with 降维 
 *            2. 剪枝法
 */
public class CoinChange {
    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] coins = {1, 2, 5};
        System.out.println(coinChange.coinChangeDp(coins, 11));
        System.out.println(coinChange.coinChangeDFS(coins, 11));
    }

    // Solution 1: DP
    /**
     * 降维: dp[i][j] which only rely on dp[i] and dp[i-1], so 二维数组, 但其实是滚动数组，可以降维成一维数组
     *
     * def: dp[p] (p is the amount) : the least coins to make "p" amount
     */
    public int coinChangeDp(int[] coins, int targetAmount) {

        if (coins == null || coins.length == 0 || targetAmount < 0)
            return -1;

        //Init
        int[] dp = new int[targetAmount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        //loop for the min coins for amount 'i', finally got the dp[amount].
        for (int coin : coins) {
            for (int i = coin; i <= targetAmount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    //e.g. i=2, min(dp[2], dp[2-2] + 1),
                    //"dp[2-2]" means the least num of coins need for previous amount (j - coin_i), + 1 means plus current coin
                    dp[i] = Integer.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[targetAmount] == Integer.MAX_VALUE ? -1 : dp[targetAmount];
    }

    // Solution 2 : DFS
    int fewest = Integer.MAX_VALUE;

    public int coinChangeDFS(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0)
            return -1;

        Integer[] boxedArr = IntStream.of(coins).boxed().toArray(Integer[]::new);
        Arrays.sort(boxedArr, Collections.reverseOrder());
        dfsHelper2(boxedArr, 0, amount, 0);

        return fewest == Integer.MAX_VALUE ? -1 : fewest;
    }

    public void dfsHelper(Integer[] coins, int start, int amount, int count) {
        int coin = coins[start];
        if (start == coins.length - 1) {
            if (amount % coin == 0) {
                fewest = Integer.min(fewest, count + amount / coin);
            }
        } else {
            for (int k = amount / coin; k >= 0 && count + k < fewest; k--) {
                dfsHelper(coins, start + 1, amount - k * coin, count + k);
            }
        }
    }

    // Solution 3: the simple dfs compared with above one
    /**
     * huahua 称其为剪枝法，the idea is 先用最大的面值的硬币去算， 因为得出的结果可能是 需要最少的硬币 的数目，因为用的面值最大
     * 然后，在比较小的面值，跟最大面值需要的硬币数目比较 多的 都去掉（剪枝）
     */
    public void dfsHelper2(Integer[] coins, int start, int amount, int count) {

        if (amount == 0) {
            fewest = Integer.min(fewest, count);
        }

        if (start == coins.length)
            return;

        int coin = coins[start];
        for (int k = amount / coin; k >= 0 && count + k < fewest; k--) {
            dfsHelper2(coins, start + 1, amount - k * coin, count + k);
        }
    }

}