package category.backtracingDFS;

import category.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 12/4/18.
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        pathSumHelper(root, sum, result, path);
        return result;
    }

    public void pathSumHelper(TreeNode root, int sum, List<List<Integer>> result, List<Integer> path) {
        if (root == null) {
            return;
        }

        path.add(root.val);
        //reach a leaf
        if (root.left == null && root.right == null) {
            //find path sum
            if (root.val == sum)
                result.add(new ArrayList<Integer>(path));
        } else {
            pathSumHelper(root.left, sum - root.val, result, path);
            pathSumHelper(root.right, sum - root.val, result, path);
        }

        path.remove(path.size() - 1);
    }
}
