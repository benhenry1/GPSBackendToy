package pack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Reads in a formatted file of type .GR
 * and represents it as a graph in Java
 * using an adjacency list of edges.
 * 
 * @author Ben
 *
 */
public class GraphParser {
	
	ArrayList<LinkedList<Edge>> adjList = new ArrayList<LinkedList<Edge>>();
	public int totalNodes;
	public int totalEdges;
	
	public GraphParser(String filename) {
	  try {
		File f = new File(filename);
		FileReader r = new FileReader(f);
		BufferedReader reader = new BufferedReader(r);
		
		//reads in all information to be put in an adjacency matrix later
		String thisline;
		while ((thisline = reader.readLine()) != null) {
			String[] split = thisline.split(" ");
			
			//There are formatted comments in this file, so skip everything that's not an edge
			if (!split[0].equals("a"))
				continue;
			int from = Integer.parseInt(split[1]);
			int to = Integer.parseInt(split[2]);
			int weight = Integer.parseInt(split[3]);
			
			//increase the size of the adjList if the indices are greater than its size
			if (from >= adjList.size() || to >= adjList.size()) {
				while (adjList.size() <= Math.max(from,  to)) {
					adjList.add(new LinkedList<Edge>());
				}
			}
		
			
			//Update the linkedlists to contain the current edge
			LinkedList<Edge> fr = adjList.get(from);
		   // LinkedList<Edge> t = adjList.get(to);
		    
		    //If the 'from' linkedlist is null, we have to make a new one
		    if (fr == null)
		    	fr = new LinkedList<Edge>();
//		    if (t == null)
//		    	t = new LinkedList<Integer>();
		    
		  //Create a new edge, put it in the index of from
		    Edge e = new Edge(from, to, weight);
		    if (!fr.contains(e))
		    	fr.add(e);
		    
		    
//		    if (!t.contains(from))
//		    	t.add(new Edge(to, weight));

		    adjList.set(from, fr);
//		    adjList.set(to, t);
			
		}
		reader.close();
	  } catch (Exception e) {
		  e.printStackTrace();
		  System.out.println("There was an error reading in the file.");
	  }
		int edges = 0;
		for (LinkedList<Edge> e : adjList) { 
				edges += e.size();
		}
		this.totalEdges = edges / 2;
		this.totalNodes = adjList.size();
		
	}
	
	public ArrayList<LinkedList<Edge>> getAdjList() {
		return adjList;
	}
	
	
}
