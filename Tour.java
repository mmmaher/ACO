import java.util.*;

public class Tour {

	private ArrayList<City> tour;
	private double length;

	public Tour() {
		this.tour = new ArrayList<City>();
	}

	public Tour(ArrayList<City> tour) {
		resetTour();
		if (!tour.isEmpty()) this.tour = tour;
	}

	public void addCityToTour(City city, double len) {
		this.tour.add(city);
		this.length += len;
	}

	public void resetTour() {
		this.tour.clear();
		this.length = 0.0;
	}

	public double getLength() { return this.length; }

	public boolean cityAlreadyInTour(City city) { return this.tour.contains(city); }

	public ArrayList<City> getTour() { return this.tour; }

	public void printTour() {
		for (City c : this.tour) {
			System.out.print(c.getX() + ", " + c.getY());
		}
		System.out.println();
		System.out.println("Length: " + this.length);
	}
}
