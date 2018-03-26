package pack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Initiate the menu to interact with the user,
 * read the graph file once upon startup.
 * @author Ben
 *
 */
public class Menu {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Graph graph;
	
	public static void main(String[] args) throws Exception {
		System.out.println("Loading Application...");
		GraphParser gp = new GraphParser("USA-road-d.NE.GR");
		Graph g = new Graph(gp);
		graph = g;
		
		//We've created the graph on startup, now we just have to interact with the user
		
		while(true) {
			System.out.println("1. Find the Shortest Route\n"
					+ "2. Find Most Direct Route\n"
					+ "3. Quit\n");
			System.out.print("Enter your choice: ");
			String s = br.readLine();
			int i = 0;
			try {
				i = Integer.parseInt(s);
			} catch (Exception e) {
				System.out.println("Invalid choice.");
				continue;
			}
			if (i == 1) {
				findShort();
			} else if (i == 2) {
				findDirect();
			} else if (i == 3) {
				break;
			} else {
				System.out.println("Invalid choice.");
				continue;
			}
			
		}
//        System.out.print("Enter String");
//        String s = br.readLine();
//		System.out.println(g.getEdges(1).size());
//		Dijkstras d = new Dijkstras(g, 2012);
//		System.out.println("We done?");
//		Iterable<Edge> s = d.pathTo(2004);
//		int totweight = 0;
		
		
	}
	
	static private void findShort() throws IOException {
		System.out.println("\nEnter your location. (Due to dataset limitations, your \"location\" is some number between 1 and 1,524,453" );
		System.out.print("Location: ");
		String s = br.readLine();
		int location = 0;
		try {
			location = Integer.parseInt(s);
		} catch (Exception e) {
			System.out.println("Invalid choice.");
			return;
		}
		if (location < 1 || location > 1524453) {
			System.out.println("Invalid choice.");
		}
		System.out.print("Enter your destination\nDestination: ");
		s = br.readLine();
		int dest = 0;
		try {
			dest = Integer.parseInt(s);
		} catch (Exception e) {
			System.out.println("Invalid choice.");
			return;
		}
		if (dest < 1 || dest > 1524453) {
			System.out.println("Invalid choice.");
		}
		System.out.println("Loading...");
		Dijkstras d = new Dijkstras(graph, location);
		Iterable<Edge> t = d.pathTo(dest);
		printPath(t);
	}
	
	static private void printPath(Iterable<Edge> e) {
		int totweight = 0;
		int totsteps = 0;
		for (Edge d : e) {
			System.out.println("Follow " + d.edgeFrom + " for " + d.weight + " meters and turn onto "+ d.edgeTo + ".");
			totweight += d.weight;
			totsteps++;
		}
		System.out.println("You have arrived!\nTotal distance: " + totweight / 1000 + "km. Total number of turns: " + totsteps);
	}
	
	public static void findDirect() throws Exception {
		System.out.println("\nEnter your location. (Due to dataset limitations, your \"location\" is some number between 1 and 1,524,453" );
		System.out.print("Location: ");
		String s = br.readLine();
		int location = 0;
		try {
			location = Integer.parseInt(s);
		} catch (Exception e) {
			System.out.println("Invalid choice.");
			return;
		}
		if (location < 1 || location > 1524453) {
			System.out.println("Invalid choice.");
		}
		System.out.print("Enter your destination\nDestination: ");
		s = br.readLine();
		int dest = 0;
		try {
			dest = Integer.parseInt(s);
		} catch (Exception e) {
			System.out.println("Invalid choice.");
			return;
		}
		if (dest < 1 || dest > 1524453) {
			System.out.println("Invalid choice.");
		}
		System.out.println("Loading...");
		
		BreadthFirstSearch b = new BreadthFirstSearch(graph);
		Iterable<Edge> t = b.bfsStepCounter(location, dest);
		printPath(t);
	}
}
