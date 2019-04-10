package category.Dijkstra;

import java.util.*;

/**
 * Find the shortest path from Node A to Z
 * Dijkstra is greedy algorithm
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
		A.adjacencies = new Edge[] { new Edge(M, 8), new Edge(B, 2) };
		B.adjacencies = new Edge[] { new Edge(D, 11), new Edge(M, 1) };
		D.adjacencies = new Edge[] { new Edge(B, 11) };
		//D.adjacencies = new Edge[] { new Edge(Z, 2) };
		M.adjacencies = new Edge[] { new Edge(R, 8) };
		P.adjacencies = new Edge[] { new Edge(Z, 18) };
		R.adjacencies = new Edge[] { new Edge(P, 15) };
		Z.adjacencies = new Edge[] { new Edge(P, 18) };

		computePaths(A); // run Dijkstra
		System.out.println("Distance to " + Z + ": " + Z.minDistance);
		List<Vertex> path = getShortestPathTo(Z);
		System.out.println("Path: " + path);
	}

	public static void computePaths(Vertex source) {
		source.minDistance = 0.0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			for (Edge e : u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
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

	public Edge(Vertex argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}