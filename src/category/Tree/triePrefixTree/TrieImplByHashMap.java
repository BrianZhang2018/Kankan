package category.Tree.triePrefixTree;

import java.util.*;
/**
 * Created by brianzhang on 1/6/19.
 */
public class TrieImplByHashMap {
    public static void main(String[] args) {
        String keys[] = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
        TrieImplByHashMap root = new TrieImplByHashMap();
        // Construct trie
        for (int i = 0; i < keys.length; i++) {
            root.insert(keys[i]);
        }
        System.out.println(root.search("the"));
        System.out.println(root.search("these"));
        System.out.println(root.startsWith("th"));
        System.out.println(Arrays.toString(root.getAllWordsStartsWith("th").toArray()));
    }
    private TNode1 root;
    // Initialize root
    public TrieImplByHashMap() {
        root = new TNode1(' ');
    }

   // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null) return;
        TNode1 curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children.get(c) == null) {
                TNode1 child = new TNode1(c);
                child.nodeVal = c;
                curr.children.put(c, child);
            }
            curr = curr.children.get(c);
        }

        curr.word = word;
        curr.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null) return false;

        TNode1 curr = root;
        for (int i = 0; i < word.length(); i++) {
            HashMap<Character, TNode1> children = curr.children;

            if (children.get(word.charAt(i)) != null)
                curr = children.get(word.charAt(i));
            else
                return false;
        }
        return curr.isWord;
    }

    // Returns if there is any word in the trie that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null)
            return false;

        boolean isExist = true;
        TNode1 curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            HashMap<Character, TNode1> children = curr.children;

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

        TNode1 curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            HashMap<Character, TNode1> children = curr.children;
            if (children.get(prefix.charAt(i)) != null)
                curr = children.get(prefix.charAt(i));
            else {
                return  null;
            }
        }

        List<String> res = new ArrayList();
        dfs(curr, res);
        return res;
    }

    public void dfs(TNode1 n, List<String> res) {
        for(Map.Entry<Character, TNode1> entry : n.children.entrySet()) {
            TNode1 curr = entry.getValue();
            if(curr.isWord) {
                res.add(curr.word);
            }
            dfs(curr, res);
        }
    }

    static class TNode1 {
        HashMap<Character, TNode1> children = new HashMap();
        char nodeVal = ' ';
        String word = "";
        boolean isWord = false;

        public TNode1(char c) {
            this.nodeVal = c;
        }
    }
}
