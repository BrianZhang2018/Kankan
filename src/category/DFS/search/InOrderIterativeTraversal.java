package category.DFS.search;

import category.model.TreeNode;
import java.util.Stack;

/**
 * https://www.techiedelight.com/inorder-tree-traversal-iterative-recursive/
 *
 * Created by brianzhang on 8/30/20.
 */
public class InOrderIterativeTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        inorder(root);
    }

    public static void inorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }

            root = stack.pop();
            System.out.println(root.val);
            root = root.right;
        }
    }
}
