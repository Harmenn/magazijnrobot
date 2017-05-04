package tsp_simulator;

import java.util.ArrayList;

public class Route {
	private ArrayList<Coordinate> coords;
	public Route(ArrayList<Coordinate> coords) {
		this.coords = coords;
	}
	
	public int getSteps() {
		int steps = 0;
		for(int i = 1; i < coords.size()-1; i++) {
			if(coords.get(i).x > coords.get(i-1).x) {
				steps+= coords.get(i).x - coords.get(i-1).x;
			} else {
				steps+= coords.get(i-1).x - coords.get(i).x;
			}
			

			if(coords.get(i).y > coords.get(i-1).y) {
				steps+= coords.get(i).y - coords.get(i-1).y;
			} else {
				steps+= coords.get(i-1).y - coords.get(i).y;
			}
		}
		return steps;
	}
	
	public ArrayList<Coordinate> getRouteAsArray() {
		return coords;
	}
}
