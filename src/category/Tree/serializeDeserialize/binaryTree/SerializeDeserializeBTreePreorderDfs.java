package category.Tree.serializeDeserialize.binaryTree;

import category.model.TreeNode;
import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * tc: O(N), sc: O(N)
 * Preorder + Queue
 */
public class SerializeDeserializeBTreePreorderDfs {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
/*        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);*/
        SerializeDeserializeBTreePreorderDfs test = new SerializeDeserializeBTreePreorderDfs();
        String str = test.serialize(root);
        test.deserialize(str);
    }

    // Encodes a tree to a single string using preorder traversal
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrderDfs(sb, root);
        System.out.println(sb);
        return sb.toString();
    }

    public void preOrderDfs(StringBuilder sb, TreeNode root) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val + ","); // node value separated by ","
        preOrderDfs(sb, root.left);
        preOrderDfs(sb, root.right);
    }

    // DFS to deserialize a tree with queue
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return dfsBuildTree(queue);
    }

    private TreeNode dfsBuildTree(Queue<String> queue) {
        String val = queue.poll();
        if ("#".equals(val)) return null;

        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = dfsBuildTree(queue);
        root.right = dfsBuildTree(queue);
        return root;
    }
}