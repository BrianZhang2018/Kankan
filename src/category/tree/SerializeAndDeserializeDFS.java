package category.tree;

import category.model.TreeNode;

import java.util.*;
/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * PreOrder -> serialize
 */
public class SerializeAndDeserializeDFS {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        SerializeAndDeserializeDFS test = new SerializeAndDeserializeDFS();
        String str = test.serialize(root);
        System.out.println(Arrays.toString(str.split(",")));
        test.deserialize(str);
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(sb, root);
        return sb.toString();
    }

    public void preOrder(StringBuilder sb, TreeNode root){
        if(root == null){
            sb.append("#,");
            return;
        }
        sb.append(root.val + ",");
        preOrder(sb, root.left);
        preOrder(sb, root.right);
    }

    public TreeNode deserialize(String data) {
        return buildTree(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    // DFS to deserialize a tree
    private TreeNode buildTree(Queue<String> queue) {
        String val = queue.poll();
        if ("#".equals(val)) return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = buildTree(queue);
        root.right = buildTree(queue);
        return root;
    }
}