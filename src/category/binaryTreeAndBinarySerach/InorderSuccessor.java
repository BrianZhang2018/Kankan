package category.binaryTreeAndBinarySerach;

import category.model.TreeNode;

public class InorderSuccessor{

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        //find the p node from tree
         while (root != null && root.val != p.val) {
             if (root.val > p.val) {
                 successor = root;
                 root = root.left;
             } else {
                 root = root.right;
             }
         }
         //if root == null means p doesn't find from above while loop, or the input root == null, so return null
         if (root == null) {
             return null;
         }
 
         // 找到p之后，如果p没有右儿子，则第一个比它大的数字就是刚刚记录的successor
         if (root.right == null) {
             return successor;
         }
 
         // 找到p之后，如果有右儿子，则找到右子树中的最左边的值（最小值）
         root = root.right;
         while (root.left != null) {
             root = root.left;
         }
 
         return root;
     }
}