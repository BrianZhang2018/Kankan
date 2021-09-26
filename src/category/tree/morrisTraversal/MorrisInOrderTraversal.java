package category.tree.morrisTraversal;

import category.model.TreeNode;

/**
 * Morris Traversal ( Using Threaded Tree for Inorder Traversal)
 * O(n) Time | O(1) Space
 *
 * Definition Threaded Tree
  A binary tree is threaded by making all right child pointers that would normally be null point to the inorder successor of
  the node (if it exists), and all left child pointers that would normally be null point to the inorder predecessor of the node.
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

        morrisTraversal(root);
    }

    public static void morrisTraversal(TreeNode root){
        TreeNode temp;
        while(root!=null){
            if(root.left!=null){
                // connect threading for root
                temp = root.left;
                while(temp.right!=null && temp.right != root)
                    temp = temp.right;
                // the threading already exists
                if(temp.right!=null){
                    temp.right = null;
                    System.out.println(root.val);
                    root = root.right;
                }else{
                    // construct the threading
                    temp.right = root;
                    root = root.left;
                }
            }else{
                System.out.println(root.val);
                root = root.right;
            }
        }
    }
}
