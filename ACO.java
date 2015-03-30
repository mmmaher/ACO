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

	// ACO Parameters
	private static int numAnts;
	private static int numTours; // number of iterations
	private static double alpha; // pheromone trail importance
	private static double beta; // distance between cities importance
	private static double phi; // local pheromone update
	private static double rho; // global pheromone update
	private static double elitism;

	//Main method
	public static void main(String[] args) {
		
		file = new File(args[0]);
		numAnts = Integer.parseInt(args[1]);
		alpha = Double.parseDouble(args[2]);
		beta = Double.parseDouble(args[3]);
		

		prob = new Problem();
		prob.readFile(file);
		prob.printAll();
		
		
		
	}
}
