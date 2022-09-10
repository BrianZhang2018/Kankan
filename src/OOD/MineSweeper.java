package OOD;

/**
 * https://leetcode.com/problems/minesweeper/
 *
 *  https://www.youtube.com/watch?v=UVrdP1CLDr4
 *  condition-3: will stop if found adjacent mine
 *
 *  check 8 adjacent directions
 *      a. if no mine found, recursively traverse the 'E'
 *      b. if found mine, return (stop)
 *
 * Created by brianzhang on 8/18/20.
 */
public class MineSweeper {
    // 8 adjacent directions
    int[][] dict = new int[][]{{1, 0}, {-1, 0}, {-1, -1}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}};
    // dfs
    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0], c = click[1];
        int m = board.length, n = board[0].length;
        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }
        int num = 0;
        for (int[] d : dict) {
            int nr = d[0] + r;
            int nc = d[1] + c;
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M')
                num++;
        }

        if (num > 0) {
            board[r][c] = (char) (num + '0');
            return board;
        }

        if (num == 0) board[r][c] = 'B';

        for (int[] d : dict) {
            int nr = d[0] + r;
            int nc = d[1] + c;
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E')
                updateBoard(board, new int[]{nr, nc});
        }

        return board;
    }
}
