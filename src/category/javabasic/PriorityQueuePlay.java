package category.javabasic;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by brianzhang on 4/25/19.
 */
public class PriorityQueuePlay {
    public static void main(String[] args) {
        PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.add(2.0);
        pq.add(3.0);
        pq.add(1.0);
        pq.add(5.0);

        while (pq.size() > 0) {
            System.out.println(pq.poll());
        }
    }
}
