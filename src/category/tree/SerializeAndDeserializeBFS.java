package category.tree;

import category.model.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS solution
 * Created by brianzhang on 7/21/18.
 */
public class SerializeAndDeserializeBFS {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.right = new TreeNode(9);
        root.right.left = new TreeNode(1);

        SerializeAndDeserializeBFS test = new SerializeAndDeserializeBFS();
        String str = test.serialize(root);
        test.deserialize(str);
    }

    public String serialize(TreeNode root) {
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

    public TreeNode deserialize(String serializedTree) {
        if (serializedTree == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        String[] nodes = serializedTree.split(",");
        System.out.println(Arrays.toString(nodes));
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        queue.add(root);

        for (int i = 1; i < nodes.length; i++) {
            TreeNode parent = queue.poll();
            if (!nodes[i++].equals("#")) {
                TreeNode left = new TreeNode(Integer.valueOf(nodes[i]));
                parent.left = left;
                queue.add(left);
            }

            if (!nodes[i].equals("#")) {
                TreeNode right = new TreeNode(Integer.valueOf(nodes[i]));
                parent.right = right;
                queue.add(right);
            }
        }
        return root;
    }

}