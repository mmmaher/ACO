import java.util.*;
import java.io.*;

public class Problem {
	
	private static double[][] cities;
	private static int numCities;

	// file variable
	private static BufferedReader reader = null;

	public Problem() {
		// cities = new ArrayList<Double[]>();
	}

	public static void addCity(int i, double[] city) {
		double[] temp = {city[1], city[2]};
		cities[i] = temp;
	}

	public static int getFitness() { return 0; }

	public static double[] getCity(int i) { return cities[i-1]; }

	public static int getNumCities() { return numCities; }

	public static double getX(int i) { return cities[i-1][0]; }

	public static double getY(int i) { return cities[i-1][1]; }

	public static void printAll() {
		for (int i = 0; i < numCities; i++) {
			printCity(cities[i]);
		}
	}

	private static void printCity(double[] city) {
		System.out.println(city[0] + ", " + city[1]);
	}

	public static void readFile(File f) {
		
		try {
			reader = new BufferedReader(new FileReader(f));
			String line;
			int counter = 0;
			while ((line = reader.readLine()) != null) {
				if (line.charAt(0) == 'D') {
					String[] splitStr = line.split(" ");
					int numIndex = 0;
					for (int i = 0; i < splitStr.length; i++) {
						if (splitStr[i].contains(":")) {
							numIndex = i;
						}
					}
					numCities = Integer.parseInt(splitStr[numIndex+1]);
					cities = new double[numCities][3];
					continue;
				} 
				if (line.charAt(0) == 'N' || line.charAt(0) == 'C' ||
					line.charAt(0) == 'T' || line.charAt(0) == 'E') {
					continue;
				}
				
				// Double[] temp = new Double[3];
				double[] temp = new double[3];
				int index = 0;
				boolean started = false;
				String num = "";
				for (int i = 0; i < line.length(); i++) {
					String letter = line.substring(i, i+1);
					if (letter.equals(" ") && !started) {
						continue;
					}
					if (letter.equals(" ")) {
						temp[index] = Double.parseDouble(num);
						num = "";
						index++;
						started = false;
						continue;
					}
					num = num + letter;
					started = true;
				}
				temp[index] = Double.parseDouble(num);
				addCity(counter, temp);
				counter++;

			}
			reader.close();
			
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", f);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}
}
