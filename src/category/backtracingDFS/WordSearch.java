package category.BacktracingDFS;

/**
 * DFS here, don't use BFS will has the visited state issue
 * 
 * https://leetcode.com/problems/word-search/
 */
public class WordSearch{
    private int m = 0;
    private int n = 0;
    private boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0){
            return false;
        }
        
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
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
        
        if(row >=m || row < 0 || col>=n || col<0 || visited[row][col] || board[row][col] != word.charAt(index)){
            return false;
        }
        
        visited[row][col] = true;
        index++;
        boolean exist =  dfs(row, col+1, board, word, index) || dfs(row, col-1, board, word, index) ||
            dfs(row+1, col, board, word, index) || dfs(row-1, col, board, word, index);
        
        visited[row][col] = false;
        return exist;
    }
   
}
