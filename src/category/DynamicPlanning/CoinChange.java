package category.DynamicPlanning;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * Created by brianzhang on 11/17/18.
 * 看图
 * https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-322-coin-change/
 * <p>
 * 本题要点：1. DP 2. 降维
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] coins = {2};
        System.out.println(coinChange.coinChangeDp(coins, 5));

        System.out.println(coinChange.coinChangeDFS(coins, 5));
    }

// Solution 1: DP
    /**
     * 二维数组, 但其实是滚动数组，所以可以降维成 一维数组
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeDp(int[] coins, int amount) {

        if (coins == null || coins.length == 0 || amount < 0)
            return -1;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if(dp[i - coin] != Integer.MAX_VALUE)
                    dp[i] = Integer.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

// Solution 2 : DFS
    int fewest = Integer.MAX_VALUE;

    public int coinChangeDFS(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0)
            return -1;

        Integer[] boxedArr = IntStream.of(coins).boxed().toArray(Integer[]::new);
        Arrays.sort(boxedArr, Collections.reverseOrder());

        dfsHelper(boxedArr, 0, amount, 0);

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
}