package category.graph.Dijkstra;

import java.util.*;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 *
 * PreRequisites对于这种问题:
 * 1. all nodes connected（否则如果当前node不能reach dest node，就不得不往回飞，在飞去dest node，这样就和题目的目的相悖了）
 *
 * 解法：BFS (get shortest path) + MinHeap (get least cost)
 *
 * Created by brianzhang on 10/7/18.
 */
public class CheapestFlightWithinKStops {
    public static void main(String[] args) {
        int[][] flights = new int[][]{{0, 1, 10},
                                      {0, 2, 50},
                                      {1, 2, 10}};
        System.out.println(findCheapestPrice(3, flights, 0, 2, 1));
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // build graph
        Map<Integer, Map<Integer, Integer>> adjacent = new HashMap();
        for (int[] f : flights) {
            adjacent.putIfAbsent(f[0], new HashMap<>());
            adjacent.get(f[0]).put(f[1], f[2]);
        }

        // BFS + minHeap
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[0]- b[0]);
        queue.add(new int[]{0, src, k + 1});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int cost = node[0];
            int city = node[1];
            int stops = node[2];

            if (city == dst) return cost;

            if (stops > 0) {
                Map<Integer, Integer> adjacentFlights = adjacent.getOrDefault(city, new HashMap());
                for (int next : adjacentFlights.keySet()) {
                    queue.add(new int[]{cost + adjacentFlights.get(next), next, stops - 1});
                }
            }
        }
        return -1;
    }
}
