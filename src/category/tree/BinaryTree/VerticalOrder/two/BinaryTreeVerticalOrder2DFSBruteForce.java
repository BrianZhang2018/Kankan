package category.tree.BinaryTree.VerticalOrder.two;

import category.model.TreeNode;
import javafx.util.Pair;
import java.util.*;

/**
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 *
 * There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 *
 * Created by brianzhang on 7/28/21.
 */
public class BinaryTreeVerticalOrder2DFSBruteForce {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        Map<Integer, List<Pair<String, Integer>>> map = new TreeMap();
        map.putIfAbsent(0, new ArrayList<>());
        map.get(0).add(new Pair("0:0", root.val)); // left:right

        dfs(root, map, 0, 0);

        for(Map.Entry<Integer, List<Pair<String, Integer>>> entry : map.entrySet()) {
            List<Pair<String, Integer>> l = entry.getValue();
            l.sort((a,b) -> {
                String left1 = a.getKey().split(":")[0], left2 = b.getKey().split(":")[0];
                if(a.getKey().equals(b.getKey())) {
                    return a.getValue() - b.getValue();
                }
                return Integer.valueOf(left1) - Integer.valueOf(left2);
            });

            List<Integer> r = new ArrayList<>();
            for(Pair<String, Integer> p : l) {
                r.add(p.getValue());
            }
            res.add(r);
        }

        return res;
    }


    public void dfs(TreeNode root, Map<Integer, List<Pair<String, Integer>>> map, int left, int right) {
        if(root == null) return;

        if(root.left != null) {
            String position = (left+1) + ":" + (right-1);
            map.putIfAbsent(right-1, new ArrayList<>());
            map.get(right-1).add(new Pair(position, root.left.val));
            dfs(root.left, map, left+1, right-1);
        }

        if(root.right != null) {
            String position = (left+1) + ":" + (right+1);
            map.putIfAbsent(right+1, new ArrayList<>());
            map.get(right+1).add(new Pair(position, root.right.val));
            dfs(root.right, map, left+1, right+1);
        }
    }
}
