package category.tree.trie.LinkedList;
import java.util.List;
import java.util.ArrayList;
import amazon.model.TreeNode;

/**
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
    
    //pre-order traversal
    public void collectLeft(TreeNode root, List<Integer> res){
        if(root == null || isLeaf(root))
            return;
        res.add(root.val);
        collectLeft(root.left !=null ? root.left : root.right, res);
    }
    //postorder traversal
    public void collectRight(TreeNode root, List<Integer> res){
        if(root == null || isLeaf(root))
            return;
        
        collectRight(root.right != null ? root.right : root.left, res);
        res.add(root.val);
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
    
    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}