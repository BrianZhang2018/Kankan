package category.DynamicPlanning;

/**
 * Created by brianzhang on 10/14/18.
 * <p>
 * https://leetcode.com/problems/unique-paths/description/
 */
public class UniquePath {
    public int uniquePaths(int m, int n) {
        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    grid[i][j] = 1;
                } else {
                    grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
                }
            }
        }
        return grid[n - 1][m - 1];
    }

    public static void main(String[] args) {

        int nir[][] = new int[5][];
        nir[0] = new int[5];
        nir[1] = new int[3];
        System.out.println(nir[0].length); // 5
        System.out.println(nir[1].length); // 3
        System.out.println(nir.length);
        System.out.println(3 / 2);
    }
}
