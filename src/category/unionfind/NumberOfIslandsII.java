package category.unionfind;

import java.util.*;

/**
 * https://yeqiuquan.blogspot.com/2017/03/lintcode-434-number-of-islands-ii.html
 * https://leetcode.com/problems/number-of-islands-ii/
 * https://leetcode.com/problems/number-of-islands-ii/discuss/75470/Easiest-Java-Solution-with-Explanations
 * https://www.lintcode.com/problem/number-of-islands-ii/description
 */
public class NumberOfIslandsII {

    public static void main(String[] args) {
        List<Integer> res = new NumberOfIslandsII().numIslands2(3, 3, new int[][]{{0, 0}, {0, 1}, {1, 2}, {2, 1}});
        System.out.println(Arrays.toString(res.toArray()));
    }

    int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if (m <= 0 || n <= 0) return result;

        int count = 0;                      // number of islands
        int[] roots = new int[m * n];       // one island = one tree
        Arrays.fill(roots, -1);

        for (int[] p : positions) {
            int root = n * p[0] + p[1];     // assume new point is isolated island
            roots[root] = root;             // add new island
            count++;

            for (int[] dir : dirs) {
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                int nb = n * x + y; // nb: neighbor
                if (x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1) continue;

                int rootNb = findIsland(roots, nb);
                if (root != rootNb) {        // if neighbor is in another island
                    roots[root] = rootNb;   // union two islands
                    root = rootNb;          // current tree root = joined tree root
                    count--;
                }
            }

            result.add(count);
        }
        return result;
    }

    public int findIsland(int[] roots, int id) {
        while (id != roots[id]) id = roots[id];
        return id;
    }

/*    Path Compression (Bonus)
    If you have time, add one line to shorten the tree. The new runtime becomes: 19ms (95.94%).

    public int findIsland(int[] roots, int id) {
        while(id != roots[id]) {
            roots[id] = roots[roots[id]];   // only one line added
            id = roots[id];
        }
        return id;
    }*/
}