package category.SweepLine.overlapInterval;

import java.util.*;

/**
 * PriorityQueue
 *
 * 1. Sort by "Start-Time" [Catch]
 * 2. Check Overlap - compare the previous minimum end-time with current start-time, and update the minimum end-time when need
 * 3. Got all the overlaps
 *
 * https://www.lintcode.com/problem/meeting-rooms-ii
 *
 * Find Overlap Intervals [Catch]:
 * Logic: Sort by start-time, keep the "minimum end-time" and compare it with current "start-time". 看有没有conflict, 有的话就累加
 *
 * Created by brianzhang on 10/24/18.
 */
public class MeetingRoomII {
    public static void main(String[] args) {
        Interval source = new Interval(1, 10), source1 = new Interval(3, 5),
                source2 = new Interval(4, 6), source3 = new Interval(7, 9);
        System.out.println(minMeetingRooms(Arrays.asList(source, source1, source2, source3)));
        System.out.println(minMeetingRooms1(Arrays.asList(source, source1, source2, source3)));
    }
    /**
     * 解题思路:
     * 循环遍历每个会议的时间段，对于任意一个当前会议，是否需要增加一间新的会议室取决于 "早于它开始的其他会议是否已经结束"
     * 因此，
     * 1. 我们先查看Queue中其他会议的结束时间是否小于等于当前会议的开始时间，
     * 2. 将相比于当前会议开始时间点已经结束的会议从Queue中删除，
     * 3. 然后再将当前会议的结束时间加入到Queue中
     */
    public static int minMeetingRooms(List<Interval> intervals) {
        Collections.sort(intervals, (a, b) -> a.start - b.start); // 1. sort by start time
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 2. store the "end-time" of meeting, so minimum end-time on top

        for(Interval i : intervals){
            // if the end time of previous meeting earlier than the start time of current meeting means no conflict
            if(!pq.isEmpty() && pq.peek() <= i.start){ // here is different with car-pooling (while loop) as we need get the total number of rooms, so we only remove the meeting which can share same room with meetings
                pq.poll();
            }
            pq.add(i.end);
        }

        return pq.size();
    }

    // sweep line - treeMap solution
    public static int minMeetingRooms1(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return 0;

        TreeMap<Integer, Integer> times = new TreeMap<>();
        for (Interval i : intervals) {
            times.put(i.start, times.getOrDefault(i.start, 0) + 1);
            times.put(i.end, times.getOrDefault(i.end, 0) - 1);
        }
        int count = 0, res = 0;
        for (int c : times.values()) {
            count += c;
            res = Math.max(res, count);
        }
        return res;
    }
}

class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
