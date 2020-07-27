package category.graph.ColorSolution;

/**
 * https://leetcode.com/problems/is-graph-bipartite/
 *
 * https://en.wikipedia.org/wiki/Bipartite_graph#:~:text=In%20the%20mathematical%20field%20of,the%20parts%20of%20the%20graph.
 *
 * In the mathematical field of graph theory, a bipartite graph (or bigraph) is a graph
 * whose vertices can be divided into two disjoint and independent sets U and V such that every edge connects a vertex in U to one in V
 *
 * facebook
 * Created by brianzhang on 4/17/19.
 */
public class IsGraphBipartite {
    public static void main(String[] args) {
        IsGraphBipartite test = new IsGraphBipartite();
        System.out.println(test.isBipartite(new int[][]{{1,3}, {0,2}, {1,3}, {0,2}}));
    }

    public boolean isBipartite(int[][] graph) {
        if(graph == null || graph[0].length ==0){
            return false;
        }

        int n = graph.length;
        int[] nodesColor = new int[n]; // "0" hasn't been visited; "1" is red; "-1" is blue

        for(int i = 0; i< n; i++){
            if(nodesColor[i] == 0 && !dfs(graph, i, nodesColor, 1)){
                return false;
            }
        }
        return true;
    }

    public boolean dfs(int[][] graph, int index, int[] nodesColor, int color){
        nodesColor[index] = color;
        for(int neighbor : graph[index]){
            if(nodesColor[neighbor] == color){
                return false;
            }
            if(nodesColor[neighbor] == 0 && !dfs(graph, neighbor, nodesColor, -color)){
                return false;
            }
        }
        return true;
    }
}
