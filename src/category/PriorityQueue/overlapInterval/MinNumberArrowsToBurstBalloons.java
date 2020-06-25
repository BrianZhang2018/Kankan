package category.PriorityQueue.overlapInterval;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * Find non-overlap interval - same with NonOverlapInterval.java
 *
 * Created by brianzhang on 6/21/20.
 */
public class MinNumberArrowsToBurstBalloons {

    public static void main(String[] args) {
        System.out.println(findMinArrowShots(new int[][]{{1, 100},{1, 11},{2, 12},{11, 22}}));
    }
    // Template solution
    public static int findMinArrowShots(int[][] points) {
        if(points == null|| points.length == 0) return 0;

        Arrays.sort(points, (a, b) -> a[0] - b[0]);

        int count=0;
        int minEnd = Integer.MAX_VALUE;
        for(int[] i : points) {
            if (minEnd < i[0]) { // find non-overlap
                count++;
                minEnd = i[1];
            } else {            // don't need count overlap interval as the prev interval which overlap with current interval has already been counted
                minEnd = Math.min(minEnd, i[1]);
            }
        }

        return count+1;
    }
}
