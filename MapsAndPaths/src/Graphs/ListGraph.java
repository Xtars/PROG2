package Graphs;

import java.util.*;

public class ListGraph<N> extends Graphs implements Graph<N> {
	private Map<N, Set<Edge<N>>> nodes = new HashMap<N, Set<Edge<N>>>();
	
	public void add(N node){
		if (nodes.containsKey(node))
			System.out.println("That node already exist");
		else
			nodes.put(node, new HashSet<Edge<N>>());
	}
	
	public void connect(N from, N to, String name, int weight){
		if (!nodes.containsKey(from) || !nodes.containsKey(from))
			throw new NoSuchElementException("One of the nodes does not exist.");
		if (weight < 0)
			throw new IllegalArgumentException("The value of weight can't be negative.");
		if (from == to)
			throw new IllegalArgumentException("You can't connect the same node with itself.");
		
		
		for (Edge<N> e : nodes.get(from)){
			
		}
			
	}
	public void setConnectionWeight(N from, N to, String name, int weight);
	public Set<HashSet<Edge<N>>> getEdgesFrom(N node);
	public void disconnect(N n1, N n2);
	public void remove(N node);
	public Set<Edge<N>> getEdgesBetween(N n1, N n2);
	public List<N> getNodes();
	public String toString();	
	public ArrayList<Dijkstra<N>> fastestPath(Graph<N> g, N from, N to);
}
