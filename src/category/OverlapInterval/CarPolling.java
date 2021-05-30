package category.OverlapInterval;

import java.util.*;

/**
 * 1. Sort by start-time
 * 2. Check overlap: compare the minimum end-time with current start-time, and update the minimum end-time when need
 *
 * https://leetcode.com/problems/car-pooling/
 * 关键点就是 compare "previous end-time" with "start-time of current". 看有没有conflict, 有的话就是累加
 *
 * Created by brianzhang on 6/19/20.
 */
public class CarPolling {

    public static void main(String[] args) {
        System.out.println(carPooling(new int[][]{{2, 7, 8}, {1, 2, 3}, {3, 2, 3}, {1, 1, 7}, {9, 1, 2}, {10, 5, 6}}, 12));
        System.out.println(carPoolingMap(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4));
    }

    public static boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> a[1] - b[1]); // sort by start-time
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // store the end-time, and keep the minimum end-time on top

        for (int[] trip : trips) {
            while (!pq.isEmpty() && pq.peek()[2] <= trip[1]) {
                capacity += pq.poll()[0];
            }

            capacity -= trip[0];
            if (capacity < 0) return false;
            pq.add(trip);
        }

        return true;
    }

    // Map solution - good idea
    public static boolean carPoolingMap(int[][] trips, int capacity) {
        Map<Integer, Integer> m = new TreeMap<>(); // sort the time by ascending - TreeMap
        for (int[] t : trips) {
            m.put(t[1], m.getOrDefault(t[1], 0) + t[0]); // 上车加人
            m.put(t[2], m.getOrDefault(t[2], 0) - t[0]); // 下车减人
        }
      /*  for (Integer integer : m.keySet()) {
            System.out.println(integer + " " + m.get(integer));
        }*/
        for (int v : m.values()) {
            capacity -= v;
            if (capacity < 0) {
                return false;
            }
        }

        return true;
    }
}
