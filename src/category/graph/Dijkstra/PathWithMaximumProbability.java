package category.graph.Dijkstra;

import category.model.Pair;
import java.util.*;

/**
 * https://leetcode.com/problems/path-with-maximum-probability/
 *
 * Created by brianzhang on 4/14/21.
 */
public class PathWithMaximumProbability {
    public static void main(String[] args) {
        System.out.println(maxProbability(3, new int[][]{{0,1},{1,2},{0,2}}, new double[]{0.5,0.5,0.2}, 0, 2));
    }

    public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<Pair<Integer, Double>>> graph = new HashMap();
        for(int i=0; i<edges.length; i++){
            graph.putIfAbsent(edges[i][0], new ArrayList());
            graph.get(edges[i][0]).add(new Pair(edges[i][1], succProb[i]));
            graph.putIfAbsent(edges[i][1], new ArrayList());
            graph.get(edges[i][1]).add(new Pair(edges[i][0], succProb[i]));
        }

        PriorityQueue<double[]> q = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        q.add(new double[]{start, 1.0});
        Set<Integer> visited = new HashSet();
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- >0){
                double[] d = q.poll();
                int node = (int)d[0];
                double prob = d[1];

                if(node == end) return prob;

                if(visited.add(node) && graph.get(node) != null){
                    for(Pair<Integer, Double> p : graph.get(node)){
                        q.add(new double[]{p.getKey(), prob * p.getValue()});
                    }
                }
            }
        }

        return 0.0;
    }
}
