package companies.linkedin;

import java.lang.Math;

/**
 * https://www.programcreek.com/2014/05/leetcode-paint-house-java/
 * 
 * https://www.lintcode.com/problem/paint-house/description
 * https://youtube.com/watch?v=fZIsEPhSBgM
 */
public class PaintHouse{
    public static void main(String[] args) {
        System.out.println(minCost(new int[][]{{14,2,11}, {11,14,5}, {14,3,10}}));
    }

    public static int minCost(int[][] costs) {
        if(costs==null||costs.length==0) return 0;
     
        for(int i=1; i<costs.length; i++){
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }
     
        int m = costs.length-1;
        return Math.min(Math.min(costs[m][0], costs[m][1]), costs[m][2]);
    }
}
