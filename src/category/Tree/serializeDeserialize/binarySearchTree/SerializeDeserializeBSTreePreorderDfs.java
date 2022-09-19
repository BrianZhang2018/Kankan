package category.Tree.serializeDeserialize.binarySearchTree;

import category.model.TreeNode;
import java.util.*;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-bst/
 *
 * 1. SerializeDeserializeBTreePreOrderDfs.java solution works for all the binary tree, including bst
 * 2. optimization: pass the lower bound and upper bound to know the boundary of a subtree.
 *
 * tc: O(N), sc: O(N)
 * Preorder + Queue
 */
public class SerializeDeserializeBSTreePreorderDfs {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
/*        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);*/
        SerializeDeserializeBSTreePreorderDfs test = new SerializeDeserializeBSTreePreorderDfs();
        String str = test.serialize(root);
        System.out.println(Arrays.toString(str.split(",")));
        test.dfs(str);
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) return;   // optimization: don't need to store the null value
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // decodes your encoded tree
    public TreeNode dfs(String serializedTree) {
        if (serializedTree.isEmpty()) return null;
        Queue<String> q = new LinkedList<>(Arrays.asList(serializedTree.split(",")));
        return dfs(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // pass the low bound and high bound to know the boundary of a subtree
    public TreeNode dfs(Queue<String> q, int low, int high) {
        if (q.isEmpty()) return null;

        int val = Integer.parseInt(q.peek());
        if (val < low || val > high) {
            return null;
        }

        q.poll();
        TreeNode root = new TreeNode(val);
        root.left = dfs(q, low, val);
        root.right = dfs(q, val, high);
        return root;
    }
}