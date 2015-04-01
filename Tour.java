import java.util.*;

public class Tour {

	private ArrayList<City> tour = new ArrayList<City>();
	private double length;

	public Tour() {
		resetTour();
	}

	public void addCityToTour(City city, double len) {
		tour.add(city);
		length += len;
	}

	public void resetTour() {
		tour.clear();
		length = 0.0;
	}

	public double getLength() {
		return length;
	}

	public boolean cityAlreadyInTour(City city) {
		return tour.contains(city);
	}

	public ArrayList<City> getTour() {
		return tour;
	}
}
