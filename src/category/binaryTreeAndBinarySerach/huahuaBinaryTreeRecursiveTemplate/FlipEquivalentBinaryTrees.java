package category.binaryTreeAndBinarySerach.huahuaBinaryTreeRecursiveTemplate;

import category.model.TreeNode;

/**
 * flipEquiv函数的含义：判断两个树在进行翻转/不进行翻转的情况下，能不能相等。
 */
public class FlipEquivalentBinaryTrees{

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        
        if(root1 == null && root2 == null)
            return true;
        if(root1 == null || root2 == null)
            return false;
        if(root1.val != root2.val)
            return false;
        
        //不进行翻转
        boolean l1 = flipEquiv(root1.left, root2.left);
        boolean r1 = flipEquiv(root1.right, root2.right);
        //进行翻转
        boolean l2 = flipEquiv(root1.left, root2.right);
        boolean r2 = flipEquiv(root1.right, root2.left);
        
        return (l1 && r1) || (l2 && r2);
    }

}