package category.SweepLine.overlapInterval;

import java.util.*;

/**
 * https://leetcode.com/problems/employee-free-time/
 * Use Merge Interval solution to solve this problem (MergeInterval.java)
 *
 * 1. Merge interval
 * 2. Collect the Gap (free) time
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
    }

    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        List<Interval> freeIntervals = new ArrayList<>(); // result
        List<Interval> busyIntervals = new ArrayList<>();

        for (List<Interval> l : schedule) busyIntervals.addAll(l);
        // sort by start time - step-1
        Collections.sort(busyIntervals, (a, b) -> a.start - b.start);

        for (int i = 1; i < busyIntervals.size(); i++) {
            Interval prev = busyIntervals.get(i - 1);
            Interval curr = busyIntervals.get(i);
            // check the overlap - step-2
            if (prev.end >= curr.start) {
                curr.end = Math.max(curr.end, prev.end);
                curr.start = prev.start;
            } else {
                freeIntervals.add(new Interval(prev.end, curr.start));
            }
        }

        return freeIntervals;
    }
}
