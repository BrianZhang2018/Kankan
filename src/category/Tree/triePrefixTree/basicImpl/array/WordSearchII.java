package category.Tree.triePrefixTree.basicImpl.array;

import java.util.*;

/**
 * https://leetcode.com/problems/word-search-ii/
 * Trie (play as a dictionary with prefix index) + backtracking on board
 *
 * Summary:
 * - Trie is used to store all target words for "fast prefix and word lookup.
 * - Backtracking explores all board paths, but only continues along paths that match prefixes in the trie, efficiently finding valid words.
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
        // build trie tree with each word
        for (String word : words) {
            insert(word, root);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, root, i, j, ans);
            }
        }
        return ans;
    }

    private void dfs(char[][] board, TrieNode root, int i, int j, List<String> list) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '*')
            return;
        // Get the current character from the board to check if it continues a valid prefix in the trie
        char ch = board[i][j];
        int index = ch - 'a';
        if (root.children[index] == null) return;

        root = root.children[index];
        if (root.word != null) {
            list.add(root.word);
            root.word = null;
            // don't `return` here as one branch of TrieN can contain muletiple same-prefix words
        }

        board[i][j] = '*';  // mark visited
        dfs(board, root, i + 1, j, list);
        dfs(board, root, i - 1, j, list);
        dfs(board, root, i, j + 1, list);
        dfs(board, root, i, j - 1, list);
        board[i][j] = ch;   // reset the value
    }

    private void insert(String word, TrieNode root) {
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (root.children[index] == null) {
                root.children[index] = new TrieNode();
            }
            root = root.children[index];
        }
        root.word = word;
    }

    private class TrieNode {
        String word;
        TrieNode[] children = new TrieNode[26];
    }
}
