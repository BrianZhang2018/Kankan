package category.BFS.shortestPath.knight;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-knight-moves/
 *
 * If we can reach x,y in one quadrant then we can do it for all others in the same number of moves too.
 * Created by brianzhang on 1/20/21.
 */
public class MinimumKnightMoves {

    public int minKnightMoves(int x, int y) {
        // If we can reach x,y in one quadrant then we can do it for all others in the same number of moves too.
        x = Math.abs(x); y = Math.abs(y);
        if (x == 1 && y == 1) return 2;     // Special case for (1,1) because there is a more efficient way to reach it if we allow negative positions.
        int[][] dirs = new int[][]{{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
        Deque<int[]> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.addLast(new int[]{0, 0});
        visited.add("0,0");

        int result = 0;
        while (queue.size() > 0) {
            int size = queue.size();    // Number of unique positions reachable with exactly result moves.
            for (int i = 0; i < size; ++i) {
                int[] currPos = queue.poll();
                if (currPos[0] == x && currPos[1] == y) return result;
                
                for (int[] currDir : dirs) {
                    int newX = currPos[0] + currDir[0];
                    int newY = currPos[1] + currDir[1];
                    if (newX >= 0 && newY >= 0 && !visited.contains(newX + "," + newY)) { // The only time (newX >= -1 && newY >= -1) is necessary is if the final destination is (1,1).
                        visited.add(newX + "," + newY);
                        queue.addLast(new int[]{newX, newY});
                    }
                }
            }
            result++;
        }
        return -1;    // Can't be reached on an infinite chess board.
    }
}
