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
        inOrder(root);
    }

    public static void inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            System.out.println(curr.val);
            curr = curr.right;
        }
    }
}
