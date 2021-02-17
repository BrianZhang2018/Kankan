package category.tree.trie;

import java.util.*;

/**
 * https://leetcode.com/problems/design-search-autocomplete-system/
 *
 * Created by brianzhang on 2/15/21.
 */
public class AutocompleteSystem {

    class TrieNode implements Comparable<TrieNode> {
        TrieNode[] children;
        String sentence;
        int times;
        List<TrieNode> hot;

        public TrieNode() {
            children = new TrieNode[128];
            sentence = null;
            times = 0;
            hot = new ArrayList<>(); // 关键: 每个node都记录它的top 3 hot sentence
        }

        public int compareTo(TrieNode o) {
            if (this.times == o.times) {
                return this.sentence.compareTo(o.sentence);
            }

            return o.times - this.times;
        }

        public void update(TrieNode node) {
            if (!this.hot.contains(node)) {
                this.hot.add(node);
            }

            Collections.sort(hot);

            if (hot.size() > 3) {
                hot.remove(hot.size() - 1);
            }
        }
    }

    TrieNode root;
    TrieNode curr;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        curr = root;
        sb = new StringBuilder();

        for (int i = 0; i < times.length; i++) {
            add(sentences[i], times[i]);
        }
    }


    public void add(String sentence, int t) {
        TrieNode tmp = root;

        List<TrieNode> visited = new ArrayList<>();
        for (char c : sentence.toCharArray()) {
            if (tmp.children[c] == null) {
                tmp.children[c] = new TrieNode();
            }

            tmp = tmp.children[c];
            visited.add(tmp);
        }

        tmp.sentence = sentence;
        tmp.times += t;

        for (TrieNode node : visited) {
            node.update(tmp);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            add(sb.toString(), 1);
            sb = new StringBuilder();
            curr = root;
            return res;
        }

        sb.append(c);
        if (curr != null) {
            curr = curr.children[c];
        }

        if (curr == null) return res;
        for (TrieNode node : curr.hot) {
            res.add(node.sentence);
        }

        return res;
    }
}
