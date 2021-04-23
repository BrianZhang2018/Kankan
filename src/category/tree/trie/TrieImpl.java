package category.tree.trie;

/**
 * A trie, also called digital binaryTree, radix binaryTree or prefix binaryTree is a kind of search binaryTree
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * Oracle
 * Created by brianzhang on 11/4/18.
 */
public class TrieImpl {
    public static void main(String args[]) {
        String keys[] = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
        // Construct trie
        Trie root = new Trie();
        for (int i = 0; i < keys.length; i++) root.insert(keys[i]);

        System.out.println(root.search("the"));
        System.out.println(root.search("these"));
        System.out.println(root.startsWith("th"));
    }
}

class Trie {
    boolean isEnd;
    Trie[] children;

    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        children = new Trie[26];
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this;
        char [] c = word.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (node.children[c[i] - 'a'] == null) {
                node.children[c[i] - 'a'] = new Trie();
            }
            node = node.children[c[i] - 'a'];
        }
        node.isEnd = true;
        return;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie a = this;
        char [] ca = word.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if (a.children[ca[i] - 'a'] == null) {
                return false;
            } else {
                a = a.children[ca[i] - 'a'];
            }
        }
        return a.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = this;
        char [] c = prefix.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (node.children[c[i] - 'a'] == null) {
                return false;
            } else {
                node = node.children[c[i] - 'a'];
            }
        }
        return true;
    }
}



