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
	
	//ACO runner instance
	//private static ACORunner runner;

	// ACO Parameters
	private static int numAnts;
	private static int numTours; // number of iterations
	private static double alpha; // pheromone trail importance
	private static double beta; // distance between cities importance
	private static double phi; // local pheromone update
	private static double rho; // global pheromone update
	private static double qo; // probability in ACS that ant will choose best edge
	private static boolean elitism;

	//Main method
	public static void main(String[] args) {
		
		file = new File(args[0]);
		numAnts = Integer.parseInt(args[1]);
		alpha = Double.parseDouble(args[2]);
		beta = Double.parseDouble(args[3]);
		phi = Double.parseDouble(args[4]);
		rho = Double.parseDouble(args[5]);

		// String elite = args([7]);
		
		// if (elite.equals("el")) {
		// 	elitism = true;
		// } else if (elite.equals("ne")) {
		// 	elitism = false;
		// } else {
		// 	System.out.println("Invalid input");
		// 	System.exit(0);
		// }
		
		prob = new Problem(file);
		// runner = new ACORunner(.......);
		
	}
}
