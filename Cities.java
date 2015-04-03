
import java.util.*;

public class Cities {

	private static Map<Integer,City> cities;
	
	public Cities() { this.cities = new HashMap<Integer, City>(); }
	
	
	public void addCity(int id, City newCity) { this.cities.put(id, newCity); }
	public City getCity(int id) { return this.cities.get(id); }
	public int numCities() { return this.cities.size(); }
	

	public void printACity(int id) { this.cities.get(id).printCity(); }

	public void printCities() {
		for (Map.Entry<Integer, City> cityPair : this.cities.entrySet()) {
			cityPair.getValue().printCity();
		}
	}
}
