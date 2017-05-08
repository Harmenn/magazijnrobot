package tsp_simulator;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TSPOptTwo extends TSPAlgorithm {
	ArrayList<Coordinate> coords;
	public TSPOptTwo(ArrayList<Coordinate> coords) {
		this.coords = coords;
		for(Coordinate c : this.coords) {
			System.out.println(c);
		}
	}
	
	public ArrayList<Coordinate> getSortedList() {
		fixIntersections();
		
		return coords;
	}
	
	private boolean fixIntersections() {
		for(int i = 0; i < coords.size(); i++) {
			int cor_1_i;
			int cor_2_i;
			int cor_3_i;
			int cor_4_i;
			

			if(i>=coords.size()) {
				cor_1_i = 0;
			} else {
				cor_1_i = i;
			}
			
			if(i+1>=coords.size()) {
				cor_2_i = 0;
			} else {
				cor_2_i = i+1;
			}

			for(int j = cor_2_i+1; j < coords.size()-2;j++)
			{
				if(j+2>=coords.size()) {
					cor_3_i = 0;
				} else {
					cor_3_i = j+2;
				}
	
				if(j+3>=coords.size()) {
					cor_4_i = 0;
				} else {
					cor_4_i = j+3;
				}
				
				System.out.println("Iteration i: "+i);
				System.out.println("Iteration j: "+j);
				System.out.println("cor_1_i: "+cor_1_i);
				System.out.println("cor_2_i: "+cor_2_i);
				System.out.println("cor_3_i: "+cor_3_i);
				System.out.println("cor_4_i: "+cor_4_i);
				System.out.println("____");
				
				
				Coordinate cor_1 = coords.get(cor_1_i);
				Coordinate cor_2 = coords.get(cor_2_i);
				Coordinate cor_3 = coords.get(cor_3_i);
				Coordinate cor_4 = coords.get(cor_4_i);
				
				
				if(Line2D.linesIntersect(cor_1.x,cor_1.y, cor_2.x,cor_2.y, cor_3.x,cor_3.y,cor_4.x,cor_4.y)) {
					System.out.println("Found intersection ("+(cor_1_i+1)+", "+ (cor_2_i+1) +")-("+(cor_3_i+1)+", "+ (cor_4_i+1) +")");
					System.out.println("Found intersection ("+cor_1.x+", "+ cor_1.y +")-("+cor_2.x+", "+ cor_2.y +") ("+cor_3.x+", "+ cor_3.y +")-("+cor_4.x+", "+ cor_4.y +")");
					
					Collections.swap(coords, cor_2_i, cor_3_i);
					//fixIntersections();
					return true;
				}
				
			}
		}
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
