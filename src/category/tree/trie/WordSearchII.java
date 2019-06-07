package category.tree.trie;

import java.util.List;
import java.util.LinkedList;
/**
 * https://leetcode.com/problems/word-search-ii/
 */
public class WordSearchII{

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new LinkedList<>();
        TrieNode root = new TrieNode();
        for (String word: words) insert(word, root);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWords(board, root, i, j, ans);
            }
        }
        return ans;
    }
    
    private static void findWords(char[][] board, TrieNode root, int i, int j, List<String> list) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        
        char ch = board[i][j];
        int index = ch - 'a';
        if (ch == '.' || root.children[index] == null) return;
        
        root = root.children[index];
        if (root.word != null) {
            list.add(root.word);
            root.word = null;
        }
        
        board[i][j] = '.';
        findWords(board, root, i + 1, j, list);
        findWords(board, root, i - 1, j, list);
        findWords(board, root, i, j + 1, list);
        findWords(board, root, i, j - 1, list);
        board[i][j] = ch;
    }
    
    private static void insert(String word, TrieNode root) {
        for (char ch: word.toCharArray()) {
            int index = ch - 'a';
            if (root.children[index] == null) root.children[index] = new TrieNode();
            root = root.children[index];
        }
        root.word = word;
    }
    
    private static class TrieNode {
        String word;
        TrieNode[] children = new TrieNode[26];
    }

}