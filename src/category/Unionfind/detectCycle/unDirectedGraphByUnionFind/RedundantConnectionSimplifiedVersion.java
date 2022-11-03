package category.Unionfind.detectCycle.unDirectedGraphByUnionFind;

/**
 * https://leetcode.com/problems/redundant-connection/discuss/107984/10-line-Java-solution-Union-Find.
 */
public class RedundantConnectionSimplifiedVersion {

    public int[] findRedundantConnection(int[][] edges) {
        int[] parents = new int[edges.length];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            if(find(from, parents) == find(to, parents)) {
                return edge;
            }
            union(from, to, parents);
        }

        return null;
    }

    private int find(int node, int[] parents) {
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
}
