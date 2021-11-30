package category.unionfind.detectCycle.unDirectedGraph;

/**
 * Union find + weight + flat binaryTree (Weighted union find and path compression)
 * Union find is used to determine the cycle in a "undirected" graph
 *
 * Kruskal’s Algorithm is also leveraged this algorithm
 * https://algorithms.tutorialhorizon.com/kruskals-algorithm-minimum-spanning-tree-mst-complete-java-implementation/
 *
 * Created by brianzhang on 1/13/19.
 * 
 * https://www.youtube.com/watch?v=0jNmHPfA_yE  - Union Find
 */
public class RedundantConnection {
    public static void main(String[] args) {
        RedundantConnection test = new RedundantConnection();
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        int[] res = test.findRedundantConnection(edges);
        System.out.println(res[0] + " , " + res[1]);
    }

    public int[] findRedundantConnection(int[][] edges) {
        UnionFindSet s = new UnionFindSet(edges.length);
        for (int[] edge : edges)
            if (!s.Union(edge[0], edge[1])) return edge;

        return null;
    }

    class UnionFindSet {
        private int[] parents;
        private int[] ranks;  // weighted: size of parent 1, 2, 3...n

        public UnionFindSet(int n) {
            parents = new int[n + 1];
            ranks = new int[n + 1];
            for (int i = 0; i < parents.length; ++i) {
                parents[i] = i;
                ranks[i] = 1;
            }
        }

        public boolean Union(int u, int v) {
            int pu = find(u), pv = find(v);

            if (pu == pv) return false;   // find circle as has the same parent root

            // merge the "small" disjoint binaryTree to big one by comparing the size (ranking) to flat the binaryTree
            if (ranks[pv] > ranks[pu]){
                parents[pu] = pv;   // make "smaller" disjoint binaryTree point to larger one
                ranks[pu]++;        // weight++
            }
            else if (ranks[pu] > ranks[pv]){
                parents[pv] = pu;
                ranks[pv]++;
            }
            else {
                parents[pv] = pu;
                ranks[pv] += 1;
            }

            return true;
        }

        /**
         * 1. find the root node of disjoint set
         * 2. path compression to flat the binaryTree
         */
        public int find(int u) {
            // parents[u] == u, is the root node as the root node pointing to itself
            // (and all the node was initiated as parents[u] = u in the beginning of this function)
            while (parents[u] != u) {
                parents[u] = parents[parents[u]];  // parent point to the grandfather node which flat the binaryTree
                u = parents[u];   // assign the value grandfather node to u, then check again whether parents[u] == u in while condition
            }
            return u;
        }

        /* public int find2(int u) {
             if (parents[u] != u) {
                 parents[u] = find2(parents[u]);
             }
             return parents[u];
        }*/
    }
}

