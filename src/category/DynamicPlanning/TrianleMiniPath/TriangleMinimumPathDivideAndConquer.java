package category.DynamicPlanning.TrianleMiniPath;

import java.util.List;

/**
 * DP -> Divide & Conquer + Memorization for this Asolution
 * <p>
 * Created by brianzhang on 9/9/18.
 */
public class TriangleMinimumPathDivideAndConquer {
    private int[][] memo;
    private int level;
    private List<List<Integer>> triangle;

    public int minimumTotal(List<List<Integer>> triangle) {
        memo = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        this.triangle = triangle;
        level = triangle.size();
        return divideConquer(0, 0);
    }

    //return the minimum path from (x, y) to bottom
    public int divideConquer(int x, int y) {
        if (x == level)
            return 0;

        //dp - memorization
        if (memo[x][y] != 0)
            return memo[x][y];

        memo[x][y] = Math.min(divideConquer(x + 1, y), divideConquer(x + 1, y + 1)) + triangle.get(x).get(y);

        return memo[x][y];
    }

}
