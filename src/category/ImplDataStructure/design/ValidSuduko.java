package category.ImplDataStructure.design;

import java.util.HashSet;

/**
 * https://leetcode.com/problems/valid-sudoku/
 *
 * what's a valid sudoku?
 * - Each row contains unique values from 1-9.
 * - Each column contains unique values from 1-9.
 * - Each of the 9 sub-squares, of size 3x3, contains a unique value from 1-9.
 *
 * Created by brianzhang on 7/26/20.
 */
public class ValidSuduko {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'.', '8', '.', '.', '.', '5', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '3', '4', '5', '7'},
                {'.', '.', '.', '.', '7', '.', '8', '.', '9'},
                {'.', '6', '.', '4', '.', '.', '9', '.', '3'},
                {'.', '.', '7', '.', '1', '.', '5', '.', '.'},
                {'4', '.', '8', '.', '.', '7', '.', '2', '.'},
                {'9', '.', '1', '.', '2', '.', '.', '.', '.'},
                {'8', '4', '2', '3', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '1', '.', '.', '.', '8', '.'}
        };
        System.out.println(new ValidSuduko().isValidSudoku(board));
    }

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
        System.out.println("row: " + row + "  " + "col: " + col);
        for (int i = 0; i < 9; i++) {
            // check repetition in row
            if (board[row][i] == num && i != col) return false;
            // check repetition in column
            if (board[i][col] == num && i != row) return false;
            // check 3x3 sub-boxes whether it has repetition
            int m = (row / 3) * 3 + i / 3, n = (col / 3) * 3 + i % 3;
            System.out.println("subBox row: " + m + "  " + "subBox col: " + n);
            if (m == row && n == col) {
                continue;
            } else if (board[m][n] == num) {
                return false;
            }
        }
        return true;
    }

    // Solution - 2
    public boolean isValidSudoku1(char[][] board) {
        int N = 9;
        // Use hash set to record the status
        HashSet<Character>[] rows = new HashSet[N];
        HashSet<Character>[] cols = new HashSet[N];
        HashSet<Character>[] boxes = new HashSet[N];
        for (int r = 0; r < N; r++) {
            rows[r] = new HashSet<>();
            cols[r] = new HashSet<>();
            boxes[r] = new HashSet<>();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                char val = board[r][c];
                // Check if the position is filled with number
                if (val == '.') {
                    continue;
                }
                // Check the row
                if (rows[r].contains(val)) {
                    return false;
                }
                rows[r].add(val);
                // Check the column
                if (cols[c].contains(val)) {
                    return false;
                }
                cols[c].add(val);
                // Check the box
                int idx = (r / 3) * 3 + c / 3;
                if (boxes[idx].contains(val)) {
                    return false;
                }
                boxes[idx].add(val);
            }
        }
        return true;
    }
}
