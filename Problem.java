import java.util.*;
import java.io.*;

public class Problem {
	
	public static Cities cities;

	// file variable
	private static BufferedReader reader = null;

	public Problem() {
		this.cities = new Cities();
	}

	public static int getFitness() { return 0; }

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
					//numCities = Integer.parseInt(splitStr[numIndex+1]);
					continue;
				} 
				if (line.charAt(0) == 'N' || line.charAt(0) == 'C' ||
					line.charAt(0) == 'T' || line.charAt(0) == 'E') {
					continue;
				}
				
				int index = 0;
				double x = 0, y = 0;
				boolean started = false;
				String num = "";
				for (int i = 0; i < line.length(); i++) {
					String letter = line.substring(i, i+1);
					if (letter.equals(" ") && !started) {
						continue;
					}
					if (letter.equals(" ")) {
						x = Double.parseDouble(num);
						num = "";
						index++;
						started = false;
						continue;
					}
					num = num + letter;
					started = true;
				}
				y = Double.parseDouble(num);
				City newCity = new City(counter, x, y);
				cities.addCity(counter, newCity);
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
