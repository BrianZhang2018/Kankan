package category.BinaryTree;

import category.model.TreeNode;
/**
 * https://leetcode.com/problems/recover-binary-search-tree/discuss/32535/No-Fancy-Algorithm-just-Simple-and-Powerful-In-Order-Traversal
 *
 * "inorder traversal" can be used to compare the current and previous node as prev always less than curr node in in-order-traversal bst
 *
 * TC: O(N) - N is the number of nodes
 * Space complexity: Recursion stack is O(height), the best case will be O(logN), the worst case is O(N)
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

    TreeNode firstNode, secondNode;
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

        if (root == null) return;

        traverse(root.left);

        if(prevNode != null){
            if (firstNode == null && prevNode.val >= root.val) {
                firstNode = prevNode;
            }
            if (firstNode != null && prevNode.val >= root.val) {
                secondNode = root;
                // due to swap value in binary search tree that must be the large value swap to the front, and corresponding small value go to behind.
                // so line-51, we do firstNode = prevNode, but above line we do "secondNode = root" rather than prevNode as the small value is in the behind which is root.val
            }
        }

        prevNode = root;

        traverse(root.right);
    }
}
