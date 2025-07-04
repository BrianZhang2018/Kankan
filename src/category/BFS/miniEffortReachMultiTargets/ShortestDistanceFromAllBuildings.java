package category.BFS.miniEffortReachMultiTargets;

import java.util.*;

// https://leetcode.com/problems/shortest-distance-from-all-buildings/

public class ShortestDistanceFromAllBuildings {

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int buildingCount = 0;
    public int shortestDistance(int[][] grid) {
        // count buildings
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    buildingCount++;
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    res = Math.min(res, bfs(grid, i, j));
                }
            }
        }

        return res != Integer.MAX_VALUE ? res : -1 ;
    }

    public int bfs(int[][] grid, int m, int n) {
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{m, n});

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[m][n] = true;
        int houseReached = 0;
        int distanceSum = 0;
        int step = 0;
        while (!queue.isEmpty() && houseReached != buildingCount) { // careful
            for (int i = queue.size(); i > 0; --i) {
                int[] curr = queue.poll();
                int x = curr[0], y = curr[1];
                visited[x][y] = true;
                if (grid[x][y] == 1) {
                    distanceSum += step;
                    houseReached++;
                    continue;
                }
                for (int[] dir : dirs) {
                    int nx = dir[0] + x;
                    int ny = dir[1] + y;
                    if (nx >= 0 && ny >= 0 && nx < grid.length && ny < grid[0].length
                            && !visited[nx][ny] && grid[nx][ny] != 2) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
            step++; // Note: add current round into total steps, not do in the beginning of the for loop
        }

        // If we did not reach all houses, then any cell visited also cannot reach all houses.
        // Set all cells visited to 2, so we do not check them again and return MAX_VALUE.
        if (houseReached != buildingCount) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 0 && visited[i][j]) {
                        grid[i][j] = 2;
                    }
                }
            }
            return Integer.MAX_VALUE;
        } else {
            return distanceSum;
        }
    }
}
