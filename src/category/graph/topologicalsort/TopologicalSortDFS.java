package category.graph.topologicalsort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 4/18/19.
 */
//DFS
public class TopologicalSortDFS {
    private boolean[] canFinish;
    private boolean[] visited;
    //先导课
    private List<Integer>[] depends;
    private boolean canFinish(int course) {
        if (visited[course]) return canFinish[course];
        visited[course] = true;
        for(int c: depends[course]) {
            if (!canFinish(c)) return false;
        }
        //canFinish[course] = true;
        return canFinish[course] = true;
        //return true;
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        canFinish = new boolean[numCourses];
        visited = new boolean[numCourses];
        depends = new List[numCourses];
        for(int i=0; i<numCourses; i++) depends[i] = new ArrayList<Integer>();
        for(int i=0; i<prerequisites.length; i++) {
            depends[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for(int i=0; i<numCourses; i++) {
            if (!canFinish(i)) return false;
        }
        return true;
    }
}
