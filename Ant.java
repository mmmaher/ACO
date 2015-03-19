import java.io.*;
import java.util.*;
import Edge.java;
import java.util.List;
import java.util.ArrayList;

public class Ant {
	
	/* Citiesnotvisited holds the indices of the cities yet to be visited;
	path holds the string keys of the edges already visited */ 
	private List<Integer> citiesNotVisited;
	private int[] path;
	private String[] pathEdges;
	private double pathLength;
	private int currPathIndex;

	public Ant(int numCities) {
		citiesNotVisited = new ArrayList<Integer>();
		for (int i = 1; i <= numCities; i++) {
			citiesNotVisited.add(i);
		}
		path = new int[numCities];
		pathEdges = new String[numCities];
		currPathIndex = 0;
		pathLength = 0.0;
	}

	public static List<Integer> getCitiesNotVisited() {
		return citiesNotVisited;
	}

	public static int[] getPath() {
		return path;
	}

	public static String[] getPathEdges() {
		return pathEdges;
	}

	public static double getPathLength() {
		return pathLength;
	}

	public static int getCurrCity() {
		return path[currPathIndex-1];
	}

	public static void resetPath() {
		path = 0.0;
		currPathIndex = 0;
	}

	public static void updatePathLength(double amount) {
		pathLength += amount;
	}

	// public static void addEdgeToPath(String edge) {
	// 	pathEdges[currPathIndex] = edge;
	// 	currPathIndex++;
	// }

	public static void addCityToPath(int city) {
		citiesNotVisited.remove(Integer.valueOf(city));
		path[currPathIndex] = city;
		currPathIndex++;
	}

	public static void addEdgeAndCity(int city, String edge) {
		citiesNotVisited.remove(Integer.valueOf(city));
		path[currPathIndex] = city;
		pathEdges[currPathIndex] = edge;
		currPathIndex++;
	}

	public static boolean isTourComplete() {
		return citiesNotVisited.isEmpty();
	}

}