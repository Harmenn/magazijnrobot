package tsp_simulator;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;

public class TSPOwnAlgorithm extends TSPAlgorithm {
	ArrayList<Coordinate> coords;
	public TSPOwnAlgorithm(ArrayList<Coordinate> coords) {
		this.coords = coords;
		for(Coordinate c : this.coords) {
			System.out.println(c);
		}
	}
	
	public ArrayList<Coordinate> getSortedList() {
		coords = new TSPNearestNeighbour(coords).getSortedList();
		coords = new TSPOptTwo(coords).getSortedList();
		return coords;
	}
}
