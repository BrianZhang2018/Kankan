package category.tree.BinaryTree.VerticalOrder.one;

import java.util.*;
import category.model.TreeNode;

/**
 * https://www.lintcode.com/problem/binary-tree-vertical-order-traversal/
 *
 *      0
 *  -1    1
 * -2  0 0 2
 *
 * Note: if two nodes are in the same row and column, the order should be from left to right, no sort
 *
 * https://www.programcreek.com/2014/04/leetcode-binary-tree-vertical-order-traversal-java/
 */
public class BinaryTreeVerticalOrderTraversal1BFS {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        BinaryTreeVerticalOrderTraversal1BFS test = new BinaryTreeVerticalOrderTraversal1BFS();
        for(List<Integer> l : test.verticalOrder(root)) System.out.println(Arrays.toString(l.toArray()));
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        bfs(root, map);
        return new ArrayList<>(map.values());
    }

    private void bfs(TreeNode root, TreeMap<Integer, List<Integer>> map) {
        if (root == null) return;

        LinkedList<Node> q = new LinkedList<>();
        q.offer(new Node(root, 0));

        while (!q.isEmpty()) {
            Node n = q.poll();
            TreeNode node = n.node;
            int verticalOrder = n.verticalOrder;

            map.putIfAbsent(verticalOrder, new ArrayList<>());
            map.get(verticalOrder).add(node.val);

            if (node.left != null) {
                q.offer(new Node(node.left, verticalOrder - 1)); // vertical order: left minus 1
            }

            if (node.right != null) {
                q.offer(new Node(node.right, verticalOrder+1)); // vertical order: right plus 1
            }
        }
    }


    public int findSubstringInWraproundString(String p) {
        char[] chars = p.toCharArray();
        int n = chars.length;

        int[] map = new int[26];
        int len = 0;

        for (int i = 0; i < n; i++) {
            if (i > 0 && ((chars[i - 1] + 1 == chars[i]) || (chars[i - 1] == 'z' && chars[i] == 'a')))
                len += 1;
            else
                len = 1;

            // we are storing the max len of string for each letter and then we will count all these lenghts.
            map[chars[i] - 'a'] = Math.max(map[chars[i] - 'a'], len);
        }


        int answer = 0;
        for (int num : map) answer += num;

        return answer;
    }

    class Node {
        TreeNode node;
        int verticalOrder;
        public Node(TreeNode n, int order) {
            this.node= n;
            this.verticalOrder = order;
        }
    }
}