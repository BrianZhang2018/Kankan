package category.BFSAndDFS;

/**
 * Solved this by myself referring the NumsOfIsland question
 * /Users/brianzhang/workspace/WaWahaha/src/category/DFS/NumsOfIsland.java
 *
 * https://leetcode.com/problems/surrounded-regions/discuss/41708/JAVA-Easy-Version-To-UnderStand!!!!!!!!!!!!
 *
 * Created by brianzhang on 4/5/19.
 */
public class SurroundedRegionsDFS {
    public void solve(char[][] board) {
        if(board == null || board.length == 0)
            return;
        int rows = board.length;
        int columns = board[0].length;
        //Firstly, turn all board node from the 'O' to 'B' and also turn their
        //adjacent node by leveraging the dfsHelper to traversal
        for(int i=0;i < rows; i++){
            for(int j=0; j< columns; j++){
                if(i== 0 || i == rows-1 || j==0 || j == columns-1 && (board[i][j] == 'O') ){
                    dfs(i, j, board);
                }
            }
        }
        //Then, flipping the node in matrix
        for(int i=0;i < rows; i++){
            for(int j=0; j< columns; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                if(board[i][j] == 'B'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(int i, int j, char[][] board){
        if(i<0 || j<0 || i>board.length-1 || j>board[0].length-1)
            return;
        if(board[i][j] == 'O'){
            board[i][j] = 'B';
            dfs(i+1, j, board);
            dfs(i-1, j, board);
            dfs(i, j+1, board);
            dfs(i, j-1, board);
        }
    }
}
