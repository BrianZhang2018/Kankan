package category.DFSBacktracing;

import category.model.TreeNode;
import java.util.*;

/**
 * https://leetcode.com/problems/path-sum-ii/
 *
 * Created by brianzhang on 12/4/18.
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSumHelper(root, sum, result, path);
        return result;
    }

    public void pathSumHelper(TreeNode root, int sum, List<List<Integer>> result, List<Integer> path) {
        if (root == null) return;

        path.add(root.val);
        //reach a leaf
        if (root.left == null && root.right == null) {
            //find path sum
            if (root.val == sum) result.add(new ArrayList<>(path));
        }
        else {
            pathSumHelper(root.left, sum - root.val, result, path);
            pathSumHelper(root.right, sum - root.val, result, path);
        }

        path.remove(path.size() - 1);
    }
}
