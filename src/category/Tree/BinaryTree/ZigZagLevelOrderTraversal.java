package category.Tree.BinaryTree;

import java.util.*;

class ZigZagLevelOrderTraversal {
    // Returns the zigzag (spiral) level order traversal of a binary tree
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if (root == null) return res;

        ArrayDeque<TreeNode> queue = new ArrayDeque();
        int level = 1; // Track current level number
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList();
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.pop();
                // Odd levels: left to right; Even levels: right to left
                if (level % 2 != 0) {
                    tmp.add(node.val); // append at end
                } else {
                    tmp.add(0, node.val); // insert at start for reverse order
                }

                // Add children for next level
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);

            }
            res.add(tmp); // Add current level's result
            level++;
        }

        return res;
    }
}