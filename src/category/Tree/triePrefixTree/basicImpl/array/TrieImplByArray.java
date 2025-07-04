package category.Tree.triePrefixTree.basicImpl.array;

/**
 * A trie, also called prefix binaryTree
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * Oracle
 * Created by brianzhang on 11/4/18.
 */
public class TrieImplByArray {
    public static void main(String args[]) {
        String keys[] = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
        TNode root = new TNode();
        // build trie
        for (int i = 0; i < keys.length; i++) {
            root.insert(keys[i]);
        }
        System.out.println(root.search("the"));
        System.out.println(root.search("these"));
        System.out.println(root.startsWith("th"));
    }
}

class TNode {
    TNode[] children;
    String word;

    public TNode() {
        children = new TNode[26];
    }
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TNode node = this;
        char[] cs = word.toCharArray();
        for (char c : cs) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TNode();
            }
            node = node.children[c - 'a'];
        }
        node.word = word;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TNode node = this; // "this" represent root
        char[] ca = word.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if (node.children[ca[i] - 'a'] == null) {
                return false;
            } else {
                node = node.children[ca[i] - 'a'];
            }
        }
        return node.word != null;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TNode node = this;
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



