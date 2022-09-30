package category.DynamicPlanning.BestStockSellBuy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * You want to maximize your profit by choosing a single day to buy one stock
 * and choosing a different day in the future to sell that stock.
 * Created by brianzhang on 6/9/20.
 */
public class BestTimeToBuyAndSellStockI {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public static int maxProfit(int prices[]) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice)
                minPrice = prices[i];
            else if (prices[i] - minPrice > maxProfit)
                maxProfit = prices[i] - minPrice;
        }
        return maxProfit;
    }

    public static int maxProfitDP(int[] prices) {
        if (prices.length < 2) return 0;

        int[] dp = new int[prices.length];
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > minPrice) {
                dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
            } else {
                dp[i] = dp[i - 1];
                minPrice = Math.min(minPrice, prices[i]);
            }
        }

        return dp[prices.length - 1];
    }

}
