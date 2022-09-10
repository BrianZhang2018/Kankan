package category.Tree.BinaryTree;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 *
 * Created by brianzhang on 4/25/21.
 */
public class LevelOrderTraverseII {
    // DFS
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();

        level(root, 0, res);
        return res;
    }

    public void level(TreeNode node, int level, LinkedList<List<Integer>> res){
        if(node == null) return;

        if(res.size() <= level){
            res.addFirst(new ArrayList<>()); // catch
        }

        res.get(res.size() - level -1).add(node.val);
        level(node.left, level + 1, res);
        level(node.right, level + 1, res);
    }

    // BFS
    public List<List<Integer>> levelOrderBottomBFS(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int level_length = queue.size();
            for (int i = 0; i < level_length; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            res.addFirst(level);
        }
        return res;
    }

}
