package category.graph.topologicalsort;

import java.util.ArrayList;
import java.util.List;

/**
 * this solution can be used in the below problem
 * https://leetcode.com/problems/course-schedule-ii/
 * 
 * Time Complexity: O(V+E) 稀疏图->O(V), 稠密图->O(V2), V is vertex, E is edge
 * Created by brianzhang on 4/18/19.
 */
public class TopologicalSortBFS {
    public static void main(String[] args) {
        System.out.println(canFinish(4, new int[][]{{1,0}, {3,1}, {2,0}, {1,2}, {3, 2}}));
    }

    public static boolean canFinish(int n, int[][] prerequisites) {
        List<Integer>[] G = new ArrayList[n];
        int[] indegree = new int[n];  //入度
        List<Integer> bfs = new ArrayList(); // you can also use a queue here
        //initiate the graph
        for (int i = 0; i < n; ++i) 
            G[i] = new ArrayList<Integer>();

        for (int[] e : prerequisites) {
            G[e[1]].add(e[0]); // e[1] is the prerequiste course of e[0]
            indegree[e[0]]++; // e[1]->e[0], so indegree[e[0]]++
        }
        //find the start node -> indegree = 0, 没有前驱节点
        for (int i = 0; i < n; ++i)
            if (indegree[i] == 0)
                bfs.add(i);
        //把入度为零的vertex放入list, 看做remove the vertex which "indegree=0" and add into the list, 
        //then, "--indegree" the connected vertex as the prerequsite course has done, then recursive the same logic
        for (int i = 0; i < bfs.size(); ++i){
            for (int j : G[bfs.get(i)]){
                if (--indegree[j] == 0) //means we can remove the vertex as prerequsite course
                    bfs.add(j);
            }
        }

        return bfs.size() == n;
    }

// Queue version:
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<Integer>[] adjlist = new ArrayList<>[numCourses];

        for(int i=0; i<numCourses; i++) 
            adjlist[i] = new ArrayList<>();

        for(int i=0; i<prerequisites.length; i++) {
            adjlist[prerequisites[i][1]].add(prerequisites[i][0]);
            indegree[prerequisites[i][0]] ++;
        }
        int finish = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i=0; i<numCourses; i++) {
            if (indegree[i] == 0) 
                queue.add(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.remove();
            finish ++;
            if (adjlist[course] == null) continue;

            for(int c: adjlist[course]) {
                //删除这条边
                indegree[c] --;
                //维护这个队列
                if (indegree[c] == 0) queue.add(c);
            }
        }
        return finish == numCourses;
    }
}
