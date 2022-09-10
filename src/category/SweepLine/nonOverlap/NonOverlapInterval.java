package category.SweepLine.nonOverlap;

import java.util.*;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/discuss/91713/Java%3A-Least-is-Most
 *
 * Sort by "end-time"
 * Created by brianzhang on 6/21/20.
 */
public class NonOverlapInterval {
    public static void main(String[] args) {
        System.out.println(eraseOverlapIntervals(new int[][]{{1, 100},{1, 11},{2, 12},{11, 22}}));
        System.out.println(eraseOverlapIntervalsTemplate(new int[][]{{1, 100},{1, 11},{2, 12},{11, 22}}));
    }

    // Template solution - find non-overlap
    public static int eraseOverlapIntervalsTemplate(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;

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

    // sort by endTime
    public static int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;

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

    public int eraseOverlapIntervals1(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for(int[] i : intervals) {
            if(pq.isEmpty() || pq.peek()[1] <= i[0]) {
                pq.add(i);
            }
        }

        return intervals.length - pq.size();
    }

}
