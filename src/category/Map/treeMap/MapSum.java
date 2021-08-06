package category.Map.treeMap;

/**
 * Created by brianzhang on 8/1/21.
 */
class MapSum {
    public static void main(String[] args) {
        MapSum obj = new MapSum();
        obj.insert("apple", 3);
        System.out.println(obj.sum("ap"));
    }

    Trie root;
    int sum = 0;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Trie('0');
    }

    public void insert(String key, int val) {
        char[] keys= key.toCharArray();
        Trie curr = root;
        for(char k : keys) {
            if(curr.child[k-'a'] == null) {
                curr.child[k-'a'] = new Trie(k);
            }
            curr = curr.child[k-'a'];
        }
        curr.isEnd= true;
        curr.val = val;
    }

    public int sum(String prefix) {
        char[] keys= prefix.toCharArray();

        for(int i=0; i<keys.length; i++) {
            System.out.println(root.child[0]);
            if(root.child[keys[i]-'a'] == null && i < keys.length){
                System.out.println(root.child[keys[i]-'a'].c);
                return sum;
            }
            root = root.child[keys[i]-'a'];
        }

        System.out.println(root.c);

        if(root != null) {
            dfs(root);
        }

        return sum;
    }

    public void dfs (Trie t) {
        if(t == null) return;

        if(t.isEnd) sum +=t.val;

        for(Trie p : t.child) {
            dfs(p);
        }
    }
}

class Trie {
    Trie[] child;
    char c;
    boolean isEnd=false;
    int val = 0;

    public Trie(char c) {
        this.c = c;
        this.child = new Trie[26];
    }
}
