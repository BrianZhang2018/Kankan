package category.BFS;

import java.util.*;

public class RottingOranges {
    public static void main(String[] args) {
        System.out.println(orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
    }

    public static int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList();
        int countFresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    countFresh++;
                } else if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        if (countFresh == 0) return 0;

        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int m = grid.length, n = grid[0].length;
        int mins = 0;
        while (!queue.isEmpty() && countFresh > 0) {
            int size = queue.size();
            while (size > 0) {
                size--;
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int nx = curr[0] + dir[0];
                    int ny = curr[1] + dir[1];

                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                        queue.add(new int[]{nx, ny});
                        grid[nx][ny] = 2;
                        countFresh--;
                    }
                }
            }
            mins++;
        }

        return countFresh == 0 ? mins : -1;
    }
}
