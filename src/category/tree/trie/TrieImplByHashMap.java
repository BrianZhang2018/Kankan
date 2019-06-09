package category.tree.trie;

import java.util.HashMap;

/**
 * Created by brianzhang on 1/6/19.
 */
public class TrieImplByHashMap {

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public TrieImplByHashMap() {
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
        if (word == null)
            return false;

        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            HashMap<Character, TrieNode> children = curr.children;

            if (children.get(word.charAt(i)) != null)
                curr = children.get(word.charAt(i));
            else
                return false;
        }
        return curr.isLastWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (prefix == null)
            return false;

        boolean isExist = true;
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            HashMap<Character, TrieNode> children = curr.children;

            if (children.get(prefix.charAt(i)) != null)
                curr = children.get(prefix.charAt(i));
            else {
                isExist = false;
            }
        }
        return isExist;
    }

    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap();
        char nodeVal = ' ';
        String word = "";
        boolean isLastWord = false;
    }
}
