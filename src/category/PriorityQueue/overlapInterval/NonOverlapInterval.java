package category.PriorityQueue.overlapInterval;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/discuss/91713/Java%3A-Least-is-Most
 *
 * Sort by end-time
 * Created by brianzhang on 6/21/20.
 */
public class NonOverlapInterval {

    public static void main(String[] args) {
        System.out.println(eraseOverlapIntervals(new int[][]{{1, 100},{1, 11},{2, 12},{11, 22}}));
        System.out.println(eraseOverlapIntervalsTemplate(new int[][]{{1, 100},{1, 11},{2, 12},{11, 22}}));
    }

    // Template solution - find non-overlap
    public static int eraseOverlapIntervalsTemplate(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return 0;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int count = 0;
        int minEnd = Integer.MAX_VALUE;

        for(int[] i : intervals){
            if(minEnd <= i[0]){ // find non-overlap
                count++;
                minEnd = i[1];
            }else{
                minEnd = Math.min(minEnd, i[1]);
            }
        }

        return intervals.length - count - 1;
    }

    // my solution
    public static int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return 0;

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int count = 0;
        int[] prevEndTime = intervals[0];

        for(int i=1; i<intervals.length; i++){
            if(prevEndTime[1] > intervals[i][0]){ // find overlap
                count++;
            }else{
                prevEndTime = intervals[i]; // non-overlap
            }
        }

        return count;
    }

}
