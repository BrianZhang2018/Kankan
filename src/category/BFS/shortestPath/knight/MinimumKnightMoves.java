package category.BFS.shortestPath.knight;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-knight-moves/
 *
 * it's symmetric in four quadrants, so if we can reach x,y in one quadrant then we can do it for all others in the same number of moves too.
 *
 * Created by brianzhang on 1/20/21.
 */
public class MinimumKnightMoves {
    public static void main(String[] args) {
        System.out.println(minKnightMoves(5,5));
        System.out.println(minKnightMoves(1,1));
    }

    public static int minKnightMoves(int x, int y) {
        // it's symmetric, we just calculate in one quadrant
        x = Math.abs(x); y = Math.abs(y);
        int[][] dirs = new int[][]{{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
        Deque<int[]> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.addLast(new int[]{0, 0});
        visited.add("0,0");
        int result = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            while(size-- > 0){
                int[] currPos = queue.poll();
                if (currPos[0] == x && currPos[1] == y) return result;
                for (int[] currDir : dirs) {
                    int nx = currPos[0] + currDir[0];
                    int ny = currPos[1] + currDir[1];
                    if (visited.add(nx + "," + ny) && nx >=-1 && ny >=-1) { // not able to reach (1,1) in first quadrant if nx, ny >=0
                        queue.addLast(new int[]{nx, ny});
                    }
                }
            }
            result++;
        }

        return -1;
    }
}
