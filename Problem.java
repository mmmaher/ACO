import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Problem extends Graph{
	

	//Instance variables
	//public static Graph graph;
	public static Cities cities;

	// file variable
	private static BufferedReader reader = null;


	// Constructor, reads file and creates cities and graph
	public Problem(File f) {
		super(cities);
		this.cities = new Cities();
		this.readFile(f);
		this.cities.printCities();
		super.initGraph(cities);
	}

/*
	// updates edge from start to end city with new pheromone value
	public void updatePheromoneLevel(City c1, City c2, double value) { 
		this.graph.updatePheromoneLevel(c1, c2, value); 
	}

	// returns distance between two cities
	public double getLength(City c1, City c2) {
		return this.graph.getLength(c1, c2);
	}

	// returns pheromone level on edge between two cities
	public double getPheromone(City c1, City c2) {
		return this.graph.getPheromone(c1, c2);
	}

	// Evaporate pheromone on all edges in the graph
	public void evaporatePheromone(double evapFactor) { this.graph.evaporatePheromone(evapFactor); }
*/

	// Read in a TSP file
	public void readFile(File f) {
		
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
}
