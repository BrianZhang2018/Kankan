package category.PriorityQueue.overlapInterval;

import sun.jvm.hotspot.jdi.ArrayReferenceImpl;

import java.util.*;

/**
 * https://leetcode.com/problems/employee-free-time/
 * Use Merge Interval solution to solve this problem (MergeInterval.java)
 *
 * 1. Merge interval
 * 2. Collect the Gap time
 *
 * Created by brianzhang on 8/2/20.
 */
public class EmployeeFreeTime {

    public static void main(String[] args) {
        Interval i1 = new Interval(1, 2), i2 = new Interval(5, 6), i3 = new Interval(1, 3), i4 = new Interval(4, 10);
        List<Interval> l1 = Arrays.asList(i1, i2), l2 = Arrays.asList(i3), l3 = Arrays.asList(i4);
        List<List<Interval>> busySlots = Arrays.asList(l1, l2, l3);

        for (Interval i : employeeFreeTime(busySlots)) {
            System.out.println("startTime: " + i.start + " endTime: " + i.end);
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.pollFirst());

        ArrayList<Integer> list = new ArrayList<>();
    }

    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        List<Interval> freeSlots = new ArrayList<>();
        List<Interval> busySlots = new ArrayList<>();
        for (List<Interval> l : schedule)
            busySlots.addAll(l);

        Collections.sort(busySlots, (a, b) -> a.start - b.start);

        for (int i = 1; i < busySlots.size(); i++) {
            Interval prev = busySlots.get(i - 1);
            Interval curr = busySlots.get(i);

            if (prev.end >= busySlots.get(i).start) {
                curr.end = Math.max(curr.end, prev.end);
                curr.start = prev.start;
            } else {
                freeSlots.add(new Interval(prev.end, curr.start));
            }
        }

        return freeSlots;
    }
}
