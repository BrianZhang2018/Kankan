package category.Tree.serializeDeserialize;

import category.model.TreeNode;
import java.util.*;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-bst/
 *
 * don't need to store the null value, pass the lower bound and upper bound to know the boundary of a subtree.
 * tc: O(N), sc: O(N)
 * PreOrder + Queue
 */
public class SerializeDeserializeBinarySearchTreePreOrderSolution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
/*        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);*/
        SerializeDeserializeBinarySearchTreePreOrderSolution test = new SerializeDeserializeBinarySearchTreePreOrderSolution();
        String str = test.serialize(root); System.out.println(Arrays.toString(str.split(",")));
        test.deserialize(str);
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) return; // optimization point, don't need to store the null value, check the line-45
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public TreeNode deserialize(Queue<String> q, int lower, int upper) { // pass the lower bound and upper bound to know the boundary of a subtree.
        if (q.isEmpty()) return null;
        String s = q.peek();
        int val = Integer.parseInt(s);
        if (val < lower || val > upper) return null;
        q.poll();
        TreeNode root = new TreeNode(val);
        root.left = deserialize(q, lower, val);
        root.right = deserialize(q, val, upper);
        return root;
    }
}