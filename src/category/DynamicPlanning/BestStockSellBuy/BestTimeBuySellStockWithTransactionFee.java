package category.DynamicPlanning.BestStockSellBuy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 * 这题其实也很好理解，只要自己拿个例子走一遍，就很好理解了。
 *
 * Created by brianzhang on 3/31/19.
 */
public class BestTimeBuySellStockWithTransactionFee {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

 /**
  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108871/2-solutions-2-states-DP-solutions-clear-explanation!

    Status transformation:
    At day i, we may buy stock (from previous sell status) or do nothing (from previous buy status):
    buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
    Or
    At day i, we may sell stock (from previous buy status) or keep holding (from previous sell status):
    sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);

    Finally:
    We will return sell[last_day] as our result, which represents the max profit at the last day, given that you took sell action at any day before the last day.
    */
    public static int maxProfit(int[] prices, int fee){
        if(prices == null || prices.length == 0)
            return 0;

        int sell = 0;
        int buy = -prices[0];

        for(int i=1; i<prices.length; i++){
            sell = Math.max(sell, buy + prices[i] - fee); //we only apply the fee when sell the stock, you can also do opposite way.
            buy = Math.max(buy, sell - prices[i]);
        }

        return sell;
    }
}
