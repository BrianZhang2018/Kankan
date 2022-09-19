package category.unionfind.detectCycle.unDirectedGraphByUnionFind;

/**
 * https://leetcode.com/problems/redundant-connection/
 *
 * Union find is used to determine the cycle in an "undirected" graph
 * Union find + weight + Path-Compression(flat binaryTree)
 *
 * Kruskal’s Algorithm is also leveraged this algorithm
 * https://algorithms.tutorialhorizon.com/kruskals-algorithm-minimum-spanning-tree-mst-complete-java-implementation/
 * 
 * https://www.youtube.com/watch?v=0jNmHPfA_yE - Union Find
 *
 * Created by brianzhang on 1/13/19.
 */
public class RedundantConnection {
    public static void main(String[] args) {
        RedundantConnection test = new RedundantConnection();
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        int[] res = test.findRedundantConnection(edges);
        System.out.println(res[0] + " , " + res[1]);
    }

    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return null;
    }

    class UnionFind {
        private int[] parents;
        private int[] ranks;  // weighted: size of parent 1, 2, 3...n

        public UnionFind(int n) {
            parents = new int[n + 1];
            ranks = new int[n + 1];
            for (int i = 0; i < parents.length; ++i) {
                parents[i] = i;
                ranks[i] = 1; //
            }
        }

        public boolean union(int u, int v) {
            int pu = find(u), pv = find(v);
            if (pu == pv) return false;   // find circle as has the same parent root

            // merge the "small" disjoint binaryTree to big one by comparing the size (ranking) to flat the binaryTree
            if (ranks[pu] < ranks[pv]){
                parents[pu] = pv;   // make "smaller" disjoint binaryTree point to larger one
                ranks[pu]++;        // weight++
            }
            else if (ranks[pv] < ranks[pu]){
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
        public int find(int vertex) {
            // "parents[vertex] == vertex" means vertex is the root node as the root node pointing to itself
            // (and all the node was initiated as parents[vertex] = vertex in the beginning of this function)
            while (parents[vertex] != vertex) {
                // optimization: Path-compression, parent point to the grandfather node which help to flat the tree, thus find operation could take ALMOST in O(1).
                // omit it doesn't impact the functionality
                parents[vertex] = parents[parents[vertex]];
                // assign the value grandfather node to vertex, then check again whether parents[vertex] == vertex in while condition
                vertex = parents[vertex];
            }
            return vertex;
        }

/*
            public int find1(int node, int[] parents) {
                while(node != parents[node]) {
                    node = parents[node];
                }
                return node;
            }

            private void union(int a, int b, int[] parents) {
                int aRoot = find(a, parents);
                int bRoot = find(b, parents);
                if(aRoot == bRoot) return;
                parents[aRoot] = bRoot;
            }
        */
    }
}

