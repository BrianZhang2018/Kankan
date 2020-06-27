package category.PriorityQueue.overlapInterval;

import java.util.*;

/**
 * https://leetcode.com/problems/merge-intervals/
 *
 * Created by brianzhang on 1/15/19.
 */
public class MergeInterval {
    public static void main(String[] args) {
        Interval v1 = new Interval(1, 3), v2 = new Interval(2, 6), v3 = new Interval(8, 10);
        List<Interval> intervals = Arrays.asList(v1, v2, v3);
        for (Interval i : merge(intervals)) System.out.println(i.start + " - " + i.end);
        for (Interval i : merge1(intervals)) System.out.println(i.start + " - " + i.end);
    }

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals.size() == 0) return res;

        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        Interval prev = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            if (prev.end >= intervals.get(i).start) {  // template step: find overlap
                prev.end = Math.max(prev.end, intervals.get(i).end);
            } else {
                res.add(prev);
                prev = intervals.get(i);
            }
        }
        res.add(prev);

        return res;
    }

    // foreach loop for fun
    public static List<Interval> merge1(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals.size() == 0) return res;

        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        Interval temp = new Interval(Integer.MAX_VALUE, Integer.MIN_VALUE);

        for (Interval i : intervals) {
            if (temp.start == Integer.MAX_VALUE || temp.end >= i.start) {
                temp.end = Math.max(temp.end, i.end);
                temp.start = Math.min(temp.start, i.start);
            } else {
                res.add(temp);
                temp = i;
            }
        }

        res.add(temp);

        return res;
    }
}