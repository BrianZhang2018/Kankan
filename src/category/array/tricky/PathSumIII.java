package category.array.tricky;

import category.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brianzhang on 12/9/18.
 */
public class PathSumIII {

    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        return backtrack(root, prefixSumMap, 0, sum);
    }

    public int backtrack(TreeNode node, Map<Integer, Integer> prefixSumMap, Integer cur, int target) {
        if (node == null)
            return 0;
        cur += node.val;

        int res = prefixSumMap.getOrDefault(cur - target, 0);
        prefixSumMap.put(cur, prefixSumMap.getOrDefault(cur, 0) + 1);

        res += backtrack(node.left, prefixSumMap, cur, target);
        res += backtrack(node.right, prefixSumMap, cur, target);

        //backtrack step: remove the current node from map
        prefixSumMap.put(cur, prefixSumMap.get(cur) - 1);
        cur -= node.val;

        return res;
    }
}
