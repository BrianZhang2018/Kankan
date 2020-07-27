package category.graph.topologicalsort;

import java.util.ArrayList;
import java.util.*;

/**
 * https://leetcode.com/problems/minimum-height-trees/discuss/76055/Share-some-thoughts
 * <p>
 * Our problem want us to find the minimum height trees and return their root labels. First we can think about a simple case -- a path graph.
 * For a path graph of n nodes, find the minimum height trees is trivial. Just designate the "middle point(s)" as roots.
 * <p>
 * Created by brianzhang on 4/19/19.
 */
public class MinimumHeightTrees {
    public static void main(String[] args) {
        System.out.println(findMinHeightTrees(11, new int[][]{{0, 1}, {0, 2}, {2, 3}, {0, 4}, {2, 5}, {5, 6}, {3, 7}, {6, 8}, {8, 9}, {9, 10}}));
        //System.out.println(findMinHeightTrees2(6, new int[][] { { 0, 3 }, { 1, 3 }, { 2, 3 }, { 4, 3 }, { 5, 4 } }));
    }

    //better way to List as the structure instead of the array to store the adjacent relationship of nodes
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);

        List<Set<Integer>> graph = new ArrayList<>(n);
        List<Integer> leaves = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int i : leaves) {
                int incomingEdgeV = graph.get(i).iterator().next();
                graph.get(incomingEdgeV).remove(i);

                if (graph.get(incomingEdgeV).size() == 1)
                    newLeaves.add(incomingEdgeV);
            }
            leaves = newLeaves;
        }
        return leaves;
    }

    //solution-2 use array to store the adjacent relationship
    public static List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (edges == null) return res;

        int[] degree = new int[n];
        ArrayList<Integer>[] graph = new ArrayList[n];
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            degree[edge[1]]++;
            degree[edge[0]]++;
        }

        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 1) {
                leaves.add(i);
            }
        }

        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int i : leaves) {
                int incomingEdgeV = graph[i].get(0); //get the vertex of incoming edge of the leaf vertex, get(0) as the leaf only has one incoming edge
                //remove the leaf edge from incoming vertex side, e.g 0 is the leaf vertex, 0->4, 4->0, here is remove later one 
                //as the first one in the curr leaves which is discarded after the curr loop                
                graph[incomingEdgeV].remove(new Integer(i));
                degree[incomingEdgeV]--;
                //add it if it's a leaf edge
                if (degree[incomingEdgeV] == 1)
                    newLeaves.add(incomingEdgeV);
            }

            leaves = newLeaves;
        }
        return leaves;
    }
}
