package Graphs;

public class Dijkstra<N> implements Comparable<Dijkstra<N>>{
	private int time;
	private boolean settled;
	private N from;
	private Edge e;
	
	Dijkstra(){
		time = Integer.MAX_VALUE;
		settled = false;
		from = null;
		e = null;
	}

	public int getTime(){
		return time;
	}
	public boolean getSettled(){
		return settled;
	}
	public N getFrom(){
		return from;
	}
	public Edge getEdge(){
		return e;
	}
	
	public void setTime(int time){
		this.time = time;
	}
	public void setSettled(boolean settled){
		this.settled = settled;
	}
	public void setFrom(N from){
		this.from = from;
	}
	public void setEdge(Edge e){
		this.e = e;
	}
	
	public int compareTo(Dijkstra d){
		return this.time - d.time;
	}
	
	public String toString(){
		return "" + e + "(total time " + time + ")";
	}
}
