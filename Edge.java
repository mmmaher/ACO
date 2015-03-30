import java.io.*;
import java.util.*;
import static java.lang.Math.*; 

public class Edge {

	private int[] endCities;
	private double length;
	private double pheromoneLevel;

	private double computeLength(double x1, double y1, double x2, double y2) {
		return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
	}

	public Edge(double[] city1, int index1, double[] city2, int index2) {
		endCities = new int[2];
		endCities[0] = index1;
		endCities[1] = index2;
		length = computeLength(city1[0], city1[1], city2[0], city2[1]);

		/* All edges are given a minimum level of pheromone to start with */
		pheromoneLevel = 0.01;
	}

	public double getLength() { return length; }

	public int[] getEndCities() { return endCities; }

	public double getPheromoneLevel() { return pheromoneLevel; }

	public void updatePheromoneLevel(double amount) { pheromoneLevel = amount; }

	public void printEdge() {
		System.out.println("Edge between city " + endCities[0] + " and city " + endCities[1]);
	}
}