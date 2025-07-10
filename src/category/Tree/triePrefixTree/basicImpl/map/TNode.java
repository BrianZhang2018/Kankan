package category.Tree.triePrefixTree.basicImpl.map;

import java.util.*;

public class TNode {
    public HashMap<Character, TNode> children = new HashMap<>();
    public char val;
    public String word;
    public TNode(char c) {
        this.val = c;
    }

    public TNode buildTrieTree(String[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insertWord(keys[i]);
        }
        return this;
    }

    public void insertWord(String word) {
        if (word == null) return;
        TNode node = this;  // start from root
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.children.get(c) == null) {
                TNode child = new TNode(c);
                child.val = c;
                node.children.put(c, child);
            }
            node = node.children.get(c);
        }
        node.word = word;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null) return false;
        TNode curr = this;
        for (int i = 0; i < word.length(); i++) {
            HashMap<Character, TNode> children = curr.children;
            if (children.get(word.charAt(i)) != null)
                curr = children.get(word.charAt(i));
            else
                return false;
        }
        return curr.word != null;
    }

    // Returns if there is any word in the trie that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null) return false;

        TNode curr = this;
        for (int i = 0; i < prefix.length(); i++) {
            HashMap<Character, TNode> children = curr.children;
            if (children.get(prefix.charAt(i)) != null)
                curr = children.get(prefix.charAt(i));
            else {
                return false;
            }
        }
        return true;
    }
    
} 