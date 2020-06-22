package category.PriorityQueue.overlapInterval;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/car-pooling/
 *
 * Similar with MeetingRoomII
 * 关键点就是 compare last end-time with start-time of current
 * 看有没有conflict, 有的话就是累加，例如连个room，负责就可以利用当前的room，或者可以把之前的去掉
 *
 * Created by brianzhang on 6/19/20.
 */
public class CarPolling {

    public static void main(String[] args) {
        System.out.println(carPooling(new int[][]{{2,7,8},{1,2,3},{3,2,3},{1,1,7},{9,1,2},{10,5,6}},12));
        System.out.println(carPoolingMap(new int[][]{{2,1,5},{3,3,7}},4));
    }

    public static boolean carPooling(int[][] trips, int capacity) {

        Arrays.sort(trips, (a, b) -> a[1] - b[1]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2]-b[2]);
        int count = 0;
        for(int[] trip : trips) {

            while(!pq.isEmpty() && pq.peek()[2]<=trip[1]){
                count-= pq.poll()[0];
            }

            if(count + trip[0] >capacity) return false;

            count+=trip[0];
            pq.add(trip);
        }

        return count<=capacity ? true : false;
    }

    public static boolean carPoolingMap(int[][] trips, int capacity) {
        Map<Integer, Integer> m = new TreeMap<>(); //sort the time by ascending
        for (int[] t : trips) {
            m.put(t[1], m.getOrDefault(t[1], 0) + t[0]); // 上车加人
            m.put(t[2], m.getOrDefault(t[2], 0) - t[0]); // 下车减人
        }
        for (Integer integer : m.keySet()) {
            System.out.println(integer);
        }
        for (int v : m.values()) {
            //System.out.println(v);
            capacity -= v;
            if (capacity < 0) {
                return false;
            }
        }
        //System.out.println(capacity);
        return true;
    }
}
