package category.Tree.BinaryTree;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * Created by brianzhang on 9/27/20.
 */
public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, 0, root);
        return res;
    }

    // DFS approach for right side view
    public void helper(List<Integer> res, int currDepth, TreeNode node) {
        if (node == null)
            return;
        if (currDepth == res.size()) {
            res.add(node.val);
        }
        helper(res, currDepth + 1, node.right);
        helper(res, currDepth + 1, node.left);
    }

    // BFS approach for right side view
    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // The last node at each level is the rightmost one, size -1
                if (i == size - 1) {
                    res.add(node.val);
                }
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
        return res;
    }
}
