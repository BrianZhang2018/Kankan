package category.graph.ColorSolution;

import java.util.Stack;

/**
 * https://leetcode.com/problems/is-graph-bipartite/
 *
 * Created by brianzhang on 4/17/19.
 */
public class IsGraphBipartite {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(null);
        stack.push(null);

        System.out.println(stack.peek());

        System.out.println(stack.size());
    }

    public boolean isBipartite(int[][] graph) {
        if(graph == null || graph[0].length ==0){
            return false;
        }

        int n = graph.length;
        int[] colors = new int[n]; //0 is unknown; 1 is red; -1 is blue

        for(int i = 0; i< n; i++){
            if(colors[i] == 0 && !dfs(graph, i, colors, 1)){
                return false;
            }
        }
        return true;
    }

    public boolean dfs(int[][] graph, int index, int[] colors, int color){
        colors[index] = color;
        for(int neighbor : graph[index]){
            if(colors[neighbor] == color){
                return false;
            }
            if(colors[neighbor] == 0 && !dfs(graph, neighbor, colors, -color)){
                return false;
            }
        }
        return true;
    }
}
