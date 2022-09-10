package category.Tree.triePrefixTree;

import java.util.*;
/**
 * https://leetcode.com/problems/word-search-ii/
 *
 * 1. build trie tree (store the words as trie tree structure)
 * 2. backtracking find the word
 */
public class WordSearchII {
    public static void main(String[] args) {
        WordSearchII ws = new WordSearchII();
        char[][] board = {{'o','a','a','n'},
                          {'e','t','a','e'},
                          {'i','h','k','r'},
                          {'i','f','l','v'}};
        for(String res : ws.dfs(board, new String[]{"oath","pea","eat","rain"})) System.out.println(res);
    }

    public List<String> dfs(char[][] board, String[] words) {
        List<String> res = new LinkedList<>();
        TrieNode root = new TrieNode();
        // build trie tree
        for (String word: words) buildTrie(word, root);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, root, i, j, res);
            }
        }
        return res;
    }
    // DFS backtracking, 1. got the character from board 2. search the character in the trie tree
    private void dfs(char[][] board, TrieNode root, int i, int j, List<String> res) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '*')
            return;
        
        char ch = board[i][j];
        int index = ch - 'a';
        if (root.children[index] == null)
            return;
        
        root = root.children[index];
        if (root.word != null) {
            res.add(root.word);
            root.word = null;
        }

        board[i][j] = '*';
        dfs(board, root, i + 1, j, res);
        dfs(board, root, i - 1, j, res);
        dfs(board, root, i, j + 1, res);
        dfs(board, root, i, j - 1, res);
        board[i][j] = ch;
    }
    
    private void buildTrie(String word, TrieNode root) {
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
