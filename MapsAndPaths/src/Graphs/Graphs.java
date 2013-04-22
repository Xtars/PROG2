package Graphs;

import java.util.*;

//import java.util.*;

public class Graphs{
	public <N> List<N> getPath(N n1, N n2){
		List<N> path = new ArrayList<N>();
		
		return path;
	}
	
	private static <N> void dfs(Graph<N> g, N n, Set<N> visited){
		visited.add(n);
		N next = null;
		for (HashSet<Edge<N>> hs : g.getEdgesFrom(n)){
			for (Edge<N> e : hs){
				if (!visited.contains(e.getDestination())){
					next = (N) e.getDestination();
					dfs(g, next, visited);
				}
			}
		}
	}
	
	public <N> boolean pathExists(Graph<N> g, N from, N to){
		Set<N> visited = new HashSet<N>();
		dfs(g, from, visited);
		return visited.contains(to);
	}
}
