import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class Ant {
	
	/* Citiesnotvisited holds the indices of the cities yet to be visited;
	path holds the string keys of the edges already visited */ 
	// private List<Integer> citiesNotVisited;
	private int[] path;
	// private String[] pathEdges;
	private double pathLength;
	// private int currPathIndex, numCities;
	private int numCities;

	public Ant(int numCities_) {
		this.numCities = numCities_;
		// citiesNotVisited = new ArrayList<Integer>();
		// resetNotVisited();
		path = new int[numCities];
		// pathEdges = new String[numCities];
		// currPathIndex = 0;
		pathLength = 0.0;
	}

	public int[] getPath() { return path; }

	public double getPathLength() { return pathLength; }

	public void newTour(Problem problem, boolean elitist) {
		Tour tour = new Tour(problem, numCities, elitist);
		tour.beginNewTour();
		path = tour.getPath();
		pathLength = tour.getLength();
	}

	// ant retraces path and adds pheromone
	public void addPheromoneToPath(Problem problem, double q) {
		if (path.length < 2)  return;

		double value = q / pathLength;
		for (int i = 1; i < numCities; i++) {
			problem.updatePheromoneLevel(path[i-1], path[i], value);
		}
	}

	// public void resetNotVisited() {
	// 	for (int i = 1; i <= numCities; i++) {
	// 		citiesNotVisited.add(i);
	// 	}
	// }

	// public List<Integer> getCitiesNotVisited() { return citiesNotVisited; }

	// public String[] getPathEdges() { return pathEdges; }

	// public int getCurrCity() { return path[currPathIndex-1]; }

	// public void resetPath() {
	// 	pathLength = 0.0;
	// 	currPathIndex = 0;
	// }

	// public void updatePathLength(double amount) { pathLength += amount; }

	// public static void addEdgeToPath(String edge) {
	// 	pathEdges[currPathIndex] = edge;
	// 	currPathIndex++;
	// }

	// public void addCityToPath(int city) {
	// 	citiesNotVisited.remove(Integer.valueOf(city));
	// 	path[currPathIndex] = city;
	// 	currPathIndex++;
	// }

	// public void addEdgeAndCity(int city, String edge) {
	// 	citiesNotVisited.remove(Integer.valueOf(city));
	// 	path[currPathIndex] = city;
	// 	pathEdges[currPathIndex] = edge;
	// 	currPathIndex++;
	// }

	// public boolean isTourComplete() { return citiesNotVisited.isEmpty(); }

}
