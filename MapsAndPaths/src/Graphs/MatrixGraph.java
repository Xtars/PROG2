package Graphs;

import java.util.ArrayList;

public class MatrixGraph<N> extends Graphs {
	private Edge[][] connections;
	private ArrayList<N> nodes;
	
	public MatrixGraph(int amountOfNodes){
		connections = new Edge[amountOfNodes][amountOfNodes];
		nodes.ensureCapacity(amountOfNodes);
		nodes = new ArrayList<N>();
	}
}
