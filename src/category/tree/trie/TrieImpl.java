package category.tree.trie;

/**
 * A trie, also called digital binaryTree, radix binaryTree or prefix binaryTree is a kind of search binaryTree
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * oracle
 * Created by brianzhang on 11/4/18.
 */
public class TrieImpl {
    public static void main(String args[]) {
        String keys[] = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
        // Construct trie
        TrieN root = new TrieN();
        for (int i = 0; i < keys.length; i++) root.insert(keys[i]);

        System.out.println(root.search("the"));
        System.out.println(root.search("these"));
        System.out.println(root.startsWith("th"));
    }
}

class TrieN {
    boolean isEnd;
    TrieN[] next;

    /** Initialize your data structure here. */
    public TrieN() {
        isEnd = false;
        next = new TrieN[26];
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieN a = this;
        char [] c = word.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (a.next[c[i] - 'a'] == null) {
                a.next[c[i] - 'a'] = new TrieN();
            }
            a = a.next[c[i] - 'a'];
        }
        a.isEnd = true;
        return;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieN a = this;
        char [] c = word.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (a.next[c[i] - 'a'] == null) {
                return false;
            } else {
                a = a.next[c[i] - 'a'];
            }
        }
        return a.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieN a = this;
        char [] c = prefix.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (a.next[c[i] - 'a'] == null) {
                return false;
            } else {
                a = a.next[c[i] - 'a'];
            }
        }
        return true;
    }
}



