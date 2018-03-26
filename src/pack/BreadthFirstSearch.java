package pack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Wraps around a Graph and allows you to perform 
 * BFS on it multiple times.
 * 
 * @author Ben
 *
 */
public class BreadthFirstSearch {

	private Graph g;
	
	private boolean[] visited;
	private int[] parent;
	
	private ArrayList<LinkedList<Edge>> adjList;
	
	public BreadthFirstSearch(Graph p) {
		this.g = p;
		this.adjList = g.adjList;
		
		visited = new boolean[g.getSize()];
		parent = new int[g.getSize()];
	}

	//Finds the shortest path assuming all edge weights are 1
	//This encourages the use of highways
	public Iterable<Edge> bfsStepCounter(int source, int dest) throws Exception {
		Queue<Integer> q = new LinkedList<Integer>();
		int[] distTo = new int[g.getSize()];
		q.add(source);
		visited[source]= true;
		parent[source] = source;
		
		
		outerloop:
		while(!q.isEmpty()) {
			int n = q.remove();
			for (Edge v : adjList.get(n)){
				if (!visited[v.edgeTo]) {
					q.add(v.edgeTo);
					visited[v.edgeTo] = true;
					distTo[v.edgeTo] = distTo[n] + 1;
					parent[v.edgeTo] = n;
					if (v.edgeTo == dest) {
						break outerloop;
					}
				}
			}
				
		}
		
		//Begin creating the Iterable<Edge> to be returned
		LinkedList<Edge> edges = new LinkedList<Edge>();
		Edge found;
		int i = dest;
		int j = parent[dest];
		while (i != parent[i]) {
			found = null;
			LinkedList<Edge> list = g.getEdges(j);
			for (Edge e : list) {
				if (e.edgeTo == i) {
					found = e;
					break;
				}
			}
			if (found == null) {
				throw new Exception("Huh, something really messed up while creating the BFS Stack. Sorry!");
			}
			edges.push(found);
			i = j;
			j = parent[j];
		}
		
		
		reset();
		return edges;
	}
	

	
	public void reset() {
		for (int i = 0; i < visited.length; i++)
			visited[i] = false;
	}
}
