package category.BacktracingDFS;

/**
 * DFS here, don't use BFS will has the visited state issue
 *
 * T: O(mn*4^k)
 * S: O(4mn)
 *
 * https://leetcode.com/problems/word-search/
 */
public class WordSearch {

    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(ws.exist(board, "ABCCED"));
    }

    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0){
            return false;
        }
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
               if(dfs(i, j, board, word, 0)){
                    return true;
                } 
            }
        }
        return false;
    }
    
    public boolean dfs(int row, int col, char[][] board, String word, int index){
        if(index == word.length()) 
            return true;
        
        if(row >=board.length || row < 0 || col>=board[0].length || col<0 || board[row][col] != word.charAt(index)){
            return false;
        }
        board[row][col] = '*';
        boolean exist =  dfs(row, col+1, board, word, index+1) || dfs(row, col-1, board, word, index+1) ||
            dfs(row+1, col, board, word, index+1) || dfs(row-1, col, board, word, index+1);
        board[row][col] = word.charAt(index);
        return exist;
    }
   
}
