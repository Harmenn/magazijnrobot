package tsp_simulator;

import java.util.ArrayList;

public class TSPNearestNeighbour extends TSPAlgorithm {
	ArrayList<Coordinate> coords;
	public TSPNearestNeighbour(ArrayList<Coordinate> coords) {
		this.coords = coords;
		for(Coordinate c : this.coords) {
			System.out.println(c);
		}
	}

	public ArrayList<Coordinate> getSortedList() {
		ArrayList<Coordinate> sortedCoords = new ArrayList<Coordinate>();
		
		//find most left point
		Coordinate mostLeft = null;
		for(Coordinate c : coords) {
			if(mostLeft==null) {
				mostLeft = c;
				continue;
			}
			if(c.x < mostLeft.x) { 
				mostLeft = c;
			}
		}
		sortedCoords.add(mostLeft);
		coords.remove(mostLeft);
				
		while(coords.size() != 0) {
			Coordinate nearestCoord = findNearestCoord(sortedCoords.get(sortedCoords.size()-1), coords);
			coords.remove(nearestCoord);
			sortedCoords.add(nearestCoord);
		}		
		
		return sortedCoords;
	}
	public Coordinate findNearestCoord(Coordinate coord, ArrayList<Coordinate> coords) {

		//add next coords
		Coordinate nearestCoord = null;
		for(Coordinate c : coords) {
			if(nearestCoord==null) {
				nearestCoord = c;
				continue;
			}
			int biggestX = coords.get(0).x - coord.x;
			int biggestY = coords.get(0).y - coord.y;
			if((c.x - coords.get(coords.size()-1).x) + ((c.y - coords.get(coords.size()-1).y)) < biggestX + biggestY) {
				nearestCoord = c;
			}
		}
		return nearestCoord;
	}
}
