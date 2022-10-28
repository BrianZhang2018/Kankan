package category.Tree.triePrefixTree.basicImpl.map;

import java.util.*;
/**
 * Created by brianzhang on 1/6/19.
 */
public class TrieImplByHashMap {
    public static void main(String[] args) {
        String keys[] = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
        TrieImplByHashMap root = new TrieImplByHashMap();
        root.buildTrieTree(keys);
        System.out.println(root.search("the"));
        System.out.println(root.search("these"));
        System.out.println(root.startsWith("th"));
        System.out.println(Arrays.toString(root.getAllWordsStartsWith("th").toArray()));
    }
    private TNode root;

    public TrieImplByHashMap() {
        root = new TNode(' '); // Initialize root
    }

    public TNode buildTrieTree(String[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i]);
        }
        return root;
    }

    // Inserts a word into the trie
    public void insert(String word) {
        if (word == null) return;
        TNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children.get(c) == null) {
                TNode child = new TNode(c);
                child.val = c;
                curr.children.put(c, child);
            }
            curr = curr.children.get(c);
        }

        curr.word = word;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null) return false;

        TNode curr = root;
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

        boolean isExist = true;
        TNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            HashMap<Character, TNode> children = curr.children;
            if (children.get(prefix.charAt(i)) != null)
                curr = children.get(prefix.charAt(i));
            else {
                isExist = false;
            }
        }
        return isExist;
    }

    // interviewed with Rivian, can do bfs also
    public List<String> getAllWordsStartsWith(String prefix) {
        if (prefix == null) return null;

        TNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (node.children.get(prefix.charAt(i)) != null)
                node = node.children.get(prefix.charAt(i));
            else {
                return null;
            }
        }

        List<String> res = new ArrayList();
        dfs(node, res);
        return res;
    }

    public void dfs(TNode node, List<String> res) {
        if(node == null) return;
        for(Map.Entry<Character, TNode> entry : node.children.entrySet()) {
            TNode curr = entry.getValue();
            if(curr.word != null) {
                res.add(curr.word);
            }
            dfs(curr, res);
        }
    }

     class TNode {
        HashMap<Character, TNode> children = new HashMap();
        char val;
        String word;
        public TNode(char c) {
            this.val = c;
        }
    }
}
