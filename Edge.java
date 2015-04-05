import java.io.*;
import java.util.*;
import static java.lang.Math.*; 

public class Edge {

	private City[] cities;
	private double length;
	private double pheromoneLevel;

	//public Edge(double[] city1, int index1, double[] city2, int index2) {
	public Edge(City city1, City city2) {
		
		if ((city1 != null) && (city2 != null)) {
			this.cities = new City[] { city1, city2};
			this.length = computeLength();
		} else {
			System.out.println("ERROR: tried to create edge with NULL city");
			System.exit(0);
			this.length = 0;
		}

		/* All edges are given a minimum level of pheromone to start with */
		this.pheromoneLevel = 0.01;
	}

	private double computeLength() {
 
 		City c1 = this.cities[0];
 		City c2 = this.cities[1];
 		return sqrt(pow(c2.getX() - c1.getX(), 2) + pow(c2.getY() - c1.getY(), 2));
	}

	public City startCity() { return this.cities[0]; }
	public City endCity() { return this.cities[1];}

	public double getLength() { return this.length; }

	public double getPheromoneLevel() { return this.pheromoneLevel; }
	public void updatePheromoneLevel(double amount) { this.pheromoneLevel = amount; }

	public void printEdge() {
		System.out.print("City " + this.cities[0].getID() + " to City " + this.cities[1].getID() + " ");
		System.out.println(String.format("Length: %.2f", this.length));
	}
}