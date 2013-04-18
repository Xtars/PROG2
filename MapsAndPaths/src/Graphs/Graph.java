package Graphs;

import java.util.Map;
import java.util.Set;
import java.util.List;

public interface Graph<N> {
	// <N> is your node-class. For instance a City-class
	public void add(N node);
	public void connect(N from, N to, String name, int weight);
	public void setConnectionWeight(N from, N to, String name, int weight);
	public Set getEdgesFrom(N node);
	public void disconnect(N n1, N n2);
	/*public void remove(N node);
	public Collections getEdgesBetween(N n1, N n2);
	public Map getNodes();*/
	public String toString();	
}