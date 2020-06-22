package category.PriorityQueue.overlapInterval;

import java.util.*;

/**
 * https://www.lintcode.com/problem/meeting-rooms-ii
 * https://leetcode.com/problems/car-pooling/
 *
 * Bloomberg
 *
 * Similar with CarPolling
 * 关键点就是 compare last end-time with start-time of current
 * 看有没有conflict, 有的话就是累加，例如连个room，负责就可以利用当前的room，或者可以把之前的去掉
 * Created by brianzhang on 10/24/18.
 */
public class MeetingRoomII {
    public static void main(String[] args) {
        Interval source = new Interval(9, 30);
        Interval source1 = new Interval(9, 10);
        Interval source2 = new Interval(10, 12);

        System.out.println(minMeetingRooms(Arrays.asList(source, source1, source2)));
    }
    /**
     * 循环遍历每个会议的时间段，对于任意一个当前会议，是否需要增加一间新的会议室取决于"早于它开始的其他会议是否已经结束"。
     * 因此，我们先查看Queue中其他会议的结束时间是否小于等于当前会议的开始时间，将相比于当前会议开始时间点已经结束的会议从Queue中删除，
     * 然后再将当前会议的结束时间加入到Queue中
     *
     * 参考思路，解法就算了
     * https://leetcode.jp/leetcode-253-meeting-rooms-ii-%e8%a7%a3%e9%a2%98%e6%80%9d%e8%b7%af%e5%88%86%e6%9e%90/
     */
    public static int minMeetingRooms(List<Interval> intervals) {
        // Write your code here
        Collections.sort(intervals, (a, b) -> a.start - b.start); // sort by start time
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // store the end time of meeting

        for(Interval i : intervals){
            if(!pq.isEmpty() && pq.peek() <= i.start){
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
