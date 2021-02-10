package category.RightDataStructure.design;

/**
 * https://leetcode.com/problems/valid-sudoku/
 *
 * Created by brianzhang on 7/26/20.
 */
public class ValidSuduko {

    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return true;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    if (!isValid(board, i, j, board[i][j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num && i != col) {
                return false;
            }

            if (board[i][col] == num && i != row) {
                return false;
            }
            int m = row/3*3 + i/3, n = col/3*3 + i%3; // Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
            if (m == row && n == col) {
                continue;
            } else {
                if (board[m][n] == num) return false;
            }
        }
        return true;
    }
}
