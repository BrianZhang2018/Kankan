package category.BacktracingDFS;

import java.util.*;

/**
 * https://leetcode.com/problems/all-paths-from-source-to-target/
 *
 * Bloomberg
 * Created by brianzhang on 6/9/20.
 */
public class AllPathsFromSourceToTarget {

    public static void main(String[] args) {

    }

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length-1;

        dfs(graph, 0, n, new ArrayList<>());

        return res;
    }

    public void dfs(int[][] graph, int start, int end, List<Integer> temp){

        if(start == end){
            res.add(new ArrayList(temp));
            return;
        }

        if(start == 0)
            temp.add(0);

        for(int i=0; i<graph[start].length; i++){
            temp.add(graph[start][i]);
            dfs(graph, graph[start][i], end, temp);
            temp.remove(temp.size()-1);
        }
    }

}
