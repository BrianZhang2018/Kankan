package category.DynamicPlanning.easyDP;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/house-robber-iii/
 * https://leetcode.com/problems/house-robber-iii/discuss/79363/Easy-understanding-solution-with-dfs
 *
 * Created by brianzhang on 3/1/20.
 */
public class HouseRobberIII {

    public static void main(String[] args) {

    }

    public int rob(TreeNode root) {
        if(root == null)
            return 0;

        int[] res=dfs(root);
        return Math.max(res[0], res[1]);
    }

    public int[] dfs(TreeNode root){
        if(root == null)
            return new int[2];

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int[] res = new int[2];
        res[0] = left[1] + right[1] + root.val;
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return res;
    }
}
