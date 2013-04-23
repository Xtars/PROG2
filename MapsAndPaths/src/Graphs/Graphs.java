package Graphs;

import java.util.*;

//import java.util.*;

public class Graphs{
	
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
	
	public <N> ArrayList<Dijkstra<N>> fastestPath(Graph<N> g, N from, N to){
		if (!pathExists(g, from, to)){
			throw new NoSuchElementException("No path exists between those nodes.");
		} else {
			int lowestTime = Integer.MAX_VALUE;
			N lowestNode = null;
			HashMap<N, Dijkstra<N>> hm = new HashMap<>();
			for (N n : g.getNodes()){
				hm.put(n, new Dijkstra<N>());
			}
			
			hm.get(from).setTime(0);
			hm.get(from).setSettled(true);
			
			N current = (N) from;
			while (!hm.get(to).getSettled()){
				for (HashSet<Edge<N>> hs : g.getEdgesFrom(current)){
					for (Edge<N> e : hs){
						if (hm.get(current).getTime() + e.getWeight() < hm.get(e.getDestination()).getTime()){
							hm.get(e.getDestination()).setTime(hm.get(current).getTime() + e.getWeight());
							hm.get(e.getDestination()).setFrom(current);
							hm.get(e.getDestination()).setEdge(e);
						}
					}
				}
				
				lowestTime = Integer.MAX_VALUE;
				lowestNode = null;
				for (N n : g.getNodes()){
					if (!hm.get(n).getSettled() && hm.get(n).getTime() < lowestTime){
						lowestTime = hm.get(n).getTime();
						lowestNode = n;
					}
				}
				hm.get(lowestNode).setSettled(true);
				current = lowestNode;
			}
			ArrayList<Dijkstra<N>> path = new ArrayList<>();
			N cn = to;
			while (cn != from){
				path.add(hm.get(cn));
				cn = (N) hm.get(cn).getFrom();
			}
			Collections.reverse(path);
			return path;
		}
	}
}
