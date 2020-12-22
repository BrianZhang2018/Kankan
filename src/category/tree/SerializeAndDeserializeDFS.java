package category.tree;

import category.model.TreeNode;

import java.util.*;
/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * PreOrder -> serialize
 * dfs -> deserialize
 */
public class SerializeAndDeserializeDFS {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.right = new TreeNode(9);
        root.right.left = new TreeNode(1);

        SerializeAndDeserializeDFS test = new SerializeAndDeserializeDFS();
        String str = test.serialize(root);  System.out.println(Arrays.toString(str.split(",")));
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
        return deserial(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    // deserial, preOrder走一遍
    private TreeNode deserial(Queue<String> queue) {
        String val = queue.poll();
        if ("#".equals(val)) return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = deserial(queue);
        root.right = deserial(queue);
        return root;
    }
}