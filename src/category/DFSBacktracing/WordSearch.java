package category.DFSBacktracing;

/**
 * https://leetcode.com/problems/word-search/
 * DFS here, don't use BFS will have the visited state issue
 *
 * TC: O(M*N*4^L) - M*N is the size of the board, L is the length of the word (因为每一个character都可以走4个方向在matrix里, so 4^L)
 * SC: O(L), L is the length of the word to be matched.
 *
 * The main consumption of the memory lies in the recursion call of the backtracking function.
 * The maximum length of the call stack would be the length of the word. Therefore, the space complexity of the algorithm is O(L).
 */
public class WordSearch {
    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(ws.exist(board, "ABCCED"));
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) { // can omit this
                    if (dfs(i, j, board, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfs(int row, int col, char[][] board, String word, int index) {
        if (index == word.length()) return true;

        if (row >= board.length || row < 0 || col >= board[0].length || col < 0
                || board[row][col] != word.charAt(index) || board[row][col] == '*') {
            return false;
        }
        board[row][col] = '*';
        boolean exist = dfs(row, col + 1, board, word, index + 1)
                || dfs(row, col - 1, board, word, index + 1)
                || dfs(row + 1, col, board, word, index + 1)
                || dfs(row - 1, col, board, word, index + 1);

        board[row][col] = word.charAt(index); // reset value - backtracking
        return exist;
    }
}
