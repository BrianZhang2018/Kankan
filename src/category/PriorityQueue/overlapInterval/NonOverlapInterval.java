package category.PriorityQueue.overlapInterval;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/non-overlapping-intervals
 * https://leetcode.com/problems/non-overlapping-intervals/discuss/91713/Java%3A-Least-is-Most
 *
 * Created by brianzhang on 6/21/20.
 */
public class NonOverlapInterval {

    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return 0;

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int count = 0;
        int[] prev = intervals[0];

        for(int i=1; i<intervals.length; i++){
            if(intervals[i][0] < prev[1]){
                count++;
            }else{
                prev = intervals[i];
            }
        }

        return count;
    }
}
