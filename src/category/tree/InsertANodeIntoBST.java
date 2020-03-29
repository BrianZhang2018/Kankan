package category.tree;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/
 *
 * Created by brianzhang on 12/2/18.
 */
public class InsertANodeIntoBST {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        InsertANodeIntoBST test = new InsertANodeIntoBST();
        test.insertIntoBST(root, 5);
    }

    public TreeNode insertIntoBST(TreeNode root, int valOfInsertNode) {
        if (root == null)
            return null;

        return helper(root, valOfInsertNode);
    }

    public TreeNode helper(TreeNode root, int valOfInsertNode) {

        if (root == null) {
            return new TreeNode(valOfInsertNode);
        }

        if (valOfInsertNode < root.val) {
            root.left = helper(root.left, valOfInsertNode);
        } else {
            root.right = helper(root.right, valOfInsertNode);
        }

        return root;
    }

    public TreeNode insertIntoBSTIteration(TreeNode root, int val) {

        if(root == null)
            return new TreeNode(val);

        TreeNode curr = root;
        while(true){
            if(curr.val > val){
                if(curr.left == null){
                    curr.left = new TreeNode(val);
                    break;
                }else{
                    curr = curr.left;
                }
            }else{
                if(curr.right == null){
                    curr.right = new TreeNode(val);
                    break;
                }else{
                    curr = curr.right;
                }
            }
        }

        return root;
    }
}
