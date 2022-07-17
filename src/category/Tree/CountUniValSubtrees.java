package category.Tree;

import category.model.TreeNode;

/**
 * https://www.lintcode.com/problem/count-univalue-subtrees/description
 *
 * Created by brianzhang on 9/20/20.
 */
public class CountUniValSubtrees {

    public static void main(String[] args) {}

    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if(root == null) return count;
        if(isUniVal(root, root.val)) count++;

        countUnivalSubtrees(root.left);
        countUnivalSubtrees(root.right);
        return count;
    }

    public boolean isUniVal(TreeNode root, int val){
        if(root == null) return true;

        return root.val == val && isUniVal(root.left, val) && isUniVal(root.right, val);
    }
}
