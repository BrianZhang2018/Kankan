package category.BinaryTreeAndBinarySerach.binarySearchTree;

import companies.amazon.model.TreeNode;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Created by brianzhang on 4/15/19.
 */
public class ValidateBinarySearchTree {

    public static void main(String[] args) {}

    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;
        return validateTree(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validateTree(TreeNode root, long lowerLimit, long upperLimit){

        if(root == null)
            return true;

        if(root.val <= lowerLimit || root.val >= upperLimit)
            return false;

        boolean lTree = validateTree(root.left, lowerLimit, root.val);
        boolean rTree = validateTree(root.right, root.val, upperLimit);
        return lTree && rTree;
    }
}
