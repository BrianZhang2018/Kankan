package category.DataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * PriorityQueue
 * 
 * Created by brianzhang on 10/7/18.
 */
public class CheapestFlight {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap();
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) {
                prices.put(f[0], new HashMap<Integer, Integer>());
            }
            prices.get(f[0]).put(f[1], f[2]);
        }

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        queue.add(new int[]{0, src, k + 1});

        while (!queue.isEmpty()) {
            int[] temp = queue.remove();
            int price = temp[0];
            int city = temp[1];
            int stops = temp[2];

            if (city == dst)
                return price;

            if (stops > 0) {
                Map<Integer, Integer> flight = prices.getOrDefault(city, new HashMap());
                for (int key : flight.keySet()) {
                    queue.add(new int[]{price + flight.get(key), key, stops - 1});
                }
            }
        }
        return -1;
    }
}
