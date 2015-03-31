
import java.util.*;

public class Cities {

	private static Map<Integer,City> cities;
	
	
	public Cities() { this.cities = new HashMap<Integer, City>(); }
	
	
	public void addCity(int id, City newCity) { this.cities.put(id, newCity); }
	public City getCity(int id) { return this.cities.get(id); }
	public int numCities() { return this.cities.size(); }
	
	public void printCities() {
		for (int i = 0; i < this.cities.size(); i++) {
			printCity(i);
		}
	}
	
	
	private void printCity(int id) {
		City temp = this.cities.get(id);
		System.out.println(temp.getX() + ", " + temp.getY());
	}
}
