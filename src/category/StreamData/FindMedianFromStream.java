package category.StreamData;

import java.util.*;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 * explanation reference: https://www.programcreek.com/2015/01/leetcode-find-median-from-data-stream-java/
 *
 * The median is the middle value in "an ordered integer list".
 * Created by brianzhang on 10/22/18.
 */
public class FindMedianFromStream {
    public static void main(String[] args) {
        FindMedianFromStream test = new FindMedianFromStream();
        test.addNum(1);
        test.addNum(2);
        System.out.println(test.findMedian());
        test.addNum(3);
        System.out.println(test.findMedian());
    }

    PriorityQueue<Integer> minHeap = new PriorityQueue();   // 大堆，store the num which greater than median
    PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder()); // 小堆, store the num which less than median, so median is on the top if it's an odd list.

    // O(logN)
    public void addNum(int num) {
        maxHeap.offer(num); //
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()) // balance，(maxHeap)小堆的size始终保证比大堆size大 1
            maxHeap.offer(minHeap.poll());
    }

    // O(1)
    public double findMedian() {
        if (maxHeap.size() == minHeap.size())
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        else
            return maxHeap.peek();
    }

    // 反过来写，同样work
    /*public void addNum(int num) {
        minHeap.add(num);
        maxHeap.add(minHeap.poll());

        if(minHeap.size() < maxHeap.size()){
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian() {
        if(minHeap.size() == maxHeap.size()){
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }

        return minHeap.peek();
    }*/
}
