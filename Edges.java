
import java.util.*;

public class Edges {

	private Map<String, Edge> edges;
	
	public Edges() {
		
		this.edges = new HashMap<String, Edge>();
		
	}
	
	public void addEdge(String key, Edge newEdge) { this.edges.put(key, newEdge); }
	protected String createKeyWithIDs(int id1, int id2) { return String.format("%d, %d", id1, id2); }

	
	protected String getEdgeKeyBetweenCities(int city1, int city2) {
		String edgeString;
		if (city1 < city2) {
			edgeString = String.valueOf(city1) + String.valueOf(city2);
		}
		else {
			edgeString = String.valueOf(city2) + String.valueOf(city1);
		}
		// Edge edge = edges.get(edgeString);
		return edgeString;
	}
	
	protected void evaporatePheromone(double evapFactor) {
		for (Map.Entry<String, Edge> entry : edges.entrySet()) {
			//Pheromone evaporates the same in ACS and Elitist 
			double newAmount = (1 - evapFactor) * entry.getValue().getPheromoneLevel();
			entry.getValue().updatePheromoneLevel(newAmount);
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
	}

}
