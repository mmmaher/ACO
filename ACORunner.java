import java.io.*;
import java.util.*;
import java.lang.Math;

public class ACORunner {

	private static final int PRINT_INTERVAL = 1;

	private static int numAnts;
	private static int numTours;
	private static int numCities;

	private static double alpha;        // pheromone trail importance
	private static double beta;         // distance between cities importance
	private static double rho;          // global pheromone update
	private static double epsilon;      // wearing away factor
	private static double tau;          // wearing away factor
	private static double qnot;         // prob ant will choose best leg next
	private static int eliteAnts;       // number of ants in elitism
	private static boolean elitism;     // elitism or ACS

	// Random num generator
	private static Random rand = new Random();

	// Instance of the problem
	private Problem problem;

	// Algorithm specific data structures
	private ArrayList<Ant> ants = new ArrayList<Ant>();
	private Tour bestTour = new Tour();

// *********************** METHODS ************************** //
	
	// Constructor
	public ACORunner(	int numAnts_,
						int numTours_,
						double alpha_,
						double beta_, 
						double rho_,
						double epsilon_, 
						double qnot_,
						int eliteAnts_,
						boolean elite_) 
	{
		numAnts = numAnts_;
		numTours = numTours_;

		alpha = alpha_;
		beta = beta_;
		rho = rho_;
		epsilon = epsilon_;
		qnot = qnot_;
		eliteAnts = eliteAnts_;
		elitism = elite_;

		initializeAnts();
	}


	public void printBestTour() { this.bestTour.printTour(); }


	private void initializeAnts() {
		for (int i = 0; i < numAnts; i++) {
			Ant temp = new Ant();
			ants.add(temp);
		}
	}

	private void runTours() {

		for (int i = 0; i < numCities -1; i++) {

			for (Ant ant : ants) {
				City currCity;
				if (i == 0) {
					currCity = problem.cities.getCity(rand.nextInt(numCities));
					ant.startCity(currCity);
				} else { currCity = ant.getCurrentCity(); }

				List<Edge> availableEdges = problem.getCityEdges(currCity);
				Double randMove = rand.nextDouble();
				Edge tempEdge; // edge the ant chooses to move on

				if (availableEdges.isEmpty()) break;

				if (randMove < qnot && !elitism) {
					tempEdge = ant.trivialMove(availableEdges);

				} else { tempEdge = ant.moveToNext(availableEdges, alpha, beta); }

				if (tempEdge == null) break;

				if (!elitism) {
					// wear away pheromone if ACS
					double amount = (1 - epsilon) * tempEdge.getPheromoneLevel() + epsilon * tau;
					tempEdge.updatePheromoneLevel(amount);
				}
			}
		}
	}

	private void placePheromone(Ant ant, boolean bestSoFar) {
		ArrayList<City> path = new ArrayList<City>();
		path.addAll(ant.tour.getTour());
		double value = 1 / ant.tour.getLength(); // BETA HERE*** -- I think this should be rho, and only if not elitist
		
		if (bestSoFar) {
			if (!elitism) { value = value * rho; }
			else { value = value * eliteAnts; }
		}

		for (int j = 1; j < path.size(); j++) { //assumes >1 city
			problem.updatePheromone(path.get(j-1), path.get(j), value);
		}
	}


	private void retraceAntTour() {
		for (int i = 0; i < numAnts; i++) {
			placePheromone(ants.get(i), false);
		}
	}


	// Implementation of ACS and Elitist ACO
	public Tour run(Problem problem_) {

		problem = problem_;
		numCities = problem.cities.numCities();
		tau = problem.calculateTau();
		int iterationCounter = 0;

		// run as many times as we have iterations
		do {
			double iterationBestLength = Double.MAX_VALUE;
			int iterationBest = -1;

			// run tours for each ant
			runTours();

			// find the best tour of the iteration
			for (int j = 0; j < numAnts; j++) {
				double currLength = ants.get(j).tour.getLength();
				if (currLength < iterationBestLength) {
					iterationBestLength = currLength;
					iterationBest = j;
				}
			}

			// first evaporate pheromones
			problem.evaporatePheromone(rho); // rho HERE***

			// if elitist, put down pheromone for each ant
			if (elitism) { retraceAntTour(); }

			// put pheromone down on best path
			placePheromone(ants.get(iterationBest), true);

			// if this iteration's best tour is better than current best tour, update var
			if (iterationBestLength < bestTour.getLength()) {
				bestTour.resetTour();
				bestTour = new Tour(ants.get(iterationBest).tour.getTour(), iterationBestLength);
			}

			iterationCounter++;
			if (iterationCounter%PRINT_INTERVAL == 0) System.out.println("Iteration: "+iterationCounter);
		} while (iterationCounter < numTours);
		return bestTour;
	}
}
