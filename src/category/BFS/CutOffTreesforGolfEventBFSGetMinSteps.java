package category.BFS;

import java.util.*;

/**
 * The worst case time complexity could be O(m^2 * n^2) (m = number of rows, n = number of columns)
 * since there are m * n trees and for each BFS worst case time complexity is O(m * n) too.
 * <p>
 * Created by brianzhang on 2/18/19.
 * <p>
 * https://www.youtube.com/watch?v=OFkLC30OxXM
 */
public class CutOffTreesforGolfEventBFSGetMinSteps {

    public static void main(String[] args) {
        CutOffTreesforGolfEventBFSGetMinSteps test = new CutOffTreesforGolfEventBFSGetMinSteps();
        List<List<Integer>> forest = new ArrayList<List<Integer>>();
        forest.add(Arrays.asList(1, 2, 3));
        forest.add(Arrays.asList(0, 0, 4));
        forest.add(Arrays.asList(7, 6, 5));

        System.out.println(test.cutOffTree(forest));
    }

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0)
            return 0;

        int m = forest.size();
        int n = forest.get(0).size();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    pq.add(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }

        int[] start = new int[2];
        int totalSteps = 0;
        while (!pq.isEmpty()) {
            int[] tree = pq.poll();
            int step = getMinSteps(forest, m, n, start, tree);
            if (step == -1)
                return -1;
            else
                totalSteps += step;

            start[0] = tree[0];
            start[1] = tree[1];
        }

        return totalSteps;
    }

    public int getMinSteps(List<List<Integer>> forest, int m, int n, int[] start, int[] tree) {
        int[][] dicts = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(start);
        visited[start[0]][start[1]] = true;

        int step = 0;
        //BFS 是一层一层的扫，所以有 int size = queue.size() 去拿到当前层的元素个数
        while (!queue.isEmpty()) {
            int size = queue.size();
            //loop current level of BFS
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                if (curr[0] == tree[0] && curr[1] == tree[1]) {
                    return step;
                }

                for (int[] d : dicts) {
                    int nr = curr[0] + d[0];
                    int nc = curr[1] + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n
                            || forest.get(nr).get(nc) == 0 || visited[nr][nc]) continue;

                    //mark this element has been visited in current level, and not the target element, don't need visit again.
                    visited[nr][nc] = true;
                    //add the element of next level
                    queue.add(new int[]{nr, nc});
                }
            }

            step++;
        }

        return -1;
    }
}
