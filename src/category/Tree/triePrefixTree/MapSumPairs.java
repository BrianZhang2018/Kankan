package category.Tree.triePrefixTree;

/**
 * https://leetcode.com/problems/map-sum-pairs/
 *
 * Created by brianzhang on 8/3/21.
 */
public class MapSumPairs {

    Trie root;

    /** Initialize your data structure here. */
    public MapSumPairs() {
        root = new Trie();
    }

    public void insert(String key, int val) {
        Trie curr = root;
        for(char c : key.toCharArray()) {
            if(curr.subs[c-'a'] == null) {
                curr.subs[c-'a'] = new Trie();
            }
            curr = curr.subs[c-'a'];
        }

        curr.isEnd = true;
        curr.val = val;
    }

    public int sum(String prefix) {
        Trie curr = root;
        for(char c : prefix.toCharArray()) {
            if(curr.subs[c-'a'] == null) return 0;

            curr = curr.subs[c-'a'];
        }


        return dfs(curr);
    }

    public int dfs(Trie root) {
        if(root == null) return 0;

        int sum = 0;

        if(root.isEnd) sum+=root.val;

        for(Trie t : root.subs) {
            if(t == null) continue;
            sum+=dfs(t);
        }

        return sum;
    }

    class Trie {
        Trie[] subs;
        boolean isEnd;
        int val;

        public Trie() {
            subs = new Trie[26];
        }
    }

}
