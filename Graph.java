
import java.util.*;

public class Graph {

	private Map<Integer, Map<Integer, Edge>> graph;
	
	// Constructor
	public Graph(Cities cities) { 
		this.graph = new HashMap<Integer, Map<Integer, Edge>>(); 
	}


	// Creates a graph with City ID as key and list of edges 
	// from that Cityas value
	public void initGraph(Cities cities) {
		for (int i = 1; i < cities.numCities(); i++) {
			City c1 = cities.getCity(i);
			Map<Integer, Edge> edges = new HashMap<Integer, Edge>();

			for (int j = 1; j < cities.numCities(); j++) {
				City c2 = cities.getCity(j);

				if (i != j) {
					Edge newEdge = new Edge(c1, c2);	
					edges.put(j, newEdge);
				}
			}
			this.graph.put(c1.getID(), edges);
		}
	}


	// Update pheromone level between two cities
	public void updatePheromoneLevel(City c1, City c2, double value) {
		this.graph.get(c1.getID()).get(c2.getID()).updatePheromoneLevel(value);
	}


	// Get pheromone level between two cities
	public double getPheromone(City c1, City c2) {
		return this.graph.get(c1.getID()).get(c2.getID()).getPheromoneLevel();
	}


	// Get length between two cities. 
	public double getLength(City c1, City c2) {
		return this.graph.get(c1.getID()).get(c2.getID()).getLength();
	}


	// Return the list of edges that a given city has from it
	public List<Edge> getCityEdges(City city) { 
		List<Edge> temp = new ArrayList<Edge>();
		for (Map.Entry<Integer, Edge> edgePair : this.graph.get(city.getID()).entrySet()) {
			temp.add(edgePair.getValue());
		}
		return temp;
	}


	// Evaporate pheromone on all edges, edges are unidirectional (23,1) is the same as (1,23)
	// so makes sure to only evaporate an edge once.
	public void evaporatePheromone(double evapFactor) {
		for (Map.Entry<Integer, Map<Integer, Edge>> edgeMapPair : this.graph.entrySet()) {
			
			int id = edgeMapPair.getKey();
			for (Map.Entry<Integer, Edge> edgePair : edgeMapPair.getValue().entrySet()) {
				Edge edge = edgePair.getValue();
				
				// Only evaporate on edges to a city with a higher ID
				if (id < edge.endCity().getID()) {
					//Pheromone evaporates the same in ACS and Elitist 
					double newAmount = (1 - evapFactor) * edge.getPheromoneLevel();
					edge.updatePheromoneLevel(newAmount);
					//System.out.println(entry.getKey() + "/" + entry.getValue());
				}
			}	
		}
	}


	// Print the graph
	public void printGraph() {
		for (Map.Entry<Integer, Map<Integer, Edge>> edgeMapPair : this.graph.entrySet()) {
			System.out.println("City " + edgeMapPair.getKey());
			for (Map.Entry<Integer, Edge> edgePair : edgeMapPair.getValue().entrySet()) {
				Edge edge = edgePair.getValue();
				System.out.print("      ");
				edge.printEdge();
			}
		}
	}	
}