package category.PriorityQueue.overlapInterval;

import java.util.*;

/**
 * 1. Sort by start-time
 * 2. Check overlap: compare the previous minimum end-time with current start-time, and update the minimum end-time when need
 * 3. Got all the overlaps
 *
 * https://www.lintcode.com/problem/meeting-rooms-ii
 *
 * Find overlap intervals
 * Logic: Sort by start-time, keep the "minimum end-time" and compare it with current "start-time". 看有没有conflict, 有的话就累加
 *
 * Created by brianzhang on 10/24/18.
 */
public class MeetingRoomII {
    public static void main(String[] args) {
        Interval source = new Interval(1, 10), source1 = new Interval(3, 5);
        Interval source2 = new Interval(4, 6), source3 = new Interval(7, 9);
        System.out.println(minMeetingRooms(Arrays.asList(source, source1, source2, source3)));
    }
    /**
     * 解题思路:
     * 循环遍历每个会议的时间段，对于任意一个当前会议，是否需要增加一间新的会议室取决于"早于它开始的其他会议是否已经结束"。
     * 因此，我们先查看Queue中其他会议的结束时间是否小于等于当前会议的开始时间，将相比于当前会议开始时间点已经结束的会议从Queue中删除，
     * 然后再将当前会议的结束时间加入到Queue中
     */
    public static int minMeetingRooms(List<Interval> intervals) {

        Collections.sort(intervals, (a, b) -> a.start - b.start); // sort by start time
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // store the end time of meeting, keep the minimum end-time on top

        for(Interval i : intervals){
            if(!pq.isEmpty() && pq.peek() <= i.start){ // here is different with car-pooling (while) as we need get the total number of rooms
                pq.poll();
            }
            pq.add(i.end);
        }

        return pq.size();
    }
}

class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
