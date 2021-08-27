package category.tree.BinaryTree.convertToLinkedList;

import companies.amazon.model.TreeNode;
import java.util.Stack;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        flatten(root);
    }

    static TreeNode prevVisited = null;

    // Solution-1
    /**
        如hint所给出，这道题就是使用先序遍历，遍历到的值作为新的右孩子存起来，左孩子变为空。
        注意的是，因为右孩子会更新，所以为了递归右子树，要在更新之前提前保存右孩子。
        整个程序需要维护一个全局变量，保存当前所遍历的节点。
    */
    public static void flatten(TreeNode root) {

        if (root == null) return;

        TreeNode right = root.right; // pass reference as value, java is pass by value
        if (prevVisited != null) {
            prevVisited.right = root;
            prevVisited.left = null;
        }

        prevVisited = root;
        flatten(root.left);
        flatten(right);
    }

    // Solution-2
    /**
      * 此题还有不用递归方法解决的方法，那就是使用栈。
      * 对整棵树一直向右子树方向遍历。当遍历的节点有右孩子时，就将其入栈。有左孩子时，将其更新为当前节点的右孩子，左孩子置空。当左孩子为空时而栈不空时，
      * 就弹出栈，作为右孩子
     */
    public static void flatten2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr!=null || !stack.isEmpty()) {

            if (curr.right != null) {
                stack.add(curr.right);
            }

            if (curr.left != null) {
                curr.right = curr.left;
                curr.left = null;
            } else if (!stack.isEmpty()) {
                curr.right = stack.pop();
            }

            curr = curr.right;
        }
    }
}
