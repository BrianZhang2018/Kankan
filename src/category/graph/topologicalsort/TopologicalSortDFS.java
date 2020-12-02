package category.graph.topologicalsort;

import java.util.*;

/**
 * https://leetcode.com/problems/course-schedule/
 *
 * 拓扑排序用来遍历一个有向无环图（DAG, Directed Acyclic Graph）
 * 如果遍历不了一个图或者说不存在一个拓扑排序，说明图中有环
 *
 * 在图论中，拓扑排序（Topological Sorting）是一个有向无环图（DAG, Directed Acyclic Graph）的所有顶点的线性序列。且该序列必须满足下面两个条件：

     1. 每个顶点出现且只出现一次。
     2. 若存在一条从顶点 A 到顶点 B 的路径，那么在序列中顶点 A 出现在顶点 B 的前面

   有向无环图（DAG）才有拓扑排序，非DAG图没有拓扑排序一说

 * Created by brianzhang on 4/18/19.
 */
public class TopologicalSortDFS {

    public static void main(String[] args) {
        TopologicalSortDFS test = new TopologicalSortDFS();
        System.out.println(test.canFinish(4, new int[][]{{0, 1}, {2, 1}, {2, 0}}));
        System.out.println(test.canFinish(4, new int[][]{{0, 1}, {2, 1}, {1, 2}}));

    }

    private boolean[] canFinish;
    private boolean[] visited;
    private List<Integer>[] depends; // 先导课

    public boolean canFinish(int numCourses, int[][] preRequisites) {
        canFinish = new boolean[numCourses];
        visited = new boolean[numCourses];
        depends = new List[numCourses];

        for (int i = 0; i < numCourses; i++)
            depends[i] = new ArrayList<>();

        for (int[] i : preRequisites) {
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
