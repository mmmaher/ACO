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
	
	private static Problem prob;

	private static int numCities;


	//File Variables
	private static File file;

	//Main method
	public static void main(String[] args) {
		
		file = new File(args[0]);
		
		// individuals = Integer.parseInt(args[1]);
		// iterations = Integer.parseInt(args[6]);
		// algorithm = args[7];
		
		prob = new Problem();

		prob.readFile(file);
		prob.printAll();
	}
}
