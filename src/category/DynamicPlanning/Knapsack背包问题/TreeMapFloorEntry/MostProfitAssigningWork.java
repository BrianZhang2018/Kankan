package category.DynamicPlanning.Knapsack背包问题.TreeMapFloorEntry;

import java.util.*;
/**
 * https://leetcode.com/problems/most-profit-assigning-work/
 * Assumptions: low difficult task may have a high profit
 */
public class MostProfitAssigningWork {
    public static void main(String[] args) {
        System.out.println(maxProfitAssignment(new int[]{68, 35, 52, 47, 86},
                new int[]{67, 17, 1, 81, 3}, new int[]{92, 10, 85, 84, 82}));
    }

    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        TreeMap<Integer, Integer> tmap = new TreeMap<>();
        // in case two jobs have same difficulty but different profit,
        // we want to count the higher profit
        for (int i = 0; i < difficulty.length; i++) {
            tmap.put(difficulty[i], Math.max(profit[i], tmap.getOrDefault(difficulty[i], 0)));
        }

        int max = 0, res = 0;
        // maximum profit at this difficulty or below
        // in case lower difficulty job offers higher profit
        for (Integer key : tmap.keySet()) {
            max = Math.max(tmap.get(key), max);
            tmap.put(key, max);
        }

        Map.Entry<Integer, Integer> entry;
        for (int i = 0; i < worker.length; i++) {
            if (tmap.containsKey(worker[i])) {
                res += tmap.get(worker[i]);
            } else {
                entry = tmap.floorEntry(worker[i]);
                if (entry != null) {
                    res += entry.getValue();
                }
            }
        }
        return res;
    }
}
