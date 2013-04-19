package Graphs;

class Edge<N> {
	private N destination;
	private String name;
	private int weight;
	
	Edge(N destination, String name, int weight){
		this.destination = destination;
		this.name = name;
		this.weight = weight;
	}
	
	public N getDestination(){
		return destination;
	}
	
	public String getName(){
		return name;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public void setWeight(int n){
		weight = n;
	}
	
	public String toString(){
		return "till " + destination + " med " + name + " tar " + weight;
	}
}
