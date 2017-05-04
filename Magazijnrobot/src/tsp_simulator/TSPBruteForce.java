package tsp_simulator;

import java.util.ArrayList;

public class TSPBruteForce extends TSPAlgorithm{
	ArrayList<Coordinate> coords;
	ArrayList<Route> routes = new ArrayList<Route>();
	int fastestRoute;
	Coordinate mostLeft = null;
	public TSPBruteForce(ArrayList<Coordinate> coords) {
		this.coords = coords;
		for(Coordinate c : this.coords) {
			System.out.println(c);
		}
	}
	public static void main(String[] args) {
		ArrayList<Coordinate> lijstje = new ArrayList<Coordinate>();
		lijstje.add(new Coordinate(1,1));
		lijstje.add(new Coordinate(3,3));
		lijstje.add(new Coordinate(5,1));
		lijstje.add(new Coordinate(4,1));
		lijstje.add(new Coordinate(2,1));
		TSPBruteForce tsp = new TSPBruteForce(lijstje);
		for(Coordinate c : tsp.getSortedList()) {
			System.out.println(c);
		}
	}

	
	private ArrayList<Coordinate> bruteForce() {
		//find most left point
		for(Coordinate c : coords) {
			if(mostLeft==null) {
				mostLeft = c;
				continue;
			}
			if(c.x < mostLeft.x) { 
				mostLeft = c;
			}
		}
		coords.remove(mostLeft);
		
		getNextSteps(null, coords);
		
		int leastSteps=-1;
		ArrayList<Coordinate> selectedList = null;
		for(Route r : routes) {
			if(leastSteps==-1){
				leastSteps = r.getSteps();
			}
			if(leastSteps > r.getSteps()) {
				selectedList = r.getRouteAsArray();
				System.out.println("Shortest route is: "+r.getSteps());
			}
		}
		System.out.println("I found " + routes.size() + " different routes");
		return selectedList;
	}
	
	private void getNextSteps(ArrayList<Coordinate> currentCoords, ArrayList<Coordinate> leftCoords) {

		if(currentCoords==null)
		{
			currentCoords = new ArrayList<Coordinate>();
			currentCoords.add(mostLeft);
		}
		
		System.out.println("My current coords: "+currentCoords.size());
		System.out.println("My left coords: "+leftCoords.size());
		
		if(leftCoords.size()==0) {
			Route route = new Route(currentCoords);
			routes.add(route);
			System.out.println("Added Route");
		}
		else 
		{
			for(int i = 0; i < leftCoords.size(); i++) {
				ArrayList<Coordinate> newCurrentCoords = new ArrayList<Coordinate>();
				ArrayList<Coordinate> newLeftCoords = new ArrayList<Coordinate>();
				
				for(Coordinate c : currentCoords) {
					newCurrentCoords.add(c);
				}

				for(Coordinate c : leftCoords) {
					newLeftCoords.add(c);
				}
				
				System.out.println("THE OLD FUCKING ARRAY HAS: "+leftCoords.size());
				System.out.println("THE NEW FUCKING ARRAY HAS: "+newLeftCoords.size());
				
				newCurrentCoords.add(leftCoords.get(i));
				newLeftCoords.remove(i);
				System.out.println("Going into nested");
				getNextSteps(newCurrentCoords, newLeftCoords);
			}
		}
	}

	@Override
	public ArrayList<Coordinate> getSortedList() {
		return bruteForce();
	}
}
