package category.tree.BinaryTree.VerticalOrder.one;

import java.util.*;
import category.model.TreeNode;

/**
 * https://www.lintcode.com/problem/binary-tree-vertical-order-traversal/
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 *      0
 *  -1    1
 * -2  0 0 2
 * https://www.programcreek.com/2014/04/leetcode-binary-tree-vertical-order-traversal-java/
 */
public class BinaryTreeVerticalOrderTraversal1 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        BinaryTreeVerticalOrderTraversal1 test = new BinaryTreeVerticalOrderTraversal1();
        for(List<Integer> l : test.verticalOrder(root)) System.out.println(Arrays.toString(l.toArray()));
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        bfs(root, map);
        return new ArrayList<>(map.values());
    }
     
    private void bfs(TreeNode root, TreeMap<Integer, List<Integer>> map) {
        if (root == null) return;

        LinkedList<TreeNode> q1 = new LinkedList<>();  //queue for binaryTree node
        LinkedList<Integer> q2 = new LinkedList<>();  //queue for binaryTree node's degree
        q1.offer(root);
        q2.offer(0);
     
        while (!q1.isEmpty()) {
            TreeNode node = q1.poll();
            int level = q2.poll();

            map.putIfAbsent(level, new ArrayList<>());
            map.get(level).add(node.val);
     
            if (node.left != null) {
                q1.offer(node.left);
                q2.offer(level - 1);
            }
     
            if (node.right != null) {
                q1.offer(node.right);
                q2.offer(level + 1);
            }
        }
    }

}