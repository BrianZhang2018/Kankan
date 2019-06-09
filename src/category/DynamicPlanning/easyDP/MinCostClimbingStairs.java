package category.DynamicPlanning.easyDP;

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/solution/
 */
public class MinCostClimbingStairs {

    public static void main(String[] args){
        System.out.println(Integer.toBinaryString('A'));
        System.out.println((int)('A'));
        System.out.println((char)('a'^256));
        minCostClimbingStairs(new int[]{0,0,1,1});
    }

    public static int minCostClimbingStairs(int[] cost) {
        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i-1], cost[i-2]);
        }
        // for(int i : cost){
        //     System.out.println(i);
        // }
        return Math.min(cost[cost.length-1], cost[cost.length-2]);
    }
}