package category.tree.bondary;

import java.util.List;
import java.util.ArrayList;
import companies.amazon.model.TreeNode;

/**
 * The idea is very simple, break the boundary into 4 parts:
    1. Root
    2. Left Boundary
    3. Right Boundary
    4. Leaves.
For left Boundary we add the result top down, and for right boundary we add the result bottom up.
In both left and right boundary we ensure that we don't touch the leaves.
 * https://www.lintcode.com/problem/boundary-of-binary-tree/description
 */
public class BoundaryOfBinaryTree{

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        // write your code here
        List<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;
         
        res.add(root.val);  
        collectLeft(root.left, res);
        collectLeaves(root, res);
        collectRight(root.right, res);
        return res;    
    }
    
    //pre-order traversal, the normal sequence root->left->right
    public void collectLeft(TreeNode root, List<Integer> res){
        if(root == null || isLeaf(root))
            return;
        res.add(root.val);
        collectLeft(root.left !=null ? root.left : root.right, res);
    }

    public void collectLeaves(TreeNode root, List<Integer> res){
        if(root == null)
            return;
        if(isLeaf(root)){
            res.add(root.val);
            return;
        }else{
             collectLeaves(root.left, res);
             collectLeaves(root.right, res);
        }
    }

    //postorder traversal but the sequence is right->left->root
    public void collectRight(TreeNode root, List<Integer> res){
        if(root == null || isLeaf(root))
            return;
        
        collectRight(root.right != null ? root.right : root.left, res);
        res.add(root.val);
    }
    
    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}