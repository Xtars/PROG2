package Graphs;

import java.util.Map;
import java.util.Collections;
import java.util.List;

public interface Graph {
	// <N> is your node-class. For instance a City-class
	public <N> void add(N node);
	public <N> void connect(N from, N to);
	public <N> void disconnect(N n1, N n2);
	public <N> void remove(N node);
	public <N> void setConnectionWeight(N from, N to, String name, int weight);
	public <N> Collections getEdgesFrom(N node);
	public <N> Collections getEdgesBetween(N n1, N n2);
	public Map getNodes();
	public String toString();	
}
