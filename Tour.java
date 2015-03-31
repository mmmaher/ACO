import java.util.*;

public class Tour {
	private static final int ALPHA = 2;
	private static final int BETA = 2;

	private int[] citiesVisited;
	private int[] path;
	private double tourLength;
	private int numCities;
	private Random rand = new Random();
	private static Problem problem;
	private boolean elite;
	private static int prevCity;

	public Tour(Problem problem, int numberOfCities, boolean elitism) {
		this.problem = problem;
		this.elite = elitism;
		this.numCities = numberOfCities;

		citiesVisited = new int[numCities];
	}

	public void beginNewTour() {
		Arrays.fill(citiesVisited, 0);
		Arrays.fill(path, -1); // will help catch errors!
		tourLength = 0.0;

		int startCity = rand.nextInt(numCities);
		prevCity = startCity;
		citiesVisited[startCity] = 1;
		path[0] = startCity;

		// run loop numCities - 1 times
		for (int i = 1; i < numCities; i++) {
			int currCity = chooseNexCity();
			citiesVisited[currCity] = 1;
			path[i] = currCity;

			tourLength += problem.getLength(prevCity, currCity);

			// wither away pheromone on edge if not elite

			prevCity = currCity;
		}
	}

	public void putDownPheromone() {
		for (int i = 0; i < numCities; i++) {

		}
	}

	// chooses next city based on pheromone concentration and distance between cities
	private int chooseNexCity() {
		double[] probabilities = new double[numCities];
		double sumPherLen = 0.;
		double sumProbs = 0.;

		// first calculate probabilities of choosing each possible next city
		for (int i = 0; i < numCities; i++) {
			if (citiesVisited[i] ==1) continue;

			probabilities[i] = problem.getPheromone(prevCity, i) * problem.getLength(prevCity, i);
			sumPherLen+=probabilities[i];
		}

		// then update probabilities to divide by sumPherLen
		for (int i = 0; i < numCities; i++) {
			if (citiesVisited[i] ==1) continue;

			// probabilities[i] += probabilities[i] / sumPherLen;
			sumProbs += probabilities[i] / sumPherLen;
			probabilities[i] = sumProbs;
		}

		// choose new path "randomly", weighted with probs just assigned
		double value = rand.nextDouble() * sumProbs;
		return (chooseSpecified(value, probabilities));
	}

	private int chooseSpecified(double num, double[] array) {
		// sees where specified num falls in the array
		for (int i = 0; i < array.length; i++) {
			if (citiesVisited[i] == 1) continue;
			if (num < array[i]) {
				return i;
			}
		}
		System.out.println("ERROR HERE BAD, looking for "+num);
		return 0;
	}

}