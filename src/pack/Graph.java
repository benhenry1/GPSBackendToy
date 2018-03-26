package pack;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This is a wrapper class for the GraphParser
 * to make interacting with it cleaner
 * @author Ben
 *
 */
public class Graph {
	
	public ArrayList<LinkedList<Edge>> adjList;
	private int size;
	
	
	public Graph(GraphParser p) {
		this.adjList = p.getAdjList();
		this.size = p.totalNodes;
	}
	
	//Allows you to create an empty graph of a given size
	public Graph(int size) {
		adjList = new ArrayList<LinkedList<Edge>>(size);
		this.size = size;
	}
	
	//Add an edge into the graph
	public void addEdge(int from, int to, int weight) {
		//increase the size of the adjList if the indices are greater than its size
		if (from >= adjList.size() || to >= adjList.size()) {
			while (adjList.size() <= Math.max(from,  to)) {
				adjList.add(new LinkedList<Edge>());
			}
		}
		
		LinkedList<Edge> fr = adjList.get(from);
		if (fr == null)
			fr = new LinkedList<Edge>();
		
		fr.add(new Edge(from, to, weight));
		
		size++;
	}
	
	public int getSize() {
		return size;
	}
	
	//Returns a linked list of all edges in the graph. This was used for debugging purposes
	public LinkedList<Edge> getEdges() {
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for(LinkedList<Edge> e : adjList) {
			edges.addAll(e);
		}
		return edges;
	}
	
	//Returns a linked list of all edges coming from node i.
	public LinkedList<Edge> getEdges(int i) {
		return adjList.get(i);
	}
}