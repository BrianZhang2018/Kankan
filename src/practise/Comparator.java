package practise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 11/25/18.
 */
public class Comparator {

    public static void main(String[] args) {
        Interval source1 = new Interval(9, 10);
        Interval source = new Interval(9, 77);
        Interval source2 = new Interval(10, 12);

        List<Interval> tests = new ArrayList<>();
        tests.add(source);
        tests.add(source1);
        tests.add(source2);

        tests.sort(new java.util.Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

    }
}

class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
