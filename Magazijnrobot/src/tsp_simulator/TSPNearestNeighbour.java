package tsp_simulator;

import java.util.ArrayList;

public class TSPNearestNeighbour extends TSPAlgorithm {
	ArrayList<Coordinate> coords;
	ArrayList<Coordinate> sortedCoords = new ArrayList<Coordinate>();
	public TSPNearestNeighbour(ArrayList<Coordinate> coords) {
		this.coords = coords;
		for(Coordinate c : this.coords) {
			System.out.println(c);
		}
	}

	public ArrayList<Coordinate> getSortedList() {
		
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
		Coordinate lastCoord = sortedCoords.get(sortedCoords.size()-1);
		for(Coordinate c : coords) {
			if(nearestCoord==null) {
				nearestCoord = c;
				continue;
			}
			/*int biggestX = coords.get(0).x - coord.x;
			int biggestY = coords.get(0).y - coord.y;
			
			
			
			if((c.x - coords.get(coords.size()-1).x) + ((c.y - coords.get(coords.size()-1).y)) < biggestX + biggestY) {
				nearestCoord = c;
			}*/
			
			int distanceCurX;
			int distanceCurY;
			if(nearestCoord.x > lastCoord.x) {
				distanceCurX = nearestCoord.x - lastCoord.x;
			} else {
				distanceCurX = lastCoord.x - nearestCoord.x;
			}

			if(nearestCoord.y > lastCoord.y) {
				distanceCurY = nearestCoord.y - lastCoord.y;
			} else {
				distanceCurY = lastCoord.y - nearestCoord.y;
			}

			System.out.println("Current coordinate is: "+nearestCoord);
			System.out.println("Last coordinate is: "+lastCoord);
			System.out.println("Current distance is: "+(distanceCurY+distanceCurX));
			
			int distanceThisX;
			int distanceThisY;
			if(nearestCoord.x > c.x) {
				distanceThisX = nearestCoord.x - c.x;
			} else {
				distanceThisX = c.x - nearestCoord.x;
			}

			if(nearestCoord.y > c.y) {
				distanceThisY = nearestCoord.y - c.y;
			} else {
				distanceThisY = c.y - nearestCoord.y;
			}
			System.out.println("This distance is: "+(distanceThisX+distanceThisY));
			
			if(distanceThisX+distanceThisY < distanceCurY+distanceCurX){
				System.out.println((distanceThisX+distanceThisY) + " < " + (distanceCurY+distanceCurX));
				nearestCoord = c;
			}
			
		}
		System.out.println("Returned: "+nearestCoord.x + ", " + nearestCoord.y);
		System.out.println("______");
		return nearestCoord;
	}
}
