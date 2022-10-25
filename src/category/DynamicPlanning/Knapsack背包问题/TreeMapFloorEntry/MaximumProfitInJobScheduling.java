package category.DynamicPlanning.Knapsack背包问题.TreeMapFloorEntry;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/discuss/409009/JavaC%2B%2BPython-DP-Solution
 * Explanation:
     Sort the jobs by endTime.
     dp[time] = profit means that within the first time duration, we can make at most profit money.
     Initial dp[0] = 0, as we make profit = 0 at time = 0.

     For each job = [s, e, p], where s,e,p are its start time, end time and profit,
     Then the logic is similar to the knapsack problem.
     If we don't do this job, nothing will be changed.
     If we do this job, binary search(floorEntry) in the dp to find the largest profit we can make before start time s.
     So we also know the maximum current profit that we can make doing this job.

     Compare with last element in the dp,
     we make more money,
     it worth doing this job,
     then we add the pair of [e, cur] to the back of dp.
     Otherwise, we'd like not to do this job.

 Complexity
     Time O(NlogN)
     Space O(N)
 *
 * Created by brianzhang on 9/22/21.
 */
public class MaximumProfitInJobScheduling {
    public static void main(String[] args) {
        System.out.println(jobScheduling(new int[]{1,2,3,3}, new int[]{3,4,5,6}, new int[]{50,60,40,70}));
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int length = startTime.length;
        int[][] jobs = new int[length][3];
        for(int i=0; i<length; i++) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]); // sort by endTime
        TreeMap<Integer, Integer> dp = new TreeMap();
        dp.put(0,0);
        for(int[] i : jobs) {
            int curr = dp.floorEntry(i[0]).getValue() + i[2]; //
            if(curr > dp.lastEntry().getValue()) {
                dp.put(i[1], curr);
            }
        }

        return dp.lastEntry().getValue();
    }
}
