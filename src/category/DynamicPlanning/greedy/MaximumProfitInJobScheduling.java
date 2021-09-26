package category.DynamicPlanning.greedy;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 *
 * Created by brianzhang on 9/22/21.
 */
public class MaximumProfitInJobScheduling {

    public static void main(String[] args) {
        System.out.println(jobScheduling(new int[]{1,2,3,3}, new int[]{3,4,5,6}, new int[]{50,10,40,7}));
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int length = startTime.length;
        int[][] jobs = new int[length][3];

        for(int i=0; i<length; i++) {
            jobs[i] = new int[] { startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);

        TreeMap<Integer, Integer> dp = new TreeMap();
        dp.put(0,0);

        for(int[] i : jobs) {
            int curr = dp.floorEntry(i[0]).getValue() + i[2];
            if(curr > dp.lastEntry().getValue()) {
                dp.put(i[1], curr);
            }
        }

        return dp.lastEntry().getValue();
    }
}
