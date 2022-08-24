package category.Tree.triePrefixTree;

import java.util.*;

/**
 * https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structur
 *
 * Created by brianzhang on 7/17/21.
 */
public class PalindromePairsTrie {

    public static void main(String[] args) {
      for(List l : new PalindromePairsTrie().palindromePairs(new String[]{"cat","looltac"})) {
            System.out.println(l.toString());
        }
    }

    private static class TrieNode {
        TrieNode[] next;
        int index;
        List<Integer> list;

        TrieNode() {
            next = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++)
            addWord(root, words[i], i);

        for (int i = 0; i < words.length; i++)
            searchPair(words[i], i, root, res); // since we stored the reversed word in Trie, so just search the original word in tree, if exists, means we found a reversed word to original word, pair found

        return res;
    }

    private void addWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) { // store reversed word in Trie
            int j = word.charAt(i) - 'a';

            if (root.next[j] == null) {
                root.next[j] = new TrieNode();
            }

            if (isPalindrome(word, 0, i)) {
                root.list.add(index); // store the suffix palindrome
            }

            root = root.next[j];
        }

        root.list.add(index);
        root.index = index;
    }

    private void searchPair(String word, int index, TrieNode root, List<List<Integer>> res) {
        for (int j = 0; j < word.length(); j++) {
            if (root.index >= 0 && isPalindrome(word, j, word.length() - 1)) { // e.g. "cat" : "looltac" => (reversed stored in Tire) suffix palindrome "lool" + reversed word (root.index >= 0)
                res.add(Arrays.asList(index, root.index));
            }

            root = root.next[word.charAt(j) - 'a'];
            if (root == null) return;
        }

        for (int j : root.list) {
            if (index == j) continue; // can exclude single character word, e.g. "s"
            res.add(Arrays.asList(index, j));
        }
    }

    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }
        return true;
    }
}
