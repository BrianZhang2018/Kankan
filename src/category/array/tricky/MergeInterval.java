package category.array.tricky;

import java.util.*;

/**
 * https://leetcode.com/problems/merge-intervals/
 *
 * Created by brianzhang on 1/15/19.
 */
public class MergeInterval {

    public static void main(String[] args) {
        Interval v1 = new Interval(1, 3);
        Interval v2 = new Interval(2, 6);
        Interval v3 = new Interval(8, 10);
        List<Interval> intervals = Arrays.asList(v1, v2, v3);
        for(Interval i : merge(intervals))
            System.out.println(i.start + " - " + i.end);
    }

    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() == 0) {
            return new ArrayList<>();
        }
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        List<Interval> res = new ArrayList<>();
        Interval cur = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start <= cur.end) {
                cur.end = Math.max(cur.end, intervals.get(i).end);
            } else {
                res.add(cur);
                cur = intervals.get(i);
            }
        }
        res.add(cur);
        return res;
    }
}

class Interval {
    int start;
    int end;

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
