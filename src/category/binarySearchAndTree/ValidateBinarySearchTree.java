package category.binarySearchAndTree;

import amazon.model.TreeNode;

/**
 * Created by brianzhang on 4/15/19.
 */
public class ValidateBinarySearchTree {

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
