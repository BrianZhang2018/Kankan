package category.graph;

import java.util.*;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/solution/
 *
 To find the Eulerian path, inspired from the original Hierzolher's algorithm, we simply change one condition of loop, rather than stopping at the starting point,
 we stop at the vertex where we do not have any unvisited edges.

 To summarize, the main idea to find the Eulerian path consists of two steps:

 Step 1). Starting from any vertex, we keep following the unused edges until we get stuck at certain vertex where we have no more unvisited outgoing edges.

 Step 2). We then backtrack to the nearest neighbor vertex in the current path that has unused edges and we repeat the process until all the edges have been used.

 The first vertex that we got stuck at would be the end point of our Eulerian path. So if we follow all the stuck points backwards, we could reconstruct the Eulerian path at the end.
 * Created by brianzhang on 3/11/21.
 */
public class ReconstructItinerary {

    public static void main(String[] args) {
        List<String> l1 = Arrays.asList("JFK","KUL"), l2 = Arrays.asList("JFK","NRT"), l3 = Arrays.asList("NRT","JFK");
        System.out.println(new ReconstructItinerary().findItinerary(Arrays.asList(l1,l2,l3)));
    }

    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;

    public List<String> findItinerary(List<List<String>> tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        // build the graph
        for (List<String> ticket : tickets) {
            flights.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            flights.get(ticket.get(0)).add(ticket.get(1));
        }
        dfs("JFK");
        return path;
    }

    // dfs + backtracking
    public void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty()){
            dfs(arrivals.poll());
        }
        path.addFirst(departure);
    }
}
