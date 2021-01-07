package category.BFS.graph;

import java.util.*;

/**
 * https://leetcode.com/problems/network-delay-time/
 *
 * Dijkstra's algorithm (or Dijkstra's Shortest Path First algorithm, SPF algorithm) is an algorithm for finding the shortest paths between nodes in a graph, which may represent, for example, road networks.
 *
 * Created by brianzhang on 4/7/19.
 */
public class NetworkDelayTime {

    public static void main(String[] args) {
        //int[][] times = new int[][]{{2,1,5}, {2,3,1}, {3,1,1}, {3,4,1}};
        //int[][] times = new int[][]{{1,2,1}, {2,3,2}, {1,3,2}};
        int[][] times = new int[][]{{1,2,1}, {1,3,2}, {2,4,1}, {4,3,1}};
        System.out.println(networkDelayTime(times, 4, 1));
    }

    public static int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Map<Integer,Integer>> map = new HashMap<>();
        for(int[] time : times){
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }

        // int[]{distance, node}, sort distance as ascending order, so the shortest path will always be selected firstly
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        pq.add(new int[]{0, K});

        boolean[] visited = new boolean[N+1];
        int res = 0;

        while(!pq.isEmpty()){
            int[] curr = pq.remove();
            int node = curr[1], distance = curr[0];
            if(visited[node]) continue;

            visited[node] = true;
            res = distance;
            N--;
            if(map.containsKey(node)){
                for(int next : map.get(node).keySet()){
                    pq.add(new int[]{distance + map.get(node).get(next), next});
                }
            }
        }
        return N == 0 ? res : -1;
    }
}
