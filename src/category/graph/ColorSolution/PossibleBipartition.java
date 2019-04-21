package category.graph.ColorSolution;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/possible-bipartition/
 *
 * Created by brianzhang on 4/18/19.
 */
public class PossibleBipartition {

    //定义图的数组类型为ArrayList<Integer>[]graph的数组
    ArrayList<Integer>[] graph;
    //定义染色的调色盘
    int [] colors;

    public boolean possibleBipartition(int N, int[][] dislikes) {
        //初始化graph，为图的每一vertex建立一个list，这个list存储所有的neighbor vertex
        graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            graph[i] = new ArrayList<Integer>();
        }
        //构建图
        for(int[] edge: dislikes){//遍历二维数组中每一个一维数组
            //因为每一个一维数组在这道题目里面都只有两个值，因而只用保证两个连同分量的相互关联
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        //initiate 调色盘
        colors = new int[N+1];

        //开始染色
        for(int i=1;i<N+1;i++){
            //如果有冲突说明不是二分的图
            if(colors[i]==0&&!dfs(i,1))return false;
        }
        return true;
    }
    public boolean dfs(int cur, int color){
        colors[cur]=color;
        for(int nxt: graph[cur]){
            if(colors[nxt]==color)return false;//如果下一neighor节点已经染色，并且颜色和现在节点相同，则false
            if(colors[nxt]==0 && !dfs(nxt,-color))return false;
            //如果当前节点可以染色，当前节点的下一节点冲突则为false；
        }
        return true;
    }
}
