package category.unionfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Union find is used to determine the cycle when add a new edge
 *
 * Created by brianzhang on 1/13/19.
 */
public class RedundantConnection {

    public static void main(String[] args) {
        RedundantConnection redundantConnection = new RedundantConnection();
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        int[] res = redundantConnection.findRedundantConnection(edges);
        System.out.println(res[0] + " , " + res[1]);

        List<Integer> test = new ArrayList<>();
        Collections.sort(test);
    }

    class UnionFindSet {
        private int[] parents;
        private int[] ranks; //tree heights

        public UnionFindSet(int n) {
            parents = new int[n + 1];
            ranks = new int[n + 1];
            for (int i = 0; i < parents.length; ++i) {
                parents[i] = i;
                ranks[i] = 1;
            }
        }

        public boolean Union(int u, int v) {
            int pu = find(u);
            int pv = find(v);
            if (pu == pv) return false;

            if (ranks[pv] > ranks[pu])
                parents[pu] = pv;
            else if (ranks[pu] > ranks[pv])
                parents[pv] = pu;
            else {
                parents[pv] = pu;
                ranks[pu] += 1;
            }

            return true;
        }

        //1. find the root node
        //2. path compression
        public int find(int u) {
            //(parents[u] == u) is the root node as the root node pointing to itself
            // (and all the node was initiated as parents[u] = u in the beginning of this function)
            while (parents[u] != u) {
                //parent point to the grandfater node
                parents[u] = parents[parents[u]];
                //assign the value grandfather node to u, then check again whether parents[u] == u in while condition
                u = parents[u];
            }
            return u;
        }

        public int find2(int u) {

            if (parents[u] != u) {
                parents[u] = find2(parents[u]);
            }
            return parents[u];
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        UnionFindSet s = new UnionFindSet(edges.length);
        for (int[] edge : edges)
            if (!s.Union(edge[0], edge[1]))
                return edge;
        return null;
    }
}

