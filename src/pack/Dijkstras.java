package pack;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Performs Dijkstras algorithm on a given graph.
 * Creates the minimum spanning tree from the provided
 * source node.
 * @author Ben
 *
 */
public class Dijkstras {
	private NodeWrapper[] nodes;
	private Edge[] edgeTo;
	private PriorityQueue<NodeWrapper> pq;
	
	public Dijkstras(Graph g, int source) {
		//For this application, all edge weights will be positive
		nodes = new NodeWrapper[g.getSize()];
		edgeTo = new Edge[g.getSize()];
		for (int v = 0; v < g.getSize(); v++) {
			nodes[v] = new NodeWrapper(v, Integer.MAX_VALUE);
			
		} nodes[source].newDist(0);
		
		//Now relax vertices in order from S
		pq = new PriorityQueue<NodeWrapper>(g.getSize());
		pq.add(nodes[source]);
		while (!pq.isEmpty()) {
			NodeWrapper v = pq.poll();
			for (Edge e : g.adjList.get(v.node)) {
				relax(e);
			}
		}
		
	}
	
	
	private void relax(Edge e) {
		int u = e.edgeFrom;
		int v = e.edgeTo;
		if (nodes[v].distTo > nodes[u].distTo + e.weight) {
			nodes[v].distTo = nodes[u].distTo + e.weight;
			edgeTo[v] = e;
			if (pq.contains(nodes[v])) {
				pq.remove(nodes[v]); pq.add(nodes[v]);
			} else {
				pq.add(nodes[v]);
			}
				
		}
	}
	
	public Iterable<Edge> pathTo(int v) {
		LinkedList<Edge> path = new LinkedList<Edge>();
		for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.edgeFrom]) {
			path.push(e);
		}
		return path;
	}
}
