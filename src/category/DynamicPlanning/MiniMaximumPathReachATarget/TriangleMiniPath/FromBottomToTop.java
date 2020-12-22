package category.DynamicPlanning.MiniMaximumPathReachATarget.TriangleMiniPath;

import java.util.List;

/**
 * 动态规划 - 自底向上
 * <p>
 * from bottom to top
 * 降纬而且简化, 也是自底向上 ->  OneDimensionalArrayForMemo.java
 * <p>
 * Created by brianzhang on 11/12/18.
 */
public class FromBottomToTop {

    private int[][] memo;
    private int height;

    public int minimumTotal(List<List<Integer>> triangle) {

        if (triangle == null || triangle.size() == 0)
            return 0;

        int height = triangle.size();
        memo = new int[height][height];

        //Initialize the last level
        for (int i = 0; i < triangle.get(height - 1).size(); i++) {
            memo[height - 1][i] = triangle.get(height - 1).get(i);
        }

        //递推， 自底向上
        for (int i = height - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // memo[i][j] means: the minimum path from (i,j) to bottom
                memo[i][j] = Math.min(memo[i + 1][j], memo[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        //the minimum path from (0,0) to bottom
        return memo[0][0];
    }

}
