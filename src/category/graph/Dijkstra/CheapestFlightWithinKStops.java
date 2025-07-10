package category.graph.Dijkstra;

import java.util.*;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 *
 * PreRequisites对于这种问题:
 * 1. all nodes connected（否则如果当前node不能reach dest node，就不得不往回飞，在飞去dest
 * node，这样就和题目的目的相悖了）
 *
 * 解法：BFS (get shortest path) + MinHeap (get least cost)
 *
 * Created by brianzhang on 10/7/18.
 */
public class CheapestFlightWithinKStops {
    public static void main(String[] args) {
        // [from, to, price]
        int[][] flights = new int[][] { { 0, 1, 10 }, { 0, 2, 50 }, { 1, 2, 10 } };
        System.out.println(findCheapestPrice(3, flights, 0, 2, 1));
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // build flight graph - from -> [[to, price], ...]  
        Map<Integer, Map<Integer, Integer>> adjacent = new HashMap();
        for (int[] f : flights) {
            adjacent.computeIfAbsent(f[0], key -> new HashMap()).put(f[1], f[2]);
        }

        // minHeap - always poll the cheapest flight
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        queue.add(new int[] { 0, src, k + 1 }); // initially, [cost,  start node, max stops]
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int costSum = node[0], fromeCity = node[1], remainingStops = node[2];

            if (fromeCity == dst) return costSum;

            if (remainingStops > 0) {
                Map<Integer, Integer> adjacentFlights = adjacent.getOrDefault(fromeCity, new HashMap());
                for (int next : adjacentFlights.keySet()) {
                    queue.add(new int[]{costSum + adjacentFlights.get(next), next, remainingStops - 1});
                }
            }
        }
        return -1;
    }
}
