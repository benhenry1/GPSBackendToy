package pack;
/**
 * Each edge holds the source, destination, and weight.
 * 
 * @author Ben
 *
 */
public class Edge implements Comparable {
	public int edgeTo;
	public int edgeFrom;
	public int weight;
	
	public Edge(int from, int to, int weight) {
		edgeTo = to;
		edgeFrom = from;
		this.weight = weight;	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + edgeTo;
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (edgeTo != other.edgeTo)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public int compareTo(Object o) {
		Edge c = (Edge) o;
		// TODO Auto-generated method stub
		if (this.weight < c.weight) {
			return -1;
		} else if (this.weight > c.weight) {
			return 1;
		}
		return 0;
	}
}
