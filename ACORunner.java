import java.io.*;
import java.util.*;
import java.lang.Math;

public class ACORunner {
	private static final double WEARING_SIGMA = 1.;
	private static final double WEARING_TAUNOT = 1.;

	private static double alpha; // pheromone trail importance
	private static double beta; // distance between cities importance
	private static double phi; // local pheromone update
	private static double rho; // global pheromone update
	private static double qo; // prob ant will choose best leg next

	private ArrayList<Ant> ants = new ArrayList<Ant>();
	private Problem problem;

	private int numAnts;
	private int numIterations;
	private int numCities;

	private ArrayList<City> bestTour = new ArrayList<City>(); 
	private double bestTourLength;

	private boolean elitist;

	private static Random rand = new Random();

	public ACORunner(Problem problem_, int numAnts_, int numIterations_, boolean elitist_,
		double alpha_, double beta_, double phi_, double rho_) {
		problem = problem_;
		numAnts = numAnts_;
		numIterations = numIterations_;
		numCities = problem.cities.numCities();

		alpha = alpha_;
		beta = beta_;
		phi = phi_;
		rho = rho_;

		if (elitist_) {
			elitist = true;
		} else {
			elitist = false;
		}

		initializeAnts();
	}

	public void printBestTour() {
		for (int i = 0; i < bestTour.size(); i++) {
			System.out.print(bestTour.get(i) + " ");
		}
		System.out.println("");
		System.out.println("Length: " + bestTourLength);
	}


	private void initializeAnts() {
		for (int i = 0; i < numAnts; i++) {
			Ant temp = new Ant();
			ants.add(temp);
		}
	}

	// runs tour for each ant
	private double createAntTour(Ant ant) {
		// generate random start city for ant
		City currCity = problem.cities.getCity(rand.nextInt(numCities));
		ant.startCity(currCity);

		// move ant to a new city numCities - 1 times
		for (int i = 1; i < numCities; i++) {
			ArrayList<Edge> availableEdges = new ArrayList<Edge>();
			availableEdges.addAll(problem.getCityEdges(currCity));

			// tempEdge = edge the ant chooses to move on
			Edge tempEdge = ant.moveToNext(availableEdges);
			
			if (!elitist) {
				// wear away pheromone if ACS
				double amount = (1 - WEARING_SIGMA) * tempEdge.getPheromoneLevel() + WEARING_SIGMA * WEARING_TAUNOT;
				tempEdge.updatePheromoneLevel(amount);
			}
			currCity = ant.getCurrentCity();
		}

		return ant.tour.getLength();
	}

	private void placePheromone(Ant ant) {
		ArrayList<City> path = new ArrayList<City>();
		path.addAll(ant.tour.getTour());
		double value = beta / ant.tour.getLength(); // BETA HERE***
		
		for (int j = 1; j < path.size(); j++) { //assumes >1 city
			problem.updatePheromone(path.get(j-1), path.get(j), value);
		}
	}

	private void retraceAntTour() {
		for (int i = 0; i < numAnts; i++) {
			placePheromone(ants.get(i));
		}
	}

	public void run() {
		int iterationCounter = 0;

		// run as many times as we have iterations
		do {
			double iterationBestLength = Double.MAX_VALUE;
			int iterationBest = -1;

			// run tours for each ant
			for (int j = 0; j < numAnts; j++) {

				double currLength = createAntTour(ants.get(j));

				if (currLength < bestTourLength) {
					iterationBestLength = currLength;
					iterationBest = j;
				}
			}

			// first evaporate pheromones
			problem.evaporatePheromone(rho); // rho HERE***

			// if elitist, put down pheromone for each ant
			if (elitist) { retraceAntTour(); }

			// put pheromone down on best path
			placePheromone(ants.get(iterationBest));

			if (iterationBestLength < bestTourLength) {
				bestTourLength = iterationBestLength;
				bestTour.clear();
				bestTour.addAll(ants.get(iterationBest).tour.getTour());
			}

			iterationCounter++;
		} while (iterationCounter < numIterations);
	}
}
