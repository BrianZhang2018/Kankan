package category.graph.topologicalsort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 4/18/19.
 */
public class TopologicalSortDFS {

    public static void main(String[] args) {
        TopologicalSortDFS test = new TopologicalSortDFS();
        System.out.println(test.canFinish(4, new int[][]{{1, 0}, {3, 1}, {2, 0}, {1, 2}, {3, 2}}));
    }

    private boolean[] canFinish;
    private boolean[] visited;
    private List<Integer>[] depends;//先导课

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        canFinish = new boolean[numCourses];
        visited = new boolean[numCourses];
        depends = new List[numCourses];

        for (int i = 0; i < numCourses; i++) depends[i] = new ArrayList<>();

        for(int[] i : prerequisites){
            depends[i[1]].add(i[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i)) return false;
        }
        return true;
    }

    // 递归回溯
    private boolean dfs(int course) {
        if (visited[course]) return canFinish[course];

        visited[course] = true;
        for (int c : depends[course]) {
            if (!dfs(c)) return false;
        }
        return canFinish[course] = true;
    }
}
