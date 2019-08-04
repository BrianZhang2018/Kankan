package category.tree;

import category.model.TreeNode;
import java.util.Arrays;

/**
 * preOrser -> serialize
 * DFS -> deserialize
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class SerializeAndDeseBTPreorder{
    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.right = new TreeNode(9);
        root.right.left = new TreeNode(1);

        SerializeAndDeseBTPreorder test = new SerializeAndDeseBTPreorder();
        String str = test.serialize(root);
        System.out.println(str);
        test.deserialize(str);
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper(sb, root);
        return sb.toString();
    }
    //preorder traversal
    public void helper(StringBuilder sb, TreeNode root) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val + ",");
        helper(sb, root.left);
        helper(sb, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] ss = data.split(",");
        System.out.println(Arrays.toString(ss));
        return helper(ss, new int[0]);
    }

    public TreeNode helper(String[] ss, int[] index) {
        if (ss[index[0]].equals("#")) {
            index[0]++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(ss[index[0]++]));
        node.left = helper(ss, index);
        node.right = helper(ss, index);
        return node;
    }
}