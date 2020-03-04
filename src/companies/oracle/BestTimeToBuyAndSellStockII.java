package companies.oracle;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * Created by brianzhang on 3/1/20.
 */
public class BestTimeToBuyAndSellStockII {

    //Greedy algorithm
    public int maxProfit(int[] prices) {

        if(prices == null || prices.length == 0)
            return 0;

        int total = 0;
        for(int i=0; i<prices.length-1; i++){
            if(prices[i+1] > prices[i])
                total+= prices[i+1] - prices[i];
        }

        return total;
    }
}
