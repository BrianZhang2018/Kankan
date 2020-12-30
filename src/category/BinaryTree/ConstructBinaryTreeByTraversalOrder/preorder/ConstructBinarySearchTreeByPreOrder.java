package category.BinaryTree.ConstructBinaryTreeByTraversalOrder.preorder;

import category.model.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 *
 * TC: O(N), since we visit each node exactly once.  SC: O(N) to keep the entire tree.
 * Created by brianzhang on 2/17/20.
 */
public class ConstructBinarySearchTreeByPreOrder {
    public static void main(String[] args) {
        TreeNode root = bstFromPreOrder(new int[]{8,5,1,7,10,12});
        printOutByPreOrder(root);
        bstFromPreOrderBFS(new int[]{8,5,1,7,10,12});
    }

    // Solution-1: DFS preOrder recursive solution
    public static TreeNode bstFromPreOrder(int[] preOrder) {
        TreeNode root = new TreeNode(preOrder[0]);
        for (int i = 1; i < preOrder.length; i++) {
            buildBST(root, preOrder[i]);
        }
        return root;
    }

    static void buildBST(TreeNode root, int n) {
        if (n < root.val) {
            if (root.left == null)
                root.left = new TreeNode(n);
            else
                buildBST(root.left, n);
        }
        if (n > root.val) {
            if (root.right == null)
                root.right = new TreeNode(n);
            else
                buildBST(root.right, n);
        }
    }

    // Solution-2: lower/upperLimit solution - refer from the ValidateBinarySearchTree.java solution
    // TC: O(N), since we visit each node exactly once. SC: O(N) to keep the entire tree.
    int idx = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public TreeNode helper(int[] preorder,int low, int high) {
        if(idx == preorder.length) return null;

        if (preorder[idx] <= low || preorder[idx] >= high) return null;

        TreeNode root = new TreeNode(preorder[idx++]);
        root.left = helper(preorder, low, root.val);
        root.right = helper(preorder, root.val, high);

        return root;
    }

    // Solution-3: Iteration solution converted from Solution-2
    public static TreeNode bstFromPreOrderBFS(int[] preOrder) {
        if (preOrder == null || preOrder.length == 0) return null;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(preOrder[0]);
        stack.push(root);

        for (int i = 1; i < preOrder.length; i++) {
            TreeNode node = stack.peek();
            TreeNode child = new TreeNode(preOrder[i]);
            if (preOrder[i] < node.val) {
                node.left = child;
            } else {
                while (!stack.isEmpty() && preOrder[i] > stack.peek().val) {
                    node = stack.pop();
                }
                node.right = child;
            }
            stack.push(child);
        }

        return root;
    }


    // ignore: printOut result helper function
    public static void printOutByPreOrder(TreeNode root){
        if(root == null) {System.out.println("leaf"); return;}
        System.out.println(root.val);
        printOutByPreOrder(root.left);
        printOutByPreOrder(root.right);
    }
}
