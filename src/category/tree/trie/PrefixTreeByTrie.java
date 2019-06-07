package category.tree.trie.LinkedList;

import java.util.HashMap;

/**
 * Created by brianzhang on 11/11/18.
 */
public class PrefixTreeByTrie {

    public static void main(String[] args) {
        PrefixTreeByTrie trie = new PrefixTreeByTrie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("beer");
        trie.insert("add");
        trie.insert("jam");
        trie.insert("rental");
        trie.insert("apps");
        System.out.println(trie.startsWith("jam"));
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public PrefixTreeByTrie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null)
            return;
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char temp = word.charAt(i);
            if (curr.children.get(temp) == null) {
                TrieNode child = new TrieNode();
                child.nodeVal = temp;
                curr.children.put(temp, child);
            }
            curr = curr.children.get(temp);
        }

        curr.word = word;
        curr.isLastWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {

        String res = dfs(root, word);
        if (res.equals(word))
            return true;
        else
            return false;
    }

    public String dfs(TrieNode node, String word) {

        String value = node.word;

        for (TrieNode child : node.children.values()) {
            String res = dfs(child, word);
            if (res.equals(word)) {
                value = res;
            }
        }
        return value;
    }

    private boolean result = false;

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        result = false;
        dfsPrefix(root, prefix, new StringBuilder(""));
        return result;
    }

    /**
     * DFS + Backtracking: cool, wow, but slow
     * debug it, you will see how the backtracking works
     */
    public synchronized void dfsPrefix(TrieNode node, String word, StringBuilder str) {

        if (node.nodeVal != 0) {
            str.append(node.nodeVal);
        }

        if (str.length() == word.length() && str.toString().equals(word)) {
            result = true;
            //return;
        }

        for (TrieNode node1 : node.children.values()) {
            dfsPrefix(node1, word, str);
            str.deleteCharAt(str.length() - 1);
        }
    }

    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap();
        char nodeVal = 0;
        String word = "";
        boolean isLastWord = false;
    }
}
