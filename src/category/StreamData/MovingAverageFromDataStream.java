package category.StreamData;

import java.util.*;

/**
 * https://leetcode.com/problems/moving-average-from-data-stream/
 *
 * Input
     ["MovingAverage", "next", "next", "next", "next"]
     [[3], [1], [10], [3], [5]]
     Output
     [null, 1.0, 5.5, 4.66667, 6.0]

  Explanation
     MovingAverage movingAverage = new MovingAverage(3);
     movingAverage.next(1); // return 1.0 = 1 / 1
     movingAverage.next(10); // return 5.5 = (1 + 10) / 2
     movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
     movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
     * Created by brianzhang on 3/25/21.
 */
public class MovingAverageFromDataStream {

    private int size = 0, windowSum = 0, count = 0;
    private Queue<Integer> queue = new LinkedList();

    /** Initialize your data structure here. */
    public MovingAverageFromDataStream(int size) {
        this.size = size;
    }

    public double next(int val) {
        count++;
        queue.offer(val);
        windowSum += val;

        int tail = count>size ? queue.poll() : 0;

        windowSum-=tail;

        return windowSum * 1.0 / (count >size ? size : count);
    }
}
