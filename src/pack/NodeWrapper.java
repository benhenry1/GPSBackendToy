package pack;
/**
 * A quick Node class that helped me implement my Dijkstras algorithm
 * @author Ben
 */
public class NodeWrapper implements Comparable {
	public int node;
	public int distTo;
	
	public NodeWrapper(int node, int distTo) {
		this.node = node;
		this.distTo = distTo;
	}
	
	public void newDist(int dist) {
		distTo = dist;
	}

	@Override
	public int compareTo(Object o) {
		NodeWrapper that = (NodeWrapper) o;
		if (this.distTo < that.distTo)
			return -1;
		else if (this.distTo > that.distTo) 
			return 1;
		return 0;
	}
}
