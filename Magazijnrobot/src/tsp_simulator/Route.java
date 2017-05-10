package tsp_simulator;

import java.util.ArrayList;

public class Route {
	private ArrayList<Coordinate> coords;

	public Route(ArrayList<Coordinate> coords) {
		this.coords = coords;
	}

	public int getSteps() {
		int steps = 0;
		for (int i = 0; i < coords.size() - 1; i++) {

			int i1 = i;
			int i2 = i - 1;
			if (i2 == -1)
				i2 = coords.size() - 1;
			if (coords.get(i1).x > coords.get(i2).x) {
				steps += coords.get(i1).x - coords.get(i2).x;
			} else {
				steps += coords.get(i2).x - coords.get(i1).x;
			}

			if (coords.get(i1).y > coords.get(i2).y) {
				steps += coords.get(i1).y - coords.get(i2).y;
			} else {
				steps += coords.get(i2).y - coords.get(i1).y;
			}
		}

		return steps;
	}

	public ArrayList<Coordinate> getRouteAsArray() {
		return coords;
	}
}
