import java.util.*;
import java.io.*;

public class Problem {
	
	private static List<Double[]> cities;
	private static int numCities;

	// file variable
	private static BufferedReader reader = null;

	public Problem() {
		cities = new ArrayList<Double[]>();
	}

	public static void addCity(Double[] city) {
		Double[] temp = {city[1], city[2]};
		cities.add(temp);
	}

	public static int getFitness() {
		return 0;
	}

	public static int getNumCities() {
		return numCities;
	}

	public static Double getX(int i) {
		return cities.get(i)[0];
	}

	public static Double getY(int i) {
		return cities.get(i)[1];
	}

	public static void printAll() {
		for (int i = 0; i < cities.size(); i++) {
			printCity(cities.get(i));
		}
	}

	private static void printCity(Double[] city) {
		System.out.println(city[0] + ", " + city[1]);
	}

	public static void readFile(File f) {
		
		try {
			reader = new BufferedReader(new FileReader(f));
			String line;
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
					continue;
				} 
				if (line.charAt(0) == 'N' || line.charAt(0) == 'C' ||
					line.charAt(0) == 'T' || line.charAt(0) == 'E') {
					continue;
				}
				
				Double[] temp = new Double[3];
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
