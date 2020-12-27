package category.BinaryTree;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * Created by brianzhang on 9/27/20.
 */
public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, 0, root);
        return res;
    }

    public void helper(List<Integer> res, int currDepth, TreeNode node){
        if(node == null) return;

        if(currDepth == res.size()){
            res.add(node.val);
        }

        helper(res, currDepth+1, node.right);
        helper(res, currDepth+1, node.left);
    }
}
