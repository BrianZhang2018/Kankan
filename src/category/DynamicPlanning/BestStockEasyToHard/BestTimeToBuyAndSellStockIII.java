package category.DynamicPlanning.BestStockEasyToHard;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * Reference:
 * http://bangbingsyb.blogspot.com/2014/11/leetcode-best-time-to-buy-and-sell.html
 *
 * Similar question: TrappingRainWater.java
 * Created by brianzhang on 2/24/19.
 */
public class BestTimeToBuyAndSellStockIII {
    public static void main(String[] args) {
        int[] test = { 1, 2,3,4,5};
        System.out.println(maxProfit(test));
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int n = prices.length;
        int profit = 0;

        // scan from left end - got the max profit for first transaction
        // left[i] keeps the max profit from 0 -> i
        int[] left = new int[n];
        int min = prices[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        // scan from right end - got the max profit for second transaction
        // right[i] keeps the max profit from n-1 -> 0
        int[] right = new int[n];
        int max = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], max - prices[i]);
            max = Math.max(max, prices[i]);
            //profit = Math.max(profit, left[i] + right[i]);
        }

        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));

        // You can sell and buy at the same day (refer above reference link)
        // 最后这两个收益之和便是以i为分割的最大收益. 将序列从左向右扫一遍可以获取 first transaction, 从右向左扫一遍可以获取 second transaction. 相加后取最大值即为答案
        for (int i = 0; i < n; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }

        return profit;
    }

}
