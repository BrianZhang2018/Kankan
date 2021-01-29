package category.graph.BipartiteGraph;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/possible-bipartition/
 *
 *  group[i] = 0 means node i hasn't been visited.
 *  group[i] = 1 means node i has been grouped to 1.
 *  group[i] = -1 means node i has been grouped to -1.
 *
 * Created by brianzhang on 4/18/19.
 */
public class PossibleBipartition {

    ArrayList<Integer>[] graph;
    int[] colors;

    public boolean possibleBipartition(int N, int[][] dislikes) {
        graph = new ArrayList[N+1];   //初始化graph，为图的每一vertex建立一个list，这个list存储所有的neighbor vertex
        for(int i=1;i<=N;i++){
            graph[i] = new ArrayList<>();
        }
        //构建图
        for(int[] edge: dislikes){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        colors = new int[N+1];

        //开始染色
        for(int i=1;i<N+1;i++){
            // 如果有冲突说明不是二分的图
            if(colors[i]==0 && !dfs(i,1)) return false;
        }
        return true;
    }
    public boolean dfs(int cur, int color){
        colors[cur]=color;
        for(int nxt: graph[cur]){
            if(colors[nxt]==color) return false; // 如果下一neighbor节点已经染色，并且颜色和现在节点相同，则false
            if(colors[nxt]==0 && !dfs(nxt,-color)) return false; // 如果当前节点可以染色，dfs看neighbor节点是否冲突
        }
        return true;
    }
}
