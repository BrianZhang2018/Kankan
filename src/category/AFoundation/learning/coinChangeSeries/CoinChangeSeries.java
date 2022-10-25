package category.AFoundation.learning.coinChangeSeries;

import java.util.Arrays;

public class CoinChangeSeries {
    enum TransactionType {
        BALANCE_INQUIRY, DEPOSIT_CASH, DEPOSIT_CHECK, WITHDRAW, TRANSFER
    }

    public static void main(String[] args) {
     /*   System.out.println(new CoinChangeSeries().solutionBF(new int[]{357,239,73,52}, 9832));
        System.out.println(new CoinChangeSeries().solutionBFMemo(new int[]{357,239,73,52}, 9832));*/
        System.out.println(new CoinChangeSeries().solutionDPBottomUp1(new int[]{357,239,73,52}, 9832));
        System.out.println(TransactionType.BALANCE_INQUIRY.ordinal());
    }

    // Brute force recursion,  space complexity O(S)
    // time complexity: O(S^n) (S is the amount, n is number of different denomination coins), the worst case S amount need be divided into each sub amount problems.
    // Amount 1 with coins, Amount 2 with coins ...
    public int solutionBF(int[] coins, int amount) {
        dfsHelperBruteForce1(coins, amount);
        //dfsHelperBruteForce2(coins, 0, amount, 0);
        return fewestNumOfCoins == Integer.MAX_VALUE ? -1 : fewestNumOfCoins;
    }
    // 1
    public int dfsHelperBruteForce1(int[] coins, int remain) {
        if(remain < 0) return -1;
        if(remain == 0) return 0;

        int minCount = Integer.MAX_VALUE;
        for(int coin : coins) {
            int subCount = dfsHelperBruteForce1(coins, remain-coin);
            if(subCount != -1) {
                minCount = Math.min(minCount, subCount+1);
            }
        }

        return minCount != Integer.MAX_VALUE ? minCount : -1;
    }
    // 2 - my version - alternative way
    int fewestNumOfCoins = Integer.MAX_VALUE;
    public void dfsHelperBruteForce2(int[] coins, int start, int remain, int count) {
        if(remain == 0) {
            fewestNumOfCoins = Math.min(fewestNumOfCoins, count);
            return;
        }
        for(int i=start; i<coins.length; i++) {
            if(remain - coins[i] >= 0) {
                dfsHelperBruteForce2(coins, i, remain-coins[i], count+1);
            }
        }
    }

    // Brute force recursion with memo
    private Integer[] memo;
    public int solutionBFMemo(int[] coins, int amount) {
        memo = new Integer[amount+1];
        return dfsHelperBruteForce1WithMemo1(coins, amount);
    }

    public int dfsHelperBruteForce1WithMemo1(int[] coins, int remain) {
        if(remain < 0) return -1;
        if(remain == 0) return 0;

        if(memo[remain] != null) return memo[remain];

        int minCount = Integer.MAX_VALUE;
        for(int coin : coins) {
            int subCount = dfsHelperBruteForce1WithMemo1(coins, remain-coin);
            if(subCount != -1) {
                minCount = Math.min(minCount, subCount+1);
                memo[remain] = minCount;
            }
        }

        return minCount != Integer.MAX_VALUE ? minCount : -1;
    }

    // DP bottom-up tabulation, time complexity: O(S * N), S is the amount, N is the number of different denomination coins
    public static int solutionDPBottomUp(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1); // amount+1 used to represent unreachable maximum number, like Integer.MAX_VALUE.
        dp[0] = 0;

        for(int coin : coins) {
            for(int i=coin; i<=amount; i++) {
                if(dp[i-coin] != amount+1) { // this condition actually can be removed
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
                }
            }
        }

        return dp[amount] != amount+1 ? dp[amount] : -1;
    }
    // same like above one, just different `for loop` order
    public int solutionDPBottomUp1(int[] coins, int amount) {
        Integer[] dp = new Integer[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        for(int i=1; i<=amount; i++) {
            for(int coin : coins) {
                if(i-coin<0) continue;
                dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }

        }
        return dp[amount] != amount+1 ? dp[amount] : -1;
    }

    // dp top-down recursion

    // backtracking solution explore
   /* class Solution {
        public int coinChange(int[] coins, int amount) {
            memo = new int[amount+1];
            Arrays.fill(memo, amount+1); // amount + 1 means the max inf number like Integer.MAX_VALUE
            backtracking(coins, amount, 0, 0);
            return res;
        }

        int min = Integer.MAX_VALUE;
        int res = 0;
        int[] memo;

        public int backtracking(int[] coins, int amount, int count, int start) {
            if(amount < 0) return -1;

            if(amount == 0) {

                res = Integer.min(res, count);
                return -1;
            }

            if(memo[amount] != amount+1) {
                System.out.println("hahah");
                return memo[amount];
            }

            for(int i=start; i<coins.length; i++) {
                amount-=coins[i];
                System.out.println(i);
                int subCount = backtracking(coins, amount, count+1, i); // not i+1, since same number can be reused multiple times
                if(subCount != -1) {
                    memo[amount] = Math.min(memo[amount], subCount);
                }
                amount+=coins[i];
            }

            return memo[amount] == amount+1 ? -1 : memo[amount];
        }
    }*/
}
