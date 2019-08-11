package category.BacktracingDFS;

import java.util.List;
import java.util.LinkedList;
/**
 * DFS + TrieNode + backtracking
 * https://leetcode.com/problems/word-search-ii/
 */
public class WordSearchII{

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new LinkedList<>();
        TrieNode root = new TrieNode();
        //construct TrieNode for each word under the `root`
        for (String word: words) 
            insert(word, root);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, root, i, j, ans);
            }
        }
        return ans;
    }
    
    private static void dfs(char[][] board, TrieNode root, int i, int j, List<String> list) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) 
            return;
        
        char ch = board[i][j];
        int index = ch - 'a';
        if (ch == '.' || root.children[index] == null) 
            return;
        
        root = root.children[index];
        if (root.word != null) {
            list.add(root.word);
            root.word = null;
            // no `return` here as one branch of Trie can contain multiple word
        }
        
        //mark the visited board[i][j]
        board[i][j] = '.';
        dfs(board, root, i + 1, j, list);
        dfs(board, root, i - 1, j, list);
        dfs(board, root, i, j + 1, list);
        dfs(board, root, i, j - 1, list);
        //reset the visited board[i][j] value
        board[i][j] = ch;
    }
    
    private static void insert(String word, TrieNode root) {
        for (char ch: word.toCharArray()) {
            int index = ch - 'a';
            if (root.children[index] == null) 
                root.children[index] = new TrieNode();

            root = root.children[index];
        }
        root.word = word;
    }
    
    private static class TrieNode {
        String word;
        TrieNode[] children = new TrieNode[26];
    }
}
