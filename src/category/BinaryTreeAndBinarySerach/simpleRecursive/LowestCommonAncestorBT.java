package category.BinaryTreeAndBinarySerach.simpleRecursive;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * Created by brianzhang on 5/9/20.
 */
public class LowestCommonAncestorBT {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode p = new TreeNode(5), q = new TreeNode(1);

        LowestCommonAncestorBT test = new LowestCommonAncestorBT();
        TreeNode res = test.lowestCommonAncestor(root, p, q);
        System.out.println(res.val);
    }

    // Easy recursion solution
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root.val == p.val || root.val ==q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null) return root;

        return left != null ? left : right;
    }

    TreeNode lca;

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        helper(root, p, q);
        return lca;
    }

    public boolean helper(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root == null)
            return false;

        int left = helper(root.left, p, q) ? 1 : 0;
        int right = helper(root.right, p, q) ? 1 : 0;

        // If the current node is one of p or q
        int mid = (p.val == root.val) || (q.val == root.val) ? 1 : 0;

        // If any two of the flags left, right or mid become True
        if(mid + left + right >=2){
            lca = root;
        }

        // Return true if any one of the three bool values is True.
        return (mid + right + left >0);
    }

}
