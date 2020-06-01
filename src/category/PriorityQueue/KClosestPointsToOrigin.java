package category.PriorityQueue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 *
 * Created by brianzhang on 5/27/20.
 */
public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        int[][] points = new int[][]{{1,3},{-2,2}};
        for(int[] res : kClosest(points, 1)){
            System.out.println(Arrays.toString(res));
        }
    }

    // PriorityQueue solution
    public static int[][] kClosest(int[][] points, int K) {
        if(K == points.length) return points;

        PriorityQueue<int[]> pq = new PriorityQueue<>(K, (a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1])); // reverse order

        for(int[] point: points) {
            pq.add(point);
            if(pq.size() > K)
                pq.poll();
        }
        return pq.toArray(new int[0][0]);
    }

    // 看着玩
    public int[][] kClosest1(int[][] points, int k) {
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
}
