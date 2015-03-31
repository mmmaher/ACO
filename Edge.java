import java.io.*;
import java.util.*;
import static java.lang.Math.*; 

public class Edge {

	private City[] endCities;
	private double length;
	private double pheromoneLevel;

	private double computeLength(double x1, double y1, double x2, double y2) {
		return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
	}

	//public Edge(double[] city1, int index1, double[] city2, int index2) {
	public Edge(City city1, City city2) {
		
		endCities = new City[] { city1, city2};
		length = computeLength(city1.getX(), city1.getY(), city2.getX(), city2.getY());

		/* All edges are given a minimum level of pheromone to start with */
		pheromoneLevel = 0.01;
	}

	public double getLength() { return this.length; }

	public City[] getEndCities() { return this.endCities; }

	public double getPheromoneLevel() { return this.pheromoneLevel; }

	public void updatePheromoneLevel(double amount) { this.pheromoneLevel = amount; }

	public void printEdge() {
		System.out.println("Edge between city " + endCities[0].getID() + " and city " + endCities[1].getID());
	}
}