package category.tree.trie.LinkedList;

import category.model.TreeNode;

/**
 * Created by brianzhang on 12/2/18.
 */
public class InsertAnodeINtoBST {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        InsertAnodeINtoBST insertAnodeINtoBST = new InsertAnodeINtoBST();
        insertAnodeINtoBST.insertIntoBST(root, 5);
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return null;

        return helper(root, val);
    }

    public TreeNode helper(TreeNode root, int val) {

        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.left = helper(root.left, val);
        } else {
            root.right = helper(root.right, val);
        }

        return root;
    }
}
