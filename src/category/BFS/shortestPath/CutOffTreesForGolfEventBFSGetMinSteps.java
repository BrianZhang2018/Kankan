package category.BFS.shortestPath;

import java.util.*;

/**
 * https://leetcode.com/problems/cut-off-trees-for-golf-event/
 *
 * Solve the problems: Find the shortest path between each binaryTree
 *
 * The worst case time complexity could be O(m^2 * n^2) (m = number of rows, n = number of columns)
 * since there are m * n trees and for each BFS worst case time complexity is O(m * n) too.
 *
 * https://www.youtube.com/watch?v=OFkLC30OxXM
 * Created by brianzhang on 2/18/19.
 */
public class CutOffTreesForGolfEventBFSGetMinSteps {
    public static void main(String[] args) {
        CutOffTreesForGolfEventBFSGetMinSteps test = new CutOffTreesForGolfEventBFSGetMinSteps();
        List<List<Integer>> forest = new ArrayList<>();
        forest.add(Arrays.asList(4, 3, 2));
        forest.add(Arrays.asList(0, 0, 5));
        forest.add(Arrays.asList(8, 7, 6));
        System.out.println(test.cutOffTree(forest));
    }

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) return 0;

        int m = forest.size();
        int n = forest.get(0).size();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

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
            int step = getMinSteps(forest, start, tree); // BFS find the shortest path
            if (step == -1) //don't have the path between two trees
                return -1;
            else
                totalSteps += step;

            start[0] = tree[0];
            start[1] = tree[1];
        }
        return totalSteps;
    }

    // Find the shortest path
    public int getMinSteps(List<List<Integer>> forest, int[] start, int[] tree) {
        int m = forest.size();
        int n = forest.get(0).size();
        int[][] dicts = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;

        int step = 0;
        // BFS 一层一层(level wise)的搜索，一层代表一步, 无论你在这一层的哪个位置，上一层都可以通过一步到达, 所以到第几层，就是需要 层数-1 步
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 搜索当前层的所有点, 'for' loop here which loop all the elements in current level, then you will exactly know result in which level,
            // otherwise the above while (!queue.isEmpty()) is enough.
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == tree[0] && curr[1] == tree[1]) {
                    return step;
                }
                for (int[] d : dicts) {
                    int nr = curr[0] + d[0];
                    int nc = curr[1] + d[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || forest.get(nr).get(nc) == 0 || visited[nr][nc]) {
                        continue;
                    }
                    visited[nr][nc] = true; // mark this element has been visited in current level, don't need visit again.
                    queue.add(new int[]{nr, nc});
                }
            }
            step++;
        }
        return -1;
    }
}
