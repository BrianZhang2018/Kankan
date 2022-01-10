package category.BFS;

import java.util.HashSet;

/**
 * https://leetcode.com/problems/game-of-life/
 *
 * Created by brianzhang on 7/6/21.
 */
public class GameOfLife {

    public static void main(String[] args) {
    }

    public void gameOfLife(int[][] board) {

        int m= board.length, n=board[0].length;

        int [][] res = new int[m][];
        for(int i = 0; i < m; i++)
            res[i] = board[i].clone(); // deep clone

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                board[i][j] = bfs(res, i, j);
            }
        }
    }

    public int bfs(int[][] board, int i, int j){
        int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0, -1}, {1,1},{1,-1}, {-1,-1},{-1,1}};

        int people = 0;

        for(int[] dir : dirs){
            int nx = dir[0] + i;
            int ny = dir[1] + j;
            if(nx<0 || nx>=board.length || ny<0 || ny>=board[0].length) continue;

            people+= board[nx][ny];
        }

        if(board[i][j] == 0 && people == 3) return 1;

        if(people < 2 || people > 3) return 0;

        return board[i][j];
    }
}
