package category.PriorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;
/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 * 
 * Created by brianzhang on 10/22/18.
 */
public class FindmedianFromStream {

    public static void main(String[] args) {

        FindmedianFromStream test = new FindmedianFromStream();
        test.addNum(1);
        test.addNum(4);
    }

     //the num which greater than median
     PriorityQueue<Integer> minHeap = new PriorityQueue();
     //the num which less than median, so median is on the top.
     PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder());

    // Adds a number into the data structure.
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size())
            maxHeap.offer(minHeap.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (maxHeap.size() == minHeap.size())
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        else
            return maxHeap.peek();
    }
}
