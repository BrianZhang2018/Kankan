package category.DynamicPlanning.BestStockSellBuy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * Reference:
 * http://bangbingsyb.blogspot.com/2014/11/leetcode-best-time-to-buy-and-sell.html
 *
 * Similar problem: TrappingRainWater.java
 *
 * max_profit[0:n-1] => max_profit[0:i] + max_profit[i:n-1]
 * 对于这个特定分割来说，最大收益为两段的最大收益之和. 每一段的最大收益当然可以用I的解法来做。
 * 而III的解一定是对所有0<=i<=n-1的分割的最大收益中取一个最大值。为了增加计算效率，考虑采用dp来做bookkeeping.
 *
 * Created by brianzhang on 2/24/19.
 */
public class BestTimeToBuyAndSellStockIIIAtMostTwoTransactions {
    public static void main(String[] args) {
        int[] test = { 1, 2,3,4,5};
        System.out.println(maxProfit(test));
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)  return 0;

        int n = prices.length;
        int profit = 0;
        // scan from left - got the max profit for first transaction
        // left[i] keeps the max profit from 0 -> n-1
        int[] left = new int[n];
        int min = prices[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        // scan from right - got the max profit for second transaction
        // right[i] keeps the max profit from n-1 -> 0
        int[] right = new int[n];
        int max = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], max - prices[i]);
            max = Math.max(max, prices[i]);
        }

        System.out.println(Arrays.toString(left)); System.out.println(Arrays.toString(right));

        // You can sell and buy at the same day (refer above reference link)
        // 最后这两个收益之和便是以 i 为分割的两边最大最大收益之和-(可以理解为分成了两个"Best Time to Buy and Sell Stock II"的问题, 对 i 的左右区间).
        for (int i = 0; i < n; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }

        return profit;
    }

}
