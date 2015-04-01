import java.io.*;
import java.util.*;

public class Ant {

	public Tour tour;
	private City currCity;

	private static Random rand = new Random();

	public Ant() {
		tour = new Tour();

	}

	public City startCity(City city) {
		currCity = city;
		tour.resetTour();
		tour.addCityToTour(city, 0.0);
	}

	public City getCurrentCity() {
		return currCity;
	}

	private City getOtherCity(Edge edge) {
		City[] citiesTemp = edge.getEndCities();
		if (citiesTemp[0] == currCity) { return citiesTemp[1]; }
		else { return citiesTemp[0]; }
	}

	public Edge moveToNext(ArrayList<Edge> edges) {
		ArrayList<Double> probabilities = new ArrayList<Double>();
		double sumPherLen = 0.;
		double sumProbs = 0.;
		City city;

		// first calculate probabilities of choosing each possible next city
		for (Edge edge : edges) {
			city = getOtherCity(edge);

			if (tour.cityAlreadyInTour(city)) continue;

			probabilities.add(edge.getPheromoneLevel() * edge.getLength());
			sumPherLen+=probabilities.get(probabilities.size()-1);
		}

		// then update probabilities to divide by sumPherLen
		for (int i = 0; i < probabilities.size(); i++) {
			sumProbs += probabilities.get(i) / sumPherLen;
			probabilities.set(i, sumProbs);
		}

		// choose new path "randomly", weighted with probs just assigned
		double value = rand.nextDouble() * sumProbs;
		Edge chosenEdge = edges.get(chooseSpecified(value, probabilities));

		currCity = getOtherCity(chosenEdge);
		tour.addCityToTour(currCity, chosenEdge.getLength());

		return (chosenEdge);
	}

	private int chooseSpecified(double num, ArrayList<Double> a) {
		// sees where specified num falls in the array
		for (int i = 0; i < a.size(); i++) {
			if (num < a.get(i)) {
				return i;
			}
		}
		System.out.println("ERROR HERE BAD, looking for "+num);
		return 0;
	}
}
