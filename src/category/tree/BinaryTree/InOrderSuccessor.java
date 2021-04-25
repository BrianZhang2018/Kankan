package category.tree.BinaryTree;

import category.model.TreeNode;

/**
 * https://www.lintcode.com/problem/inorder-successor-in-bst/description
 */
public class InOrderSuccessor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        InOrderSuccessor test = new InOrderSuccessor();
        TreeNode p = new TreeNode(1);
        TreeNode tn = test.inorderSuccessor(root, p);
        System.out.println(tn.val);
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        //find the p node from binary search tree
         while (root != null && root.val != p.val) {
             if (root.val > p.val) {
                 successor = root;
                 root = root.left;
             } else {
                 root = root.right;
             }
         }
         // if root == null means p doesn't find from above while loop, or the input root == null, so return null
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