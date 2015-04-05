import java.util.*;

public class Tour {

	private ArrayList<City> tour;
	private double length;

	public Tour() {
		this.tour = new ArrayList<City>();
		this.length = Double.MAX_VALUE;
	}

	public Tour(ArrayList<City> tour, double length) {
		resetTour();
		this.tour = tour;
		this.length = length;
	}

	public void addCityToTour(City city, double len) {
		this.tour.add(city);
		this.length += len;
	}

	public void resetTour() {
		if (this.tour != null) {
			this.tour.clear();
			this.length = 0.0;
		}
	}

	public double getLength() { return this.length; }

	public boolean cityAlreadyInTour(City city) { return this.tour.contains(city); }

	public ArrayList<City> getTour() { return this.tour; }

	public void printTour() {
		City lastCity = null;
		for (City c : this.tour) {
			if (lastCity != null) {
				Edge temp = new Edge(lastCity, c);
				//temp.printEdge();
			}
			c.printCity();
			lastCity = c;
		}
		System.out.println();
		System.out.format("Length: %.2f\n", this.length);
	}
}
