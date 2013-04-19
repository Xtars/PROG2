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
	
	/*public void remove(N n){
		if (!nodes.contains(n))
			throw new NoSuchElementException("That node does not exist.");
				
		// get index  of the to-be-removed node so we know from where we have to update the matrix
		int pos = indexOf(n);
		int i;
		for (i = 0; i < connections.length; i++){
			connections[i][pos] = null;
			connections[pos][i] = null;
		}
		
		for (i = pos; i < connections.length; i++){
			for (int j = 0; j < connections.length; j++){
				
				if (i == j){
					connections[i][j] = null;
				}
				
				// if either i or j equals connections.length-1 (the last index of the matrix),
				// the value must be null since we are removing an entry from the Graph.
				else if (j == connections.length-1 || i == connections.length-1){
					connections[i][j] = null;
					connections[j][i] = null;
				}
				
				// if it is our first iteration j also equals i here which means that the value is (and should remain) null
				// if it is a later iteration, the value has already been updated
				// so we jump ahead to the next entry that haven't been updated
				else if (j == pos){
					j = pos+1;
				}
				
				// if j is smaller than i all we have to do is update the current entry to the entry that is "below" or "to the right"
				else if (j < i){
					connections[i][j] = connections[i+1][j];
					connections[j][i] = connections[j][i+1];
				}
				
				// if j is bigger than i however, the current entry should be updated to be the entry "below" AND "to the right"
				else if (j > i){
					connections[i][j] = connections[i+1][j+1];
					connections[j][i] = connections[j+1][i+1];
				}
			} // inner-for loop
		} // outer-for loop
		
		//  remove the node
		nodes.remove(n);
	}*/
	
	public void connect(N from, N to, String name, int weight){
		if (!nodes.contains(from) || !nodes.contains(to))
			throw new NoSuchElementException("One of the nodes does not exist.");
		
		int fPos = indexOf(from);
		int tPos = indexOf(to);
		
		connections[fPos][tPos] = new Edge(to, name, weight);
		connections[tPos][fPos] = new Edge(from, name, weight);
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
