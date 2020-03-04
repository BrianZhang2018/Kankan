package category.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by brianzhang on 1/27/19.
 */
public class KClosetElements {

    public static void main(String[] args) {
        int[][] test = {{1, 3}, {-2, 2}};

        kClosest2(test, 1);
        System.out.println(test[0][0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(2);
        pq.add(1);
        pq.add(3);
        System.out.println("test pq " + pq.peek());
    }

    public int[][] kClosest(int[][] points, int k) {
        if (points == null)
            return null;

        Arrays.sort(points, (int[] p1, int[] p2) -> p1[0] * p1[0] + p1[1] * p1[1] - (p2[0] * p2[0] + p2[1] * p2[1]));

        int[][] res = new int[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < points[i].length; j++) {
                res[i][j] = points[i][j];
            }
        }

        return res;
    }

    public static int[][] kClosest2(int[][] points, int k) {
        if (points == null)
            return null;

        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] p2, int[] p1) ->
                p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);

        for (int i = 0; i < points.length; i++) {
            pq.offer(points[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[][] res = new int[k][2];
        for (int i = 0; i < pq.size(); i++) {
            res[0] = pq.poll();
        }

        return res;
    }
}
