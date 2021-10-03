package category.tree.morrisTraversal;

import category.model.TreeNode;

/**
 * Morris Traversal ( Using Threaded Tree for Inorder Traversal)
 * O(n) Time / (O(1) Space --> 牛逼的地方，一般的 inorder traversal 不管是 iterative 还是 recursive 都是 O(N) space)
 *
 * Definition "Threaded Tree"
 * The idea of threaded binary trees is to make inorder traversal faster and do it without stack and without recursion.
 *
   A binary tree is threaded by making all right child pointers that would normally be null point to the "inorder successor" of
  the node (if it exists), and all left child pointers that would normally be null point to the "inorder predecessor" of the node.
 *
 *
 * https://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
 *
 * Created by brianzhang on 9/25/21.
 */
public class MorrisInOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        morrisInOrderTraversal(root);
    }

    public static void morrisInOrderTraversal(TreeNode root){
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) { // if the left subtree has been totally traversed, then go "back" to the `predecessor`
                System.out.println(cur.val);
                cur = cur.right;
            } else {
                // to find the rightmost node in the left subtree
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }

                if (prev.right == null) {  // construct the threading
                    prev.right = cur;
                    cur = cur.left; // continue traverse left subtree
                } else { // the threading already exists
                    /**
                        1. to break the predecessor relationship previously built
                        2. to go to `cur.right`, which is another `predecessor`
                     */
                    prev.right = null;
                    System.out.println(cur.val);
                    cur = cur.right; // left subtree done, let's go right subtree
                }
            }
        }
    }
}
