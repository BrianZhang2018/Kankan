package category.DivideAndConquerRecursive;

import category.model.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 * Created by brianzhang on 6/22/19.
 */
public class UniqueBinarySearchTreeII {
    public List<TreeNode> generateTrees(int n) {

        if(n == 0)
            return Collections.EMPTY_LIST;

        List<TreeNode> ret = new ArrayList<>();
        return getBsts(1, n);
    }

    public List<TreeNode> getBsts(int start, int end){
        List<TreeNode> list = new ArrayList<>();

        if(start > end){
            list.add(null);
            return list;
        }

        if(start == end){
            list.add(new TreeNode(start));
            return list;
        }

        for(int i=start; i<=end; i++){
            List<TreeNode> lefts = getBsts(start, i-1);
            List<TreeNode> rights = getBsts(i+1, end);
            for(TreeNode l : lefts){
                for(TreeNode r : rights){
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    list.add(root);
                }
            }
        }
        return list;
    }
}
