package Graphs;

import java.util.*;

public class ListGraph<N> extends Graphs implements Graph<N> {
	private Map<N, Set<Edge<N>>> nodes;
	
	public ListGraph(){
		nodes = new HashMap<N, Set<Edge<N>>>();
	}
	
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
		
		boolean exists = false;
		for (Edge<N> e : nodes.get(from)){
			if (e.getName().equals(name) && e.getDestination().equals(to)){
				exists = true;
				break;
			}
		}
		if (!exists){
			nodes.get(from).add(new Edge<N>(to, name, weight));
			nodes.get(to).add(new Edge<N>(from, name, weight));
		} else {
			throw new IllegalStateException("An edge with that name between these nodes already exist.");
		}
	}
	
	public Set<HashSet<Edge<N>>> getEdgesFrom(N node){
		Set<HashSet<Edge<N>>> hs = new HashSet<HashSet<Edge<N>>>();
		HashSet<Edge<N>> hsInner = new HashSet<Edge<N>>();
		
		for (Edge<N> e : nodes.get(node)){
			hsInner.add(e);
		}
		
		hs.add(hsInner);
		return hs;
	}
	
	public String toString(){
		String s = "";
		for(N n : nodes.keySet()){
			s += n.toString() + ": ";
			s += getEdgesFrom(n) + "\n";
		}
		return s;
	}
	
	/*public void disconnect(N n1, N n2){
		if (!nodes.containsKey(n1) || !nodes.containsKey(n2))
			throw new NoSuchElementException("One of the nodes does not exist.");
		
		nodes.get(n1)
		
	}
	
	public void setConnectionWeight(N from, N to, String name, int weight){
		
	}
	public void remove(N node);
	public Set<Edge<N>> getEdgesBetween(N n1, N n2);
	public List<N> getNodes();
	public String toString();	
	public ArrayList<Dijkstra<N>> fastestPath(Graph<N> g, N from, N to);*/
}
