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
	private static int numIterations;
	private static double alpha;
	private static double beta;
	private static double pher;
	private static double elitism;

	//Main method
	public static void main(String[] args) {
		
		file = new File(args[0]);

		prob = new Problem();
		prob.readFile(file);
		prob.printAll();
	}
}
