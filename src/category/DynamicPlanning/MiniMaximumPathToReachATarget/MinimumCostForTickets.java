package category.DynamicPlanning.MiniMaximumPathToReachATarget;

/**
 * 这道题 最后转化成一年的minimum cost
 * <p>
 * https://leetcode.com/problems/minimum-cost-for-tickets/discuss/226670/Java-DP-Solution-with-explanation-O(n)
 * Created by brianzhang on 2/23/19.
 */
public class MinimumCostForTickets {

    public int mincostTickets(int[] days, int[] costs) {
        if (days == null || costs == null)
            return 0;

        boolean[] isDays = new boolean[366];
        int[] minCost = new int[366];
        minCost[0] = 0;

        for (int day : days) {
            isDays[day] = true;
        }

        for (int i = 1; i <= 365; i++) {

            if (!isDays[i]) {
                minCost[i] = minCost[i - 1];
                continue;
            }

            int min;
            min = minCost[i - 1] + costs[0];
            min = Math.min(min, minCost[Math.max(0, i - 7)] + costs[1]); // Math.max(0, i-7) 0 is the default value if the (i-7) <0.
            min = Math.min(min, minCost[Math.max(0, i - 30)] + costs[2]);
            minCost[i] = min;
        }

        return minCost[365];
    }
}
