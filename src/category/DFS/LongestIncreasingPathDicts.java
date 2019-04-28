package category.DFS;

/**
 * Created by brianzhang on 4/27/19.
 */
public class LongestIncreasingPathDicts {

    public static void main(String[] args){
        LongestIncreasingPathDicts test = new LongestIncreasingPathDicts();
        System.out.println(test.longestIncreasingPath(new int[][]{{3,4,5},{3,2,6},{2,2,1}}));
    }

    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                res = Math.max(res, len);
            }
        }
        return res;
    }

    public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if(cache[i][j] != 0)
            return cache[i][j];
        int max = 1; // the initiation value of max is 1 as we start from current number, so length is 1.
        for(int[] dir: dirs) {
            int x = i + dir[0], y = j + dir[1];
            if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j])
                continue;
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
}
