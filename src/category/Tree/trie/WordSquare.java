package category.Tree.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.lintcode.com/problem/word-squares/
 * https://zhuhan0.blogspot.com/2017/09/leetcode-425-word-squares.html
 *
 * 这个一道查找搜索的题，所以不能用简单的permutation+backtracking去做，要用Trie数据结构搜索word，进行排列组合和backtracking
 * Created by brianzhang on 3/27/20.
 */
public class WordSquare {
    public static void main(String[] args) {
        WordSquare ws = new WordSquare();
        List<List<String>> res = ws.wordSquare(new String[]{"area","lead","wall","lady","ball"});
        for(List l : res)System.out.println(Arrays.toString(l.toArray()));
    }

    public List<List<String>> wordSquare(String[] words) {
        TrieNode root = buildTrie(words);
        List<List<String>> squares = new ArrayList<>();

        for (String word : words) {
            List<String> square = new ArrayList<>();
            square.add(word);
            dfs(root, word.length(), square, squares);
        }
        return squares;
    }

    private void dfs(TrieNode root, int len, List<String> square, List<List<String>> res) {
        if (square.size() == len) {
            res.add(new ArrayList<>(square));
            return;
        }

        String prefix = getPrefix(square, square.size());
        TrieNode node = search(root, prefix);
        if (node == null) {
            return;
        }
        List<String> children = new ArrayList<>();
        getChildren(node, prefix, children); // get children words which matched the prefix

        for (String child : children) { //Continue searching for the next prefix match recursively
            square.add(child);
            dfs(root, len, square, res);
            square.remove(square.size() - 1);
        }
    }

    private TrieNode search(TrieNode root, String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current;
    }

    // get prefix for column[index] in current square (matrix)
    private String getPrefix(List<String> square, int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++) {
            sb.append(square.get(i).charAt(index));
        }
        return sb.toString();
    }

    private void getChildren(TrieNode node, String s, List<String> res) {
        if (node.isWord) {
            res.add(s);
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                getChildren(node.children[i], s + (char) ('a' + i), res);
            }
        }
    }

    //TrieN solution 看上去确实很很长，但是下面的build Trie和Trie class是固定的(TrieN data structure)
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.isWord = true;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] children;
        boolean isWord;

        TrieNode() {
            children = new TrieNode[26];
        }
    }
}
