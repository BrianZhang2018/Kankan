package category.Tree.triePrefixTree.basicImpl.map;

import java.util.*;
/**
 * Created by brianzhang on 1/6/19.
 */
public class TrieImplByHashMap {
    public static void main(String[] args) {
        String keys[] = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
        TNode root = new TNode(' ');
        root.buildTrieTree(keys);
        System.out.println(root.search("the"));
        System.out.println(root.search("these"));
        System.out.println(root.startsWith("th"));
        System.out.println(Arrays.toString(getAllWordsStartsWith("th", root).toArray()));
    }

    // interviewed with Rivian, can do bfs also
    public static List<String> getAllWordsStartsWith(String prefix, TNode root) {
        if (prefix == null) return null;

        TNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (node.children.get(prefix.charAt(i)) != null)
                node = node.children.get(prefix.charAt(i));
            else {
                return null;
            }
        }

        return dfs(node, new ArrayList<String>());
    }

    public static List<String> dfs(TNode node, List<String> res) {
        if(node == null) return null;
        for(Map.Entry<Character, TNode> entry : node.children.entrySet()) {
            TNode curr = entry.getValue();
            if(curr.word != null) {
                res.add(curr.word);
            }
            dfs(curr, res);
        }
        return res;
    }
}
