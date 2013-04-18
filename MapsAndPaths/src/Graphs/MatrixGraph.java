package Graphs;

import java.util.*;

public class MatrixGraph<N> extends Graphs implements Graph<N>{
	private Edge[][] connections;
	private ArrayList<N> nodes;
	
	public MatrixGraph(int amountOfNodes){
		connections = new Edge[amountOfNodes][amountOfNodes];
		//nodes.ensureCapacity(amountOfNodes);
		nodes = new ArrayList<N>();
	}
	
	public void add(N n){
		if (nodes.size() == connections.length)
			throw new IndexOutOfBoundsException("Too many nodes!");
		nodes.add(n);
	}
	
	public void connect(N from, N to, String name, int weight){
		int fPos = indexOf(from);
		int tPos = indexOf(to);
		
		connections[fPos][tPos] = new Edge(to, name, weight);
		connections[tPos][fPos] = new Edge(from, name, weight);
	}
	
	public void setConnectionWeight(N from, N to, String name, int weight){
		int fPos = indexOf(from);
		int tPos = indexOf(to);
		
		Edge e;
		
		e = connections[fPos][tPos];
		e.setWeight(weight);
		e.setName(name);
		
		e = connections[tPos][fPos];
		e.setWeight(weight);
		e.setName(name);
	}
	
	public Set<Edge> getEdgesFrom(N n){
		Set<Edge> edges = new HashSet<Edge>();
		int pos = nodes.indexOf(n);
		
		for(int i = 0; i < nodes.size(); i++){
			if (connections[pos][i] != null)
				edges.add(connections[pos][i]);
		}
		return edges;
	}
	
	public void disconnect(N n1, N n2){
		if (!nodes.contains(n1) || !nodes.contains(n2))
			throw new NoSuchElementException("One of the nodes does not exist.");
		
		int pos1 = nodes.indexOf(n1);
		int pos2 = nodes.indexOf(n2);
		
		if (connections[pos1][pos2] != null){
			connections[pos1][pos2] = null;
			connections[pos2][pos1] = null;
		}
	}
	
	public String toString(){
		String s = "";
		for(N n : nodes){
			s += n.toString() + ": ";
			s += getEdgesFrom(n) + "\n";
		}
		return s;
	}
	
	
	// help methods
	private int indexOf(N n){
		int pos = nodes.indexOf(n);
		if (pos < 0)
			throw new NoSuchElementException("Node does not exist.");
		return pos;
	}
}
