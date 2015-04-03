import java.io.*;
import java.util.*;

public class Ant {

	public Tour tour;
	private City currCity;

	private static Random rand = new Random();

	public Ant() {
		tour = new Tour();

	}

	public void startCity(City city) {
		currCity = city;
		tour.resetTour();
		tour.addCityToTour(city, 0.0);
	}

	public City getCurrentCity() { return currCity; }

	private City getOtherCity(Edge edge) {
		if (currCity == edge.startCity()) { return edge.endCity(); }
		else { return edge.startCity(); }
	}


	public Edge trivialMove(List<Edge> edges) {
		Edge chosenEdge = null;
		City city;
		double minLength = Double.MAX_VALUE;

		// first calculate probabilities of choosing each possible next city
		for (Edge edge : edges) {
			
			city = getOtherCity(edge);
			if (tour.cityAlreadyInTour(city)) continue;

			if (edge.getLength() < minLength) {
				chosenEdge = edge;
				minLength = edge.getLength();
			}
		}

		if (chosenEdge == null) return null;

		currCity = getOtherCity(chosenEdge);
		tour.addCityToTour(currCity, chosenEdge.getLength());

		return chosenEdge;
	}


	public Edge moveToNext(List<Edge> edges) {
		List<Double> probabilities = new ArrayList<Double>();
		List<Edge> availableEdges = new ArrayList<Edge>();
		double sumPherLen = 0;
		double sumProbs = 0;
		City city;

		// first calculate probabilities of choosing each possible next city
		for (Edge edge : edges) {
			city = getOtherCity(edge);

			if (tour.cityAlreadyInTour(city)) continue;

			probabilities.add(edge.getPheromoneLevel() * edge.getLength());
			availableEdges.add(edge);
			sumPherLen+=probabilities.get(probabilities.size()-1);
		}

		if (availableEdges.isEmpty()) return null;

		// then update probabilities to divide by sumPherLen
		for (int i = 0; i < probabilities.size(); i++) {
			sumProbs += probabilities.get(i) / sumPherLen;
			probabilities.set(i, sumProbs);
		}

		// choose new path "randomly", weighted with probs just assigned
		double value = rand.nextDouble() * sumProbs;
		int chosenIndex = chooseSpecified(value, probabilities);
		Edge chosenEdge = availableEdges.get(chosenIndex);

		currCity = getOtherCity(chosenEdge);
		tour.addCityToTour(currCity, chosenEdge.getLength());

		return (chosenEdge);
	}

	private int chooseSpecified(double num, List<Double> a) {
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
