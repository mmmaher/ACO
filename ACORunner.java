import java.io.*;
import java.util.*;
import java.util.HashMap;
import Problem.java;

public class ACORunner {

	/* Algorithm constants */
	private static int numAnts;
	private static int numCities;
	private static int numIterations;
	private static int numEdges;

	private static Problem problem;
	private static HashMap<String, Edge> edges;
	private static ArrayList<Ant> ants;

	private static double pheromoneFactor;
	private static double heuristicFactor;
	private static double evaporationFactor;
	private static double wearingAwayFactor_1;
	private static double wearingAwayFactor_2; 
	private static double greedyFactor;

	private static int[] bestTour;
	private static String[] bestTourEdges;
	private static double bestTourLength;

	private static Random rand = new Random();

	public ACORunner(Problem problem_, int numAnts_, int numIterations_) {
		problem = problem_;
		numCities = problem.getNumCities();
		numAnts = numAnts_;
		numIterations = numIterations_;
		initializeAnts();
		initializeEdges();
	}
	
	/* Makes an edge between each city and every other city, and adds
	it to the global arraylist of edges. */
	private static void initializeEdges() {
		numEdges = (numCities * (numCities - 1))/2;
		edges = new HashMap<String, Edge>();

		for (int i = 1; i < numCities; i++) {
			for (int j = i + 1; j <= numCities; j++)
				String temp_key = String.valueOf(i) + String.valueOf(j);
				Edge temp = new Edge(problem.getCity(i), i, problem.getCity(j), j);
				edges.put(temp_key, temp);
		}
	}

	private static void initializeAnts() {
		for (int i = 0; i < numAnts; i++) {
			Ant temp = new Ant(numCities);
			ants.add(temp);
		}
	}

	public static void printBestTour() {

	}

	private static void startAnts() {
		for (int i = 0; i < numAnts; i++) {
			int temp = rand.nextInt(numCities) + 1;
			ants.get(i).addCityToPath(temp);
		}
	}

	private static String getEdgeBetweenCities(int city1, int city2) {
		String edgeString;
		if (city1 < city2) {
			edgeString = String.valueOf(city1) + String.valueOf(city2);
		}
		else {
			edgeString = String.valueOf(city2) + String.valueOf(city1);
		}
		return edgeString;
	}

	private static int chooseSpecified(double num, double[] array) {
		// sees where specified num falls in the array
		for (int i = 0; i < array.length; i++) {
			if (num < array[i]) {
				return i;
			}
		}
		System.out.println("ERROR HERE BAD, looking for "+num);
		return 0;
	}

	private static void calcAllChoices(Ant ant) {
		double sum;
		int currCity = ant.getCurrCity();

		// calculate the sum of all the choices probabilities, 
		// aka the function's denominator
		for (city : ant.getCitiesNotVisited()) {
			Edge currEdge = edges.get(getEdgeBetweenCities(currCity, city));

			double value = (currEdge.getPheromoneLevel()) * (1/currEdge.getLength());
			// TODO add in appropriate factors, wherever they go...

			sum += value;
		}

		// TODO calculate the numerator... 

		return sum;
	}

	private static void calculateChoice(int fromCity, int toCity, double sumChoices) {

	}

	private static void findPaths() {
		double min_value, max_value, randomNum;

		for (ant : ants) {
			double sum = 0.0;
			double totalChoiceSum = calcAllChoices(ant);
			for (int i = 0; i < ant.getCitiesNotVisited().size(); i++) {
				// Sums will correspond to the index of the city in the list of uncvisited cities
				double sums[] = new double[ant.getCitiesNotVisited.size()];
				sum += calculateChoice(ant.getCurrCity(), Integer.intValue(ant.getCitiesNotVisited().get(i)), totalChoiceSum);
				sums[i] = sum;
				if (sum < min_value) {
				min_value = sum;
				}
				if (sum > max_value) {
					max_value = sum;
				}

			}
			// TODO implement choosing the next edge correctly up above

			randomNum = (rand.nextDouble() * (max_value - min_value)) + min_value;

			// Chosenone should be the index value of the city to be removed
			int chosenIndex = chooseSpecifiedB(randomNum, sums);
			int chosenCity = ant.getCitiesNotVisited().get(chosenIndex);
			Edge chosenEdge = edges.get(getEdgeBetweenCities(ant.getCurrCity(), chosenCity));

			// So at this point...
			// chosenIndex - holds index of the city in the citiesNotVisited list
			// chosenCity - is the actual integer value at that index, so the city that was chosen
			// chosenEdge - the edge between the ants current city, and the chosen city
			// Now: add city to the path (also removes it frm list of unvisited), add the edge to path edges
			// Add edge and city adds the city and edge, removes city frm list, and increments current index
			ant.addEdgeAndCity(chosenCity, chosenEdge);
			
		}

	}

	public static void run() {

		for (int i = 0; i < numIterations; i++) {
			startAnts();

			// For loop should be equal to the path length, and path must contain every city
			for (int k = 0; k < numCities; k++) {
				findPaths();
			}






		}

	}


}