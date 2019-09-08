package category.tree.minimumspanningtree;

import java.util.*;
/**
 * Simple Union-find solution without weight strategy - compare with (KrushkalMinimumSpanningTree.java)
 * https://github.com/zxqiu/leetcode-lintcode/blob/master/tree/Minimum_Spanning_Tree.java
 * 
 * Created by brianzhang on 3/24/19.
 */
public class MinimumSpanningTree {
    public static void main(String[] args) {
        int n = 6; // numTotalAvailableCities
        int numTotalAvailableRoads = 3;
        List<List<Integer>> roadsAvailable = new ArrayList<>();
        roadsAvailable.add(Arrays.asList(new Integer[]{1,4}));
        roadsAvailable.add(Arrays.asList(new Integer[]{4,5}));
        roadsAvailable.add(Arrays.asList(new Integer[]{2,3}));

        int numNewRoadsContruct = 4;
        List<List<Integer>> costNewRoadsConstruct = new ArrayList<>();
        costNewRoadsConstruct.add(Arrays.asList(new Integer[]{1,2,5}));
        costNewRoadsConstruct.add(Arrays.asList(new Integer[]{1,3,10}));
        costNewRoadsConstruct.add(Arrays.asList(new Integer[]{1,6,2}));
        costNewRoadsConstruct.add(Arrays.asList(new Integer[]{5,6,5}));

        MinimumSpanningTree test = new MinimumSpanningTree();
        System.out.println(test.getMinimumCostConstruct(n, numTotalAvailableRoads, roadsAvailable, numNewRoadsContruct, costNewRoadsConstruct));
    }
    
    // unionfind solution without "weight" ccompare with KrushkalMinimumSpanningTree.java
    public int getMinimumCostConstruct(int n, int numTotalAvaiableRoads, List<List<Integer>> roadsAvailable,
                                       int numNewRoadsContruct,  List<List<Integer>> costNewRoadsConstruct) {
        if (n < 2) {
            return 0;
        }
        // Initialize available roads - O(Nn)
        UnionFind uf = new UnionFind(n + 1);
        int groupCount = n;
        for (List<Integer> road: roadsAvailable) {
            int i = road.get(0);
            int j = road.get(1);
            groupCount -= uf.union(i, j)? 1: 0;
        }
        // Initialize new roads - O(MlogM)
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.val , b.val));
        for (List<Integer> road: costNewRoadsConstruct) {
            heap.offer(new Node(road.get(0), road.get(1), road.get(2)));
        }
        // construct roads - O(Mn)
        int cost = 0;
        while (!heap.isEmpty() && groupCount > 1) {
            Node road = heap.poll();
            if (uf.union(road.x, road.y)) {
                groupCount--;
                cost += road.val;
            }
        }
        return groupCount > 1? -1: cost;
    }
 
    class UnionFind {
        int[] parents;
        //build the disjointset
        public UnionFind(int size) {
            parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
        }
 
        private int root(int i) {
            while (parents[i] != i) {
                i = parents[i];
            }
            return i;
        }
 
        public boolean find(int i, int j) { // O(n)
            return root(i) == root(j);
        }
 
        public boolean union(int i, int j) { // O(n)
            if (find(i, j)) {
                return false;
            } else {
                parents[root(i)] = j; // parents[root(j)] = i, also work here. Here can be improved with "weight" so that append the small tree to big tree when do union
                return true;
            }
        }
    }
}

class Node {
    int x;
    int y;
    int val;
    public Node(int i, int j, int s) {
        x = i;
        y = j;
        val = s;
    }
}