package category.graph.Dijkstra;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Dijkstra algorithm, and the biggest shortest path which is the total need time for all
 * nodes got visited.
 *
 * Created by brianzhang on 4/7/19.
 */
public class NetworkDelayTime {

    public static void main(String[] args) {
        NetworkDelayTime test = new NetworkDelayTime();

        int[][] times = new int[][]{{2,1,5}, {2,3,1}, {3,1,1}, {3,4,1}};
        System.out.println(test.networkDelayTime(times, 4, 2));
    }
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Map<Integer,Integer>> map = new HashMap<>();
        for(int[] time : times){
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }

        //(distance, node) add into pq
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        pq.add(new int[]{0, K});

        boolean[] visited = new boolean[N+1];
        int res = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.remove();
            int curNode = cur[1];
            int curDist = cur[0];
            if(visited[curNode]) continue;
            visited[curNode] = true;
            res = curDist;
            N--;
            if(map.containsKey(curNode)){
                for(int next : map.get(curNode).keySet()){
                    pq.add(new int[]{curDist + map.get(curNode).get(next), next});
                }
            }
        }
        return N == 0 ? res : -1;
    }
}
