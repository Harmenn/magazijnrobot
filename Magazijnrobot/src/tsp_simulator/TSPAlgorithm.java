package tsp_simulator;

import java.util.ArrayList;

public abstract class TSPAlgorithm {
	private ArrayList<Coordinate> coords;

	public abstract ArrayList<Coordinate> getSortedList();
}
