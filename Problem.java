import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Problem extends Edges{
	
	public static Cities cities;
	public static Edges edges;

	// file variable
	private static BufferedReader reader = null;

	public Problem() {
		this.cities = new Cities();
		this.edges = new Edges();
	}

	public static int getFitness() { return 0; }

	public static void updatePheromoneLevel(int start, int end, double value) {
		// updates edge from start to end city with new pheromone value
	}

	public static double getLength(int start, int end) {
		// returns distance between two cities
		return 0.;
	}

	public static double getPheromone(int start, int end) {
		// returns pheromone level on edge between two cities
		return 0;
	}

	public static void evaporatePheromone() {
		// evaporate all edges
	}

	public static void readFile(File f) {
		
		try {
			reader = new BufferedReader(new FileReader(f));
			String line;
			int counter = 0;
			while ((line = reader.readLine()) != null) {
				String pattern = "([A-Z]).*";
				if (Pattern.matches(pattern, line)) { continue; }
				
				int index = 0;
				double x = 0, y = 0;
				boolean started = false;
				String num = "";
				for (int i = 0; i < line.length(); i++) {
					String letter = line.substring(i, i+1);
					if (letter.equals(" ") && !started) {
						continue;
					}
					if (letter.equals(" ")) {
						x = Double.parseDouble(num);
						num = "";
						index++;
						started = false;
						continue;
					}
					num = num + letter;
					started = true;
				}
				y = Double.parseDouble(num);
				City newCity = new City(counter, x, y);
				cities.addCity(counter, newCity);
				counter++;

			}
			reader.close();
			
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", f);
			e.printStackTrace();
		}
	}
	
		
	public void initializeEdges() {

		String edgeKey;
		int numCities = this.cities.numCities();

		for (int i = 1; i < numCities; i++) {
			for (int j = i ; j < numCities; j++) {
				if ( j != i) {
					Edge newEdge = new Edge(cities.getCity(i), cities.getCity(j));
					this.edges.addEdge(createKeyWithIDs(i,j), newEdge);
				}
			}
		}
	}

}
