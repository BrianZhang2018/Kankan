package category.graph;

import java.util.*;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/
 *
 * In graph theory, an Eulerian trail (or Eulerian path) is a trail in a finite graph that visits every edge exactly once (allowing for revisiting vertices).
 *
 * The point that we got stuck would be the last airport that we visit. And then we follow the visited vertex (airport) backwards, we would obtain the final itinerary.
 *
 * Created by brianzhang on 3/11/21.
 */
public class ReconstructItinerary {

    public static void main(String[] args) {
        List<String> l1 = Arrays.asList("JFK","KUL"), l2 = Arrays.asList("JFK","NRT"), l3 = Arrays.asList("NRT","JFK");
        System.out.println(new ReconstructItinerary().findItinerary(Arrays.asList(l1,l2,l3)));

        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(6);
        ll.addFirst(10);
        System.out.println(ll.poll());

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(6);
        queue.offer(10);
        queue.offerFirst(11);
        queue.push(100);
        System.out.println(queue.poll());

    /*    ArrayDeque<Integer> queue1 = new ArrayDeque<>();
        queue1.push(6);
        queue1.push(10);

        System.out.println(queue1);*/
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

    public void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());

        path.addFirst(departure);
    }
}
