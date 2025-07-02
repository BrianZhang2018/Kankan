package category.graph;

import java.util.*;

/**
 * Algorithm: Minimum Bus Transfers (BFS)
 * --------------------------------------
 * Given a list of bus routes, a source, and a target stop, this algorithm finds
 * the minimum number of buses needed to travel from source to target.
 *
 * Approach:
 * - Build a map from each bus stop to all bus routes passing through it.
 * - Use BFS, where each level represents taking one more bus.
 * - Track visited bus routes and stops to avoid redundant processing.
 * - For each stop, enqueue all reachable stops via unused bus routes.
 * - Return the number of buses taken when the target is reached, or -1 if unreachable.
 */
public class BusRoute {
    public static void main(String[] args) {
        int[][] routes = { { 1, 2, 7 }, { 3, 6, 7 } };
        int source = 1, target = 6;
        System.out.println(new BusRoute().numBusesToDestination(routes, source, target));
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        // Build a map of bus stops to bus routes
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                map.computeIfAbsent(routes[i][j], k -> new ArrayList<>()).add(i);
            }
        }
        Queue<Integer> stopQueue = new LinkedList<>();
        stopQueue.add(source);
        Set<Integer> visitedRoutes = new HashSet<>(); // Tracks bus routes already taken
        Set<Integer> visitedStops = new HashSet<>(); // Tracks stops already visited
        visitedStops.add(source);

        int step = 0;
        while (!stopQueue.isEmpty()) {
            int size = stopQueue.size();
            step++;
            while (size-- > 0) {
                int stop = stopQueue.poll();
                List<Integer> routesForStop = map.get(stop);
                for (int route : routesForStop) {
                    if (visitedRoutes.add(route)) { // Only process unvisited bus routes
                        for (int s : routes[route]) {
                            if (s == target) {
                                return step;
                            }
                            if (visitedStops.add(s)) {
                                stopQueue.add(s);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}
