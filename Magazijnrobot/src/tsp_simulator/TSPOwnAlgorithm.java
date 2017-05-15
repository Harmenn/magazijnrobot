package tsp_simulator;

import java.util.ArrayList;

public class TSPOwnAlgorithm extends TSPAlgorithm {
	ArrayList<Coordinate> coords;

	public TSPOwnAlgorithm(ArrayList<Coordinate> coords) {
		this.coords = coords;
		for (Coordinate c : this.coords) {
			System.out.println(c);
		}
	}

	public ArrayList<Coordinate> getSortedList() {
		//Eerst voeren we nearest neighbour uit
		coords = new TSPNearestNeighbour(coords).getSortedList();
		//Hier halen we de intersecties uit d.m.v. OptTwo
		coords = new TSPOptTwo(coords).getSortedList();
		//Deze Route returnen we
		return coords;
	}
}
