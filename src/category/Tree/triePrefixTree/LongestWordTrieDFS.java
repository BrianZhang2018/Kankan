package category.Tree.triePrefixTree;

/**
 * https://leetcode.com/problems/longest-word-in-dictionary/
 * 
 * Created by brianzhang on 11/5/18.
 */
public class LongestWordTrieDFS {
    public static void main(String[] args) {
        String[] input = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        LongestWordTrieDFS longestWord = new LongestWordTrieDFS();
        System.out.println(longestWord.longestWord(input));
    }

    public String longestWord(String[] words) {

        if (words == null || words.length == 0)
            return "";
        TrieNode root = new TrieNode();
        for (String str : words) {
            root.insert(str);
        }
        return dfs(root);
    }

    public String dfs(TrieNode root) {

        String res = root.word;
        for (TrieNode child : root.children) {
            if (child != null && child.word.length() != 0) {
                String curr = dfs(child);
                if (curr.length() > res.length() || (curr.length() == res.length() && curr.compareTo(res) < 0)) {
                    res = curr;
                }
            }
        }
        return res;
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