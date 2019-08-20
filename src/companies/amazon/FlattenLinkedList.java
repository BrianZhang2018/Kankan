package companies.amazon;

import companies.amazon.model.TreeNode;

/**
 * Created by brianzhang on 7/18/18.
 */
public class FlattenLinkedList {
    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int last = --m + --n +1;
        System.out.println(last);
    }

    public void flatten(TreeNode root) {

        if (root == null)
            return;

        while (root != null) {

            if (root.left != null) {
                TreeNode p = root.left;

                if (p.right != null) {
                    p = p.right;
                }

//在构造过程中，只要树中有多出来的分叉（左子树），就嫁接到根节点和右子树之间，
                p.right = root.right;
                root.right = root.left;
                root.left = null;
            }

            root = root.right;
        }
    }

    //Asolution-2
    TreeNode prev = null;

    public void flatten1(TreeNode root) {

        if (root == null)
            return;

        TreeNode right = root.right;
        if (prev != null) {
            prev.right = root;
            prev.left = null;
        }

        prev = root;
        flatten(root.left);
        flatten(right);
    }
}
