package tsp_simulator;

import java.util.ArrayList;
import java.util.Collections;

public class TSPNearestNeighbour extends TSPAlgorithm {
	ArrayList<Coordinate> coords;
	ArrayList<Coordinate> sortedCoords = new ArrayList<Coordinate>();

	public TSPNearestNeighbour(ArrayList<Coordinate> coords) {
		this.coords = coords;
		for (Coordinate c : this.coords) {
			System.out.println(c);
		}
	}

	public ArrayList<Coordinate> getSortedList() {

		// find most left point
		Coordinate mostLeft = null;
		for (Coordinate c : coords) {
			if (mostLeft == null) {
				mostLeft = c;
				continue;
			}
			if (c.x < mostLeft.x) {
				mostLeft = c;
			}
		}
		sortedCoords.add(mostLeft);
		coords.remove(mostLeft);
		int startSize = coords.size();
		for(int i = 0; i<startSize; i++){
			//1 voor 1 het dichtsbijzijnde coordinaat vinden
			Coordinate nearestCoord = findNearestCoord(sortedCoords.get(sortedCoords.size() - 1), coords);
			coords.remove(nearestCoord);
			sortedCoords.add(nearestCoord);
		}
		return sortedCoords;
	}

	public Coordinate findNearestCoord(Coordinate coord, ArrayList<Coordinate> coords) {
		System.out.println("FindCoord for "+coord);
		// add next coords
		Coordinate nearestCoord = null;
		Coordinate lastCoord = sortedCoords.get(sortedCoords.size() - 1);
		System.out.println("last coord was "+lastCoord);
		// Zoek de kleinste coordinaat op
		for (Coordinate c : coords) {
			if (nearestCoord == null) {
				nearestCoord = c;
				continue;
			}
			System.out.println("Current smallest is "+nearestCoord+" with distance "+nearestCoord.distanceBetween(lastCoord));
			System.out.println("Checking for "+c);
			
			if (c.distanceBetween(lastCoord) < nearestCoord.distanceBetween(lastCoord)) {
				//De afstand is kleiner
				System.out.println("New smallest: "+c.distanceBetween(lastCoord) + " < " + c.distanceBetween(nearestCoord));
				nearestCoord = c;
			} else {
				//De afstand is groter
				System.out.println("New smallest: "+c.distanceBetween(lastCoord) + " > " + c.distanceBetween(nearestCoord));
			}
		}
		System.out.println("Found "+nearestCoord);
			
		return nearestCoord;
	}
}
