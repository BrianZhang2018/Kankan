package category.DynamicPlanning.MiniMaximumPathReachATarget.coinChange;

import java.util.*;
import java.util.stream.IntStream;

/**
 * CoinChangeI followup question - 输出combination组合
 * BackTracking - CombinationSumI 也可以解决这个问题，但是慢，我们这里用到了剪枝法
 *
 * Created by brianzhang on 5/17/20.
 */
public class CoinChangePrintOutCombination {

    public static void main(String[] args) {
        System.out.println(new CoinChangePrintOutCombination().coinChangeDFS(new int[]{1, 2, 5}, 11));
    }

    List<Integer> res;
    int minimalCoins = Integer.MAX_VALUE;

    public String coinChangeDFS(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) return null;

        Integer[] boxedArr = IntStream.of(coins).boxed().toArray(Integer[]::new);
        Arrays.sort(boxedArr, Collections.reverseOrder());

        dfsHelper(boxedArr, 0, amount, new ArrayList<>());
        return Arrays.toString(res.toArray(new Integer[res.size()]));
    }

    // DFS + 剪枝法
    public void dfsHelper(Integer[] coins, int start, int amount, List<Integer> counter) {

        if (amount == 0) {
            if(res == null || minimalCoins > counter.size()){
                res = new ArrayList(counter);
                minimalCoins = counter.size();
            }
            return;
        }

        if (start == coins.length) return;

        int coin = coins[start];
        for (int k = amount/coin; k>=0 && counter.size()+k < minimalCoins; k--) {
            if(k > 0)
                for(int i=0; i<k; i++) counter.add(coin);

            dfsHelper(coins, start + 1, amount - k * coin, counter);

            if(k > 0)
                for(int i=0; i<k; i++) counter.remove(counter.size()-1);
        }
    }
    // 因为我们要收集所有的组合，所以这里我们用到了回溯。But, 在CoinChange.java的dfs里没有回溯，因为不需要倒回去再看其他的可能的组合.
}
