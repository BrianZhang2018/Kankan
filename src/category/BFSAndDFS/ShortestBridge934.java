package category.BFSAndDFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/shortest-bridge/
 * 
 * Keys: DFS and BFS
 * Solution: DFS flip 1 to 2 for first island, then do minimum step by BFS
 * 
 * Created by brianzhang on 4/14/19.
 */
public class ShortestBridge934 {

    public static void main(String[] args) {
        ShortestBridge934 test = new ShortestBridge934();
        System.out.println(test.shortestBridge(new int[][]{{0,1,0},{0,0,0},{0,1,1}}));
    }
    public int shortestBridge(int[][] A) {

        if(A == null || A.length == 0)
            return 0;

        int m = A.length;
        int n = A[0].length;
        boolean found1 = false;
        Queue<int[]> queue = new LinkedList<int[]>();

        //1. DFS: first, Find a connected island
        //2. add left '1' nodes's coordinate belong to another island into the queue
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(A[i][j] == 1 && !found1){
                    dfs(A, i, j);
                    found1 = true;
                }
                if(found1 && A[i][j] == 1){
                    queue.add(new int[]{i, j});
                }
            }
        }
        int[][] dicts = new int[][]{{1,0}, {0,1}, {-1, 0}, {0, -1}} ;
        int level = 0;
        //Find shortest path by BFS
        while(!queue.isEmpty()){
            int size = queue.size(); //another island's coordinates
            for(int i=0; i<size; i++){
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];

                for(int[] dict : dicts){
                    int nr = r + dict[0];
                    int nc = c + dict[1];

                    if(nr<0 || nc<0 || nr >= m || nc >= n || A[nr][nc] == 1)
                        continue;

                    if(A[nr][nc] == 2){
                        return level;
                    }else if(A[nr][nc] == 0){
                        A[nr][nc] = 1;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public void dfs(int[][] arr, int row, int column){
        if(row <0 || column <0||row>=arr.length || column>=arr[0].length || arr[row][column] == 0)
            return;

        if(arr[row][column] == 1){
            arr[row][column] = 2;
            dfs(arr, row +1, column);
            dfs(arr, row, column+1);
            dfs(arr, row-1, column);
            dfs(arr, row, column-1);
        }
    }
}
