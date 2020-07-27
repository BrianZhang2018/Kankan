package category.BacktracingDFS;
/**
 * https://leetcode.com/problems/sudoku-solver/
 */
public class SudokuSolver{
    public static void main(String[] args) {
        System.out.println(1/3);
    }

    public void solveSudoku(char[][] board) {
        dfs(board, 0);
    }
    //返回值不是void的backtracking, compare with SubsetsII and Permutation. 这个递归和wordsearch相似。
    public boolean dfs(char[][] board, int d) {
        if(d == 81) //exit if filled all cells - "9X9=81"
            return true;
        int i= d/9, j= d%9; // get the index of next cell
        if (board[i][j] != '.')
            return dfs(board, d+1);
        
        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, i, j, c)) {
                board[i][j] = c;
                if (dfs(board, d+1)) //move to next cell 
                    return true;
            }
        }
        board[i][j] = '.'; //reset the status
        return false;
    }

    public boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false;
            if (board[i][col] == c) return false;
            if (board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] == c) return false;
        }
        return true;
    }

}