package category.graph;

import java.util.*;

/**
 * Algorithm: Minimum Board-to-Pin Transfers (BFS)
 * ----------------------------------------------
 * Given a list of boards (routes), each containing pins (attractions), a source pin, and a target pin,
 * this algorithm finds the minimum number of board transfers needed to travel from source to target.
 *
 * Each board is a list of pins. You can transfer from one pin to another if they are on the same board.
 *
 * Example input:
 * ["Mountain", "Lake", "Lodge"]
 * ["Lake", "Painting"]
 * ["Painting", "Picasso"]
 *
 * Question: Can you reach 'Mountain' from 'Lake', and what is the minimum cost (number of board transfers)?
 */
public class BoardAndPinsRoute {
    public static void main(String[] args) {
        List<List<String>> routes = Arrays.asList(
            Arrays.asList("Mountain", "Lake", "Lodge"),
            Arrays.asList("Lake", "Painting"),
            Arrays.asList("Painting", "Picasso")
        );
        String source = "Lake", target = "Picasso";
        int cost = minTransfers(routes, source, target);
        if (cost != -1) {
            System.out.println("Can reach '" + target + "' from '" + source + "' with cost: " + cost);
        } else {
            System.out.println("Cannot reach '" + target + "' from '" + source + "'.");
        }
    }

    public static int minTransfers(List<List<String>> routes, String source, String target) {
        if (source.equals(target)) return 0;
        // Build a map from each attraction to all route indices it appears in
        Map<String, List<Integer>> attractionToRoutes = new HashMap<>();
        for (int i = 0; i < routes.size(); i++) {
            for (String attraction : routes.get(i)) {
                attractionToRoutes.computeIfAbsent(attraction, k -> new ArrayList<>()).add(i);
            }
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(source);
        Set<Integer> visitedRoutes = new HashSet<>();
        Set<String> visitedAttractions = new HashSet<>();
        visitedAttractions.add(source);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            while (size-- > 0) {
                String current = queue.poll();
                List<Integer> routesForAttraction = attractionToRoutes.getOrDefault(current, Collections.emptyList());
                for (int routeIdx : routesForAttraction) {
                    if (visitedRoutes.add(routeIdx)) {
                        for (String next : routes.get(routeIdx)) {
                            if (next.equals(target)) {
                                return steps;
                            }
                            if (visitedAttractions.add(next)) {
                                queue.add(next);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
} 