package category.PriorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by brianzhang on 10/22/18.
 */
public class FindmedianFromStream {

    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();//heap is a minimal heap by default
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());//change to a maximum heap

    // Adds a number into the data structure.
    public static void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size())
            maxHeap.offer(minHeap.poll());
    }

    // Returns the median of current data stream
    public static double findMedian() {
        if (maxHeap.size() == minHeap.size())
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        else
            return maxHeap.peek();
    }

    public static void main(String[] args) {
        addNum(1);
        addNum(4);
    }

}
