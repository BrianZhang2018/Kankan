package category.graph.topologicalsort;

import java.util.ArrayList;
import java.util.List;

/**
 * Time Complexity: O(V+E) 稀疏图->O(V), 稠密图->O(V2), V is vertex, E is edge
 *
 * Created by brianzhang on 4/18/19.
 */
public class TopologicalSortBFS {

    public static void main(String[] args) {
        System.out.println(canFinish(4, new int[][]{{1,0}, {3,1}, {2,0}, {1,2}, {3, 2}}));
    }

    public static boolean canFinish(int n, int[][] prerequisites) {
        ArrayList<Integer>[] G = new ArrayList[n];
        int[] indegree = new int[n];  //入度
        ArrayList<Integer> bfs = new ArrayList(); // you also can use a queue here
        //initiate the graph
        for (int i = 0; i < n; ++i) G[i] = new ArrayList<Integer>();
        for (int[] e : prerequisites) {
            G[e[1]].add(e[0]);
            indegree[e[0]]++;
        }
        //find the start node
        for (int i = 0; i < n; ++i)
            if (indegree[i] == 0)
                bfs.add(i);
        //find the vertex with '0' indegree by traverse and remove the edge (--indegree) which means the prerequsite course has done
        //always put the vertex with '0' indegree into the final ordering list as no previous vertex dependent
        for (int i = 0; i < bfs.size(); ++i){
            for (int j: G[bfs.get(i)]){
                if (--indegree[j] == 0) //means delete one edge
                    bfs.add(j);
            }
        }

        return bfs.size() == n;
    }

// Queue version:
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
//         int[] indegree = new int[numCourses];
//         List<Integer>[] adjlist = new List[numCourses];
//         for(int i=0; i<numCourses; i++) adjlist[i] = new ArrayList<>();
//         for(int i=0; i<prerequisites.length; i++) {
//             adjlist[prerequisites[i][1]].add(prerequisites[i][0]);
//             indegree[prerequisites[i][0]] ++;
//         }
//         int finish = 0;
//         LinkedList<Integer> queue = new LinkedList<>();
//         for(int i=0; i<numCourses; i++) {
//             if (indegree[i] == 0) queue.add(i);
//         }
//         while (!queue.isEmpty()) {
//             int course = queue.remove();
//             finish ++;
//             if (adjlist[course] == null) continue;

//             for(int c: adjlist[course]) {
//                 //删除这条边
//                 indegree[c] --;
//                 //维护这个队列
//                 if (indegree[c] == 0) queue.add(c);
//             }
//         }
//         return finish == numCourses;
//     }
}
