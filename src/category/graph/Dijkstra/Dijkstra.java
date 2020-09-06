package category.graph.Dijkstra;

import java.util.*;

/**
 * Find the shortest path from Node A to Z
 * Dijkstra is greedy algorithm - easy understand
 *
 * https://www.youtube.com/watch?v=GazC3A4OQTE
 */
public class Dijkstra {
    public static void main(String[] args) {
        // mark all the vertices
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex D = new Vertex("D");
        Vertex M = new Vertex("M");
        Vertex P = new Vertex("P");
        Vertex R = new Vertex("R");
        Vertex Z = new Vertex("Z");

        // set the edges and weight
        A.adjacencies = new Edge[]{new Edge(M, 8), new Edge(B, 2)};
        B.adjacencies = new Edge[]{new Edge(D, 11), new Edge(M, 1)};
        D.adjacencies = new Edge[]{new Edge(B, 11)};
        //D.adjacencies = new Edge[] { new Edge(Z, 2) };
        M.adjacencies = new Edge[]{new Edge(R, 8)};
        P.adjacencies = new Edge[]{new Edge(Z, 18)};
        R.adjacencies = new Edge[]{new Edge(P, 15)};
        Z.adjacencies = new Edge[]{new Edge(P, 18)};

        computePaths(A); // start from A
        System.out.println("Distance to " + Z + ": " + Z.minDistance);
        List<Vertex> path = getShortestPathTo(Z);
        System.out.println("Path: " + path);
    }

    public static void computePaths(Vertex source) {
        source.minDistance = 0.0;
        PriorityQueue<Vertex> vertexMinQueue = new PriorityQueue<>();
        vertexMinQueue.add(source);

        while (!vertexMinQueue.isEmpty()) {
            Vertex u = vertexMinQueue.poll();

            for (Edge e : u.adjacencies) {
                Vertex ev = e.target;
                double distanceThroughU = u.minDistance + e.weight;
                if (distanceThroughU < ev.minDistance) {
                    ev.minDistance = distanceThroughU;
                    ev.previous = u;
                    vertexMinQueue.add(ev);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }
}

class Vertex implements Comparable<Vertex> {
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;

    public Vertex(String argName) {
        name = argName;
    }

    public String toString() {
        return name;
    }

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }
}

class Edge {
    public final Vertex target;
    public final double weight;

    public Edge(Vertex edgeEndVertex, double edgeWeight) {
        target = edgeEndVertex;
        weight = edgeWeight;
    }
}