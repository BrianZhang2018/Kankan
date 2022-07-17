package category.Tree.serializeDeserialize;

import category.model.TreeNode;
import java.util.*;
/**
 * BFS solution
 * tc: O(N), sc: O(N)
 * Created by brianzhang on 7/21/18.
 */
public class SerializeDeserializeBinaryTreeBFSSolution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.right = new TreeNode(9);
        root.right.left = new TreeNode(1);
        String str = serialize(root);
        System.out.println(serialize(root));
        deserialize(str);
    }

    // level traversal
    public static String serialize(TreeNode root) {
        if (root == null) {return "";}
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                sb.append(curr.val + ",");
                queue.add(curr.left);
                queue.add(curr.right);
            } else {
                sb.append("#,");
            }
        }

        return sb.toString();
    }

    public static TreeNode deserialize(String serializedTree) {
        if (serializedTree == null || serializedTree == "") return null;

        Queue<TreeNode> queue = new LinkedList<>();
        String[] nodes = serializedTree.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        queue.add(root);
        for (int i = 1; i < nodes.length; i++) {
            TreeNode parent = queue.poll();
            if (!nodes[i].equals("#")) {
                TreeNode left = new TreeNode(Integer.valueOf(nodes[i]));
                parent.left = left;
                queue.add(left);
            }

            if (!nodes[++i].equals("#")) {
                TreeNode right = new TreeNode(Integer.valueOf(nodes[i]));
                parent.right = right;
                queue.add(right);
            }
        }
        return root;
    }

}
