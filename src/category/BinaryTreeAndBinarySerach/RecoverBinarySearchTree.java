package category.BinaryTreeAndBinarySerach;

import category.model.TreeNode;
/**
 * https://leetcode.com/problems/recover-binary-search-tree/
 * https://leetcode.com/problems/recover-binary-search-tree/discuss/32535/No-Fancy-Algorithm-just-Simple-and-Powerful-In-Order-Traversal
 *
 * "in order traversal" can be used to compare the current and previous node as prev always less than curr node in in-order-traversal bst
 *
 * Created by brianzhang on 5/22/20.
 */
public class RecoverBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

        System.out.println("Before"); BinaryTreeUtil.preOrderPrintOut(root);

        RecoverBinarySearchTree test = new RecoverBinarySearchTree();
        test.recoverTree(root);

        System.out.println("After");BinaryTreeUtil.preOrderPrintOut(root);
    }

    TreeNode firstNode = null;
    TreeNode secondNode = null;
    // The reason for this initialization is to avoid null pointer exception in the first comparison when prevNode has not been initialized
    TreeNode prevNode = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {

        // In order traversal to find the two nodes
        traverse(root);

        // Swap the values of the two nodes
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }

    private void traverse(TreeNode root) {

        if (root == null)
            return;

        traverse(root.left);

        // Start of "do some business",
        // If first node has not been found, assign it to prevNode (refer to 6 in the example above)
        if (firstNode == null && prevNode.val >= root.val) {
            firstNode = prevNode;
        }

        // If first node is found, assign the second node to the root (refer to 2 in the example above)
        if (firstNode != null && prevNode.val >= root.val) {
            secondNode = root;
        }
        prevNode = root;

        // End of "do some business"

        traverse(root.right);
    }
}
