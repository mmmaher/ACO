/*
 * ACO Solver 
 * Solves a inputed TSP problem using ACO
 *
 * NIC - Professor Majercik
 * Max Bucci, Megan Maher, Nikki Morin
 * Created: 3/12/2015
 * Last Modified: 3/12/2015
 *
 */


import java.io.*;
import java.util.*;


public class ACO {
	
	// Problem + Runner class
	private static Problem prob;
	private static ACORunner runner;

	//File Variables
	private static File file;
	
	// ACO Parameters
	private static int numAnts;
	private static int numTours;      // number of iterations
	private static double alpha;      // pheromone trail importance
	private static double beta;       // distance between cities importance
	private static double phi;        // local pheromone update
	private static double rho;        // global pheromone update
	private static String elitism;    // elitism or ACS
	
	// Elitist exclusive variables
	private static int eliteAnts;     // number of ants in elitims

	// ACS exclusive variables
	private static double tau;        // wearing away factor
	private static double epsilon;    // wearing away factor  
	private static double qnot;       // probability of choosing best edge

	//Main method
	public static void main(String[] args) {
		
		file = new File(args[0]);
		elitism = args[1];
		numAnts = Integer.parseInt(args[2]);
		numTours = Integer.parseInt(args[3]);
		alpha = Double.parseDouble(args[4]);
		beta = Double.parseDouble(args[5]);
		rho = Double.parseDouble(args[6]);
	
		boolean elite = false;

		if (elitism.equals("el")) {
			eliteAnts = Integer.parseInt(args[7]);
			elite = true;
			epsilon = 0;
			tau = 0;
			qnot = 0;
				
		} else if (elitism.equals("ne")) {
			epsilon = Double.parseDouble(args[7]);
			tau = Double.parseDouble(args[8]);
			qnot = Double.parseDouble(args[9]);
			eliteAnts = 0;

		} else {
			System.out.println("Invalid input");
			System.exit(0);
		}

		prob = new Problem(file);
		runner = new ACORunner(numAnts, numTours, alpha, beta, rho, epsilon, tau, qnot, eliteAnts, elite);
		runner.run(prob);
	}
}
