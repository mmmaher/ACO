import java.util.*;
import java.io.*;

public class Problem {
	
	private static List<int[]> cities;
	private static int numCities;

	// file variable
	private static BufferedReader reader = null;

	public Problem() {
		cities = new ArrayList<int[]>();
		int[] temp = {0,0};
		cities.add(temp); // add a random city at beginning
	}

	public static void addCity(int[] city) {
		int[] temp = {city[1], city[2]};
		cities.add(temp);
	}

	public static int getFitness() {
		return 0;
	}

	public static int getNumCities() {
		return numCities;
	}

	public static void printAll() {
		for (int i = 1; i < cities.size(); i++) {
			printCity(cities.get(i));
		}
	}

	private static void printCity(int[] city) {
		System.out.println(city[0] + ", " + city[1]);
	}

	public static void readFile(File f) {
		
		try {
			reader = new BufferedReader(new FileReader(f));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.charAt(0) == 'D') {
					String[] splitStr = line.split(" ");
					numCities = Integer.parseInt(splitStr[1]);
					continue;
				} 
				if (line.charAt(0) == 'N' || line.charAt(0) == 'C' ||
					line.charAt(0) == 'T' || line.charAt(0) == 'E') {
					continue;
				}
				
				int[] temp = new int[3];
				int index = 0;
				boolean started = false;
				String num = "";
				for (int i = 0; i < line.length(); i++) {
					String letter = line.substring(i, i+1);
					if (letter.equals(" ") && !started) {
						continue;
					}
					if (letter.equals(" ")) {
						temp[index] = Integer.parseInt(num);
						num = "";
						index++;
						started = false;
						continue;
					}
					num = num + letter;
					started = true;
				}
				temp[index] = Integer.parseInt(num);
				addCity(temp);
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