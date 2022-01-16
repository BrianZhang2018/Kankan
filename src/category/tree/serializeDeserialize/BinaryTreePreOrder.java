package category.tree.serializeDeserialize;

import category.model.TreeNode;
import java.util.*;
/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * tc: O(N), sc: O(N)
 * PreOrder + Queue
 */
public class BinaryTreePreOrder {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
/*        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);*/
        BinaryTreePreOrder test = new BinaryTreePreOrder();
        String str = test.serialize(root); System.out.println(Arrays.toString(str.split(",")));
        test.deserialize(str);
    }

    // Encodes a tree to a single string using pre-order traversal
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(sb, root);
        System.out.println(sb.toString());
        return sb.toString();
    }

    public void preOrder(StringBuilder sb, TreeNode root){
        if(root == null){
            sb.append("#,");
            return;
        }
        sb.append(root.val + ","); // node value separated by ","
        preOrder(sb, root.left);
        preOrder(sb, root.right);
    }

    // DFS to deserialize a tree with queue
    public TreeNode deserialize(String data) {
        System.out.println(Arrays.toString(data.split(",")));
        TreeNode root = buildTree(new LinkedList<>(Arrays.asList(data.split(","))));
        return root;
    }

    private TreeNode buildTree(Queue<String> queue) {
        String val = queue.poll();
        if ("#".equals(val)) return null;

        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = buildTree(queue);
        root.right = buildTree(queue);
        return root;
    }
}