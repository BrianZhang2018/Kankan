package category.DFS.search;

import category.model.TreeNode;
import java.util.Stack;

/**
 * Created by brianzhang on 10/16/18.
 */
public class PreOrderIterative {

    public static void main(String args[]) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        depthFirstSearch(root);
    }

    public static void depthFirstSearch(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            System.out.print(node.val + " ");

            if (node.right != null) nodeStack.push(node.right);

            if (node.left != null) nodeStack.push(node.left);
        }
    }
}