package category.tree.trie;

import java.util.HashMap;

/**
 * Created by brianzhang on 11/4/18.
 */
public class LongestWordTrieDFS1 {
    public static void main(String[] args) {
        String[] input = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        LongestWordTrieDFS1 longestWordTrieDFS1 = new LongestWordTrieDFS1();
        System.out.println(longestWordTrieDFS1.longestWord(input));
    }

    HashMap<String, String> res = new HashMap<>();

    public String longestWord(String[] words) {

        if (words == null || words.length == 0)
            return "";
        TrieNode root = new TrieNode();
        for (String str : words) {
            root.insert(str);
        }

        res.put("longest", "");
        dfs(root, "");
        return res.get("longest");
    }

    //simple dfsHelper that don't need return in exit if condition
    public void dfs(TrieNode node, String longest) {

        if (node.isLastWord) {
            String curr = node.word;
            if (curr.length() > longest.length() || (curr.length() == longest.length() && curr.compareTo(longest) < 0)) {
                longest = curr;
                res.put("longest", longest);
            }
        }
        for (TrieNode child : node.children) {
            if (child != null && child.word.length() != 0) {
                dfs(child, longest);
            }
        }
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isLastWord = false;
        String word = "";

        public void insert(String str) {
            TrieNode currNode = this;

            for (int level = 0; level < str.length(); level++) {
                int index = str.charAt(level) - 'a';
                if (currNode.children[index] == null) {
                    currNode.children[index] = new TrieNode();
                }
                currNode = currNode.children[index];
            }
            currNode.word = str;
            currNode.isLastWord = true;
        }
    }
}


