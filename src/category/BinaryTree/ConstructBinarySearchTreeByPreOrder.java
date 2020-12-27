package category.BinaryTree;

import category.model.TreeNode;
import java.util.Stack;

/**
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 *
 * Created by brianzhang on 2/17/20.
 */
public class ConstructBinarySearchTreeByPreOrder {
    public static void main(String[] args) {
        TreeNode root = bstFromPreOrder(new int[]{8,5,1,7,10,12});
        preOrderTraversal(root);
    }

    // recursion solution
    public static TreeNode bstFromPreOrder(int[] preOrder) {
        TreeNode root = new TreeNode(preOrder[0]);
        for (int i = 1; i < preOrder.length; i++)
            addBST(root, preOrder[i]);
        return root;
    }

    static void addBST(TreeNode root, int n) {
        if (n < root.val) {
            if (root.left == null)
                root.left = new TreeNode(n);
            else
                addBST(root.left, n);
        }
        if (n > root.val) {
            if (root.right == null)
                root.right = new TreeNode(n);
            else
                addBST(root.right, n);
        }
    }

    // iterative solution
    public static TreeNode bstFromPreOrder1(int[] preOrder) {
        if (preOrder == null || preOrder.length == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preOrder[0]);
        stack.push(root);
        for (int i = 1; i < preOrder.length; i++) {
            TreeNode node = new TreeNode(preOrder[i]);
            if (preOrder[i] < stack.peek().val) {
                stack.peek().left = node;
            } else {
                TreeNode parent = stack.peek();
                while (!stack.isEmpty() && preOrder[i] > stack.peek().val) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }
        return root;
    }

    public static void preOrderTraversal(TreeNode root){
        if(root == null){
            System.out.println("leaf");
            return;
        }

        System.out.println(root.val);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
}
