package category.BacktracingDFS;

import java.util.List;
import java.util.LinkedList;
/**
 * https://leetcode.com/problems/word-search-ii/
 *
 * TrieNode + backtracking
 */
public class WordSearchII {
    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'},
                          {'e','t','a','e'},
                          {'i','h','k','r'},
                          {'i','f','l','v'}};
        for(String str : new WordSearchII().findWords(board, new String[]{"oath","pea","eat","rain"})){
            System.out.println(str);
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new LinkedList<>();
        TrieNode root = new TrieNode();
        // Build Trie tree with each all words
        for (String word: words) insert(word, root);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, root, i, j, ans);
            }
        }
        return ans;
    }
    
    private void dfs(char[][] board, TrieNode root, int i, int j, List<String> list) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        
        char ch = board[i][j];
        int index = ch - 'a';
        if (ch == '.' || root.children[index] == null) 
            return;
        
        root = root.children[index];
        if (root.word != null) {
            list.add(root.word);
            root.word = null;
            // no `return` here as one branch of TrieN can contain multiple word
        }

        board[i][j] = '.';  //mark the visited board[i][j]
        dfs(board, root, i + 1, j, list);
        dfs(board, root, i - 1, j, list);
        dfs(board, root, i, j + 1, list);
        dfs(board, root, i, j - 1, list);
        board[i][j] = ch;   //reset the visited board[i][j] value
    }
    
    private void insert(String word, TrieNode root) {
        for (char ch: word.toCharArray()) {
            int index = ch - 'a';
            if (root.children[index] == null)
                root.children[index] = new TrieNode();

            root = root.children[index];
        }
        root.word = word;
    }
    
    private class TrieNode {
        String word;
        TrieNode[] children = new TrieNode[26];
    }
}
