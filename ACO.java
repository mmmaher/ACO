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
	
	// Problem class
	private static Problem prob;

	//File Variables
	private static File file;
	
	//ACO runner instance
	//private static ACORunner runner;

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
		alpha = Double.parseDouble(args[3]);
		beta = Double.parseDouble(args[4]);
		rho = Double.parseDouble(args[5]);
	
		if (elitism.equals("el") {
			eliteAnts = Intger.parseInt(args[6]);
				
		} else if (elitism.equals("ne") {
			epsilon = Double.parseDouble(args[6]);
			tau = Double.parseDouble(args[7]);
			qnot = Double.parseDouble(args[8]);

		} else {
			System.out.println("Invalid input");
			System.exit(0);
		}
		
		prob = new Problem(file);
		prob.printGraph();
	}
}
