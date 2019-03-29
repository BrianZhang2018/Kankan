package category.unionfind;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * I referenced the RedundantConnection.java and
 * https://algorithms.tutorialhorizon.com/kruskals-algorithm-minimum-spanning-tree-mst-complete-java-implementation/
 * <p>
 * Created by brianzhang on 3/26/19.
 */
public class KrushkalMinimumSpanningTree {

    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        List<Edge> edges = graph.mst();
        graph.printGraph(edges);
    }

    static class Graph {
        public List<Edge> inputEdges = new ArrayList<Edge>();
        public List<Edge> mstEdges = new ArrayList<Edge>();
        public int vertices;
        public int[] parents;
        public int[] ranking;

        public Graph(int vertices) {
            this.vertices = vertices;
            parents = new int[vertices];
            ranking = new int[vertices];
        }

        public void addEdge(int vertex1, int vertex2, int weight) {
            inputEdges.add(new Edge(vertex1, vertex2, weight));
        }

        public List<Edge> mst() {
            PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
            for (Edge edge : inputEdges)
                pq.add(edge);

            makeDisjointSet(parents);
            int index = 0;
            while (index < vertices - 1) {
                Edge edge = pq.poll();
                int root1 = find(edge.vertex1);
                int root2 = find(edge.vertex2);

                if (root1 == root2)
                    continue;

                mstEdges.add(edge);
                union(root1, root2);
                index++;
            }
            return mstEdges;
        }

        private int find(int vertex) {
            while (parents[vertex] != vertex) {
                parents[vertex] = parents[parents[vertex]];
                vertex = parents[vertex];
            }
            return vertex;
        }

        private void union(int root1, int root2) {
            if (ranking[root1] > ranking[root2]) {
                parents[root2] = root1;
                ranking[root2]++;
            } else if (ranking[root1] < ranking[root2]) {
                parents[root1] = root2;
                ranking[root1]++;
            } else {
                parents[root2] = root1;
                ranking[root2]++;
            }
        }

        public void makeDisjointSet(int[] parents) {
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
        }

        public void printGraph(List<Edge> edgeList) {
            for (int i = 0; i < edgeList.size(); i++) {
                Edge edge = edgeList.get(i);
                System.out.println("Edge-" + i + " vertex1: " + edge.vertex1 +
                        " vertex2: " + edge.vertex2 +
                        " weight: " + edge.weight);
            }
        }
    }

    static class Edge {
        public int vertex1;
        public int vertex2;
        public int weight;

        public Edge(int vertex1, int vertex2, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }
    }
}
