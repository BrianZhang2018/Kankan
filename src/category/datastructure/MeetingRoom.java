package category.datastructure;

import java.util.*;

/**
 * Created by brianzhang on 10/24/18.
 */
public class MeetingRoom {
    public static void main(String[] args) {
        Interval source1 = new Interval(9, 10);
        Interval source = new Interval(9, 77);
        Interval source2 = new Interval(10, 12);

        List<Interval> tests = new ArrayList<>();
        tests.add(source);
        tests.add(source1);
        tests.add(source2);

        tests.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        //System.out.println(minMeetingRooms(tests));
     /*   for (Interval test : tests) {
            System.out.println(test.start);
        }*/
    }

    public static int minMeetingRooms(List<Interval> intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (Interval i : intervals) {
            map.put(i.start, map.getOrDefault(i.start, 0) + 1);
            map.put(i.end, map.getOrDefault(i.end, 0) - 1);
        }
        int room = 0;
        int max = 0;
        for (int num : map.keySet()) {
            room += num;
            max = Math.max(max, room);
        }
        return max;
    }
}

class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
