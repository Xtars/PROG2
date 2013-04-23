package Graphs;

import java.util.*;

public interface Graph<N> {
	// <N> is your node-class. For instance a City-class
	public void add(N node);
	public void connect(N from, N to, String name, int weight);
	public Set<HashSet<Edge<N>>> getEdgesFrom(N node);
	/*public void setConnectionWeight(N from, N to, String name, int weight);
	
	public void disconnect(N n1, N n2);
	public void remove(N node);
	public Set<Edge<N>> getEdgesBetween(N n1, N n2);
	public List<N> getNodes();
	public String toString();	
	public ArrayList<Dijkstra<N>> fastestPath(Graph<N> g, N from, N to);*/
}
