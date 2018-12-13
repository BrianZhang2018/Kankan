package category.array.tricky;

import category.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brianzhang on 12/9/18.
 */
public class PathIIIWithGlobalVariable {
    //global variable
    private int res = 0;

    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        //prefixSumMap.put(0, 1);  //same function like the line 28 - 29
        backtrack(root, prefixSumMap, 0, sum);
        return res;
    }

    public void backtrack(TreeNode node, Map<Integer, Integer> prefixSumMap, Integer cur, int target) {
        if (node == null)
            return;
        cur += node.val;
        res += prefixSumMap.getOrDefault(cur - target, 0);
        prefixSumMap.put(cur, prefixSumMap.getOrDefault(cur, 0) + 1);
        if (target == cur)
            res++;

        backtrack(node.left, prefixSumMap, cur, target);
        backtrack(node.right, prefixSumMap, cur, target);

        prefixSumMap.put(cur, prefixSumMap.get(cur) - 1);
        cur -= node.val;
    }
}
