package Graphs;

import java.util.*;

public class MatrixGraph<N> extends Graphs implements Graph<N>{
	private Set<Edge<N>>[][] connections;
	private ArrayList<N> nodes;
	
	public MatrixGraph(int amountOfNodes){
		connections = (HashSet<Edge<N>>[][])new HashSet[amountOfNodes][amountOfNodes];
		nodes = new ArrayList<N>();
	}

	public void add(N n){
		if (nodes.size() == connections.length)
			throw new IndexOutOfBoundsException("Too many nodes!");
		
		boolean exist = false;
		for (N check : nodes){
			if (check.equals(n)){
				exist = true;
				break;
			}
		}
		if (!exist)
			nodes.add(n);
		else
			System.out.println("That node already exist");
	}
	
	public void remove(N n){
		if (!nodes.contains(n))
			throw new NoSuchElementException("That node does not exist.");
				
		// get index  of the to-be-removed node so we know from where we have to update the matrix
		int pos = indexOf(n);
		int i;
		
		// set all entries related to n to null
		for (i = 0; i < connections.length; i++){
			connections[i][pos] = null;
			connections[pos][i] = null;
		}
		
		
		for (i = pos; i < connections.length; i++){
			for (int j = 0; j < connections.length; j++){
				
				// if i == j we are on the diagonal so we set the entry to null (you can't travel from A to A)
				if (i == j){
					connections[i][j] = null;
				}
				
				// if either i or j equals connections.length-1 (the last index of the matrix),
				// the value must be null since we are removing an entry from the Graph.
				else if (j == connections.length-1 || i == connections.length-1){
					connections[i][j] = null;
					connections[j][i] = null;
				}
				
				// if j is smaller than pos we update the current position(s) to the position "below" and "to the right" respectively
				else if (j < pos){
					connections[i][j] = connections[i+1][j];
					connections[j][i] = connections[j][i+1];
				}
				
				// if j is bigger than or equal to pos however, the current positions should be updated to be the positions "below" AND "to the right" for both
				else if (j >= pos){
					connections[i][j] = connections[i+1][j+1];
					connections[j][i] = connections[j+1][i+1];
				}
			} // inner-for loop
		} // outer-for loop
		
		//  remove the node
		nodes.remove(n);
	}
	
	public void connect(N from, N to, String name, int weight){
		if (!nodes.contains(from) || !nodes.contains(to))
			throw new NoSuchElementException("One of the nodes does not exist.");
		if (weight < 0)
			throw new IllegalArgumentException("The value of weight can't be negative.");
		if (from == to)
			throw new IllegalArgumentException("You can't connect the same node with itself.");
		
		int fPos = indexOf(from);
		int tPos = indexOf(to);
		
		if (connections[fPos][tPos] == null){
			connections[fPos][tPos] = new HashSet<Edge<N>>();
			connections[fPos][tPos].add(new Edge<N>(to, name, weight));
			connections[tPos][fPos] = new HashSet<Edge<N>>();
			connections[tPos][fPos].add(new Edge<N>(from, name, weight));
		} else {
			boolean exist = false;
			for (Edge<N> e : connections[fPos][tPos]){
				if (e.getName().equals(name)){
					exist = true;
					break;
				}
			}
			if (!exist){
				connections[fPos][tPos].add(new Edge<N>(to, name, weight));
				connections[tPos][fPos].add(new Edge<N>(from, name, weight));
			} else {
				throw new IllegalStateException("An edge with that name between these nodes already exist.");
			}
		}
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
	
	public void setConnectionWeight(N from, N to, String name, int weight){
		if (!nodes.contains(from) || !nodes.contains(to))
			throw new NoSuchElementException("One of the nodes does not exist.");
		if (weight < 0)
			throw new IllegalArgumentException("The value of weight can't be negative.");
		
		int fPos = indexOf(from);
		int tPos = indexOf(to);
		
		Set<Edge<N>> hs = connections[tPos][fPos];
		if (hs != null){
			boolean exist = false;
			// go through the edges, look for an edge with that name
			for (Edge<N> e : hs){
				// if we find the name, update the weight
				if (e.getName().equals(name)){
					e.setWeight(weight);
					exist = true;
					break;
				}
			}
			// if exist is true, there is a corresponding edge on the "reversed" position of the matrix
			if (exist){
				// reverse the position
				hs = connections[fPos][tPos];
				for (Edge<N> e : hs){
					// update that aswell
					if (e.getName().equals(name)){
						e.setWeight(weight);
						break;
					}
				}
			}
			if (!exist)
				throw new NoSuchElementException("No edge with that name between those nodes exist.");
		} else {
			throw new NoSuchElementException("No edge exist between those nodes.");
		}
	}
	
	public Set<HashSet<Edge<N>>> getEdgesFrom(N n){
		if (!nodes.contains(n))
			throw new NoSuchElementException("That node does not exist.");
		
		Set<HashSet<Edge<N>>> hs = new HashSet<HashSet<Edge<N>>>();
		int pos = nodes.indexOf(n);
		
		for(int i = 0; i < nodes.size(); i++){
			if (connections[pos][i] != null)
				hs.add(new HashSet<Edge<N>>(connections[pos][i]));
		}
		return hs;
	}
	
	public Set<Edge<N>> getEdgesBetween(N n1, N n2){
		if (!nodes.contains(n1) || !nodes.contains(n2))
			throw new NoSuchElementException("One of the nodes does not exist.");
		
		int p1 = indexOf(n1);
		int p2 = indexOf(n2);
		
		HashSet<Edge<N>> hs = new HashSet<Edge<N>>(connections[p1][p2]);
		
		return hs;
	}
	
	public List<N> getNodes(){
		List<N> al = new ArrayList<N>(nodes);
		return al;
	}
	
	public String toString(){
		String s = "";
		for(N n : nodes){
			s += n.toString() + ": ";
			s += getEdgesFrom(n) + "\n";
		}
		return s;
	}

	private int indexOf(N n){
		int pos = nodes.indexOf(n);
		if (pos < 0)
			throw new NoSuchElementException("Node does not exist.");
		return pos;
	}
}
