package tsp_simulator;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TSPOptTwo2 extends TSPAlgorithm {
	int dirtyFix = 0;
	ArrayList<Coordinate> coords;
	public TSPOptTwo2(ArrayList<Coordinate> coords) {
		this.coords = coords;
		for(Coordinate c : this.coords) {
			System.out.println(c);
		}
	}
	
	public ArrayList<Coordinate> getSortedList() {
		fixIntersections();
		return coords;
	}
	
	private static ArrayList<Integer> lastFixes = new ArrayList<Integer>();
	private static boolean alreadyFixed = false;
	private boolean fixIntersections() {
	    long startTime = System.currentTimeMillis();
	    //while (true) {
	        for (int i = 0; i < coords.size()-1; i++) {
	            for (int k = 0; k < coords.size()-1; k++) {
	                //if (System.currentTimeMillis() - startTime >= 5000) {
	                //    return false;
	                //}
	                Coordinate a = coords.get(i);
					Coordinate b = coords.get((i+1) % (coords.size()-1));
					Coordinate c = coords.get(k);
					Coordinate d = coords.get((k+1) % (coords.size()-1));
	                double distanceab = a.distanceBetween(b);
	                double distancecd = c.distanceBetween(d);
	                double distanceac = a.distanceBetween(c);
	                double distancebd = b.distanceBetween(d);
	                double change = (distanceab + distancecd) - 
	                    (distanceac + distancebd);
	                if (change > 0) {
	                    coords.set(k,a);
	                    coords.set(i,c);
	                    int half = i + ((k + 1) - i) / 2;
						int endCount = k;
						for (int startCount = i; startCount < half; startCount++) {
						    Coordinate store = coords.get(startCount);
						    coords.set(startCount, coords.get(endCount));
						    coords.set(endCount, store);
						    endCount--;
						}
	                }
	            }
	        }
	    //}
	        return false;
	}
	
	private boolean checkDuplicateCoordinates(ArrayList<Coordinate> coordList) { 
	    Set<Integer> seenValues = new HashSet();
		for(Coordinate c : coordList) {
	        if(seenValues.contains(c.x)) {
	        	System.out.println(seenValues);
	            return true;
	        }
	        else{
	            seenValues.add(c.x);
	        }
	        

	        if(seenValues.contains(c.y)) {
	        	System.out.println(seenValues);
	            return true;
	        }
	        else{
	            seenValues.add(c.y);
	        }
		}
	    return false;
	}
}
