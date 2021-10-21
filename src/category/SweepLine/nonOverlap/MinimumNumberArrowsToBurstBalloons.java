package category.SweepLine.nonOverlap;

import java.util.Arrays;

/**
 * Find non-overlap interval - same with NonOverlapInterval.java
 *
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * Created by brianzhang on 6/21/20.
 */
public class MinimumNumberArrowsToBurstBalloons {

    public static void main(String[] args) {
        System.out.println(findMinArrowShots(new int[][]{{1, 100},{1, 11},{2, 12},{11, 22}}));
    }
    // Template solution
    public static int findMinArrowShots(int[][] points) {
        if(points == null|| points.length == 0) return 0;

        Arrays.sort(points, (a, b) -> a[0] - b[0]);

        int count=1;
        int minEnd = Integer.MAX_VALUE;
        for(int[] i : points) {
            if (minEnd < i[0]) { // find non-overlap
                count++;
                minEnd = i[1];
            } else {
                minEnd = Math.min(minEnd, i[1]);
            }
        }

        return count;
    }
}
