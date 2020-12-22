package category.DynamicPlanning.MiniMaximumPathReachATarget;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Coin Change I 拓展 - 输出combination组合
 * BackTracking - CombinationSum I 也可以解决这个问题，但是慢，我们这里用到了剪枝法
 *
 * Note: 从这道题可以看出DP对backtracking的简化，其实计算的过程是一样的. DP calculate as matrix way, backtracking is DFS.
 *
 * Created by brianzhang on 5/17/20.
 */
public class CoinChangeOutputCombination {

    public static void main(String[] args) {
        CoinChangeOutputCombination coinChange = new CoinChangeOutputCombination();
        int[] coins = {1, 2, 5}; System.out.println(coinChange.coinChangeDFS(coins, 11));
    }

    List<Integer> fewestCombination; int fewest = Integer.MAX_VALUE;

    public String coinChangeDFS(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) return null;

        Integer[] boxedArr = IntStream.of(coins).boxed().toArray(Integer[]::new);
        Arrays.sort(boxedArr, Collections.reverseOrder());

        dfsHelper(boxedArr, 0, amount, new ArrayList<>());
        return Arrays.toString(fewestCombination.toArray(new Integer[fewestCombination.size()]));
    }

    /**
     * DFS + 剪枝法
     * huahua 称其为剪枝法，the idea is 先用最大的面值的硬币去算，因为得出的结果可能是 需要最少的硬币 的数目，因为用的面值最大.
     * 然后，在比较小的面值，跟最大面值需要的硬币数目比较, 多的都去掉（剪枝）
     */
    public void dfsHelper(Integer[] coins, int start, int amount, List<Integer> counter) {

        if (amount == 0) {
            if(fewestCombination == null || fewest > counter.size()){
                fewestCombination = new ArrayList(counter);
                fewest = counter.size();
            }
            return;
        }

        if (start == coins.length) return;

        int coin = coins[start];
        for (int k=amount/coin; k>=0 && counter.size() + k < fewest; k--) {
            if(k > 0){
                for(int i=0; i<k; i++) counter.add(coin);
            }
            dfsHelper(coins, start + 1, amount - k * coin, counter);
            if(k > 0){
                for(int i=0; i<k; i++) counter.remove(counter.size()-1);
            }
        }
    }
}
