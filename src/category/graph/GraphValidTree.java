package category.graph;

import java.util.*;
/**
 * https://www.lintcode.com/problem/graph-valid-tree/description
 * https://yeqiuquan.blogspot.com/2017/03/lintcode-178-graph-valid-tree.html
 */
public class GraphValidTree{
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }
        
        if (edges.length != n - 1) {
            return false;
        }
        
        ArrayList<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < edges.length; i++) {
            adjList[edges[i][0]].add(edges[i][1]);
            adjList[edges[i][1]].add(edges[i][0]);
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        HashSet<Integer> hash = new HashSet<Integer>();
        queue.add(0);
        hash.add(0);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                hash.add(node);
                for (int j = 0; j < adjList[node].size(); j++) {
                    if (!hash.contains(adjList[node].get(j))) {
                        queue.offer(adjList[node].get(j));
                    }
                }
            }
        }
        
        return hash.size() == n;
    }
}