package tsp_simulator;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TSPOptTwo extends TSPAlgorithm {
	int dirtyFix = 0;
	ArrayList<Coordinate> coords;

	public TSPOptTwo(ArrayList<Coordinate> coords) {
		this.coords = coords;
		for (Coordinate c : this.coords) {
			////System.out.println(c);
		}
	}

	public ArrayList<Coordinate> getSortedList() {
		while (fixIntersections()) {
		}
		;
		return coords;
	}

	private boolean fixIntersections() {
		// Loop door de coordinaten heen
		for (int i = 0; i < coords.size(); i++) {
			int cor_1_i;
			int cor_2_i;
			int cor_3_i;
			int cor_4_i;

			if (i >= coords.size()) {
				cor_1_i = 0;
			} else {
				cor_1_i = i;
			}

			if (i + 1 >= coords.size()) {
				cor_2_i = 0;
			} else {
				cor_2_i = i + 1;
			}

			for (int j = 0; j < coords.size(); j++) {

				//System.out.println("____");
				if (j + 2 >= coords.size()) {
					cor_3_i = j + 2 - coords.size();
				} else {
					cor_3_i = j + 2;
				}

				if (j + 3 >= coords.size()) {
					cor_4_i = j + 3 - coords.size();
				} else {
					cor_4_i = j + 3;
				}

				//System.out.println("Iteration i: " + i);
				//System.out.println("Iteration j: " + j);
				//System.out.println("cor_1_i: " + cor_1_i);
				//System.out.println("cor_2_i: " + cor_2_i);
				//System.out.println("cor_3_i: " + cor_3_i);
				//System.out.println("cor_4_i: " + cor_4_i);
				int count = 0;
				if (cor_1_i == cor_2_i)
					count++;
				if (cor_1_i == cor_3_i)
					count++;
				if (cor_1_i == cor_4_i)
					count++;

				if (cor_2_i == cor_3_i)
					count++;
				if (cor_2_i == cor_4_i)
					count++;

				if (cor_3_i == cor_4_i)
					count++;

				//System.out.println(count + " duplicate points");
				if (count == 1)
					continue;
				if (count == 2)
					continue;

				Coordinate cor_1 = coords.get(cor_1_i);
				Coordinate cor_2 = coords.get(cor_2_i);
				Coordinate cor_3 = coords.get(cor_3_i);
				Coordinate cor_4 = coords.get(cor_4_i);

				//Alle coordinaten *100 en er 10 van af trekken zodat de uiteindes elkaar niet snijden
				int tmpX_1 = cor_1.x * 100;
				int tmpY_1 = cor_1.y * 100;
				int tmpX_2 = cor_2.x * 100;
				int tmpY_2 = cor_2.y * 100;

				int tmpX_3 = cor_3.x * 100;
				int tmpY_3 = cor_3.y * 100;
				int tmpX_4 = cor_4.x * 100;
				int tmpY_4 = cor_4.y * 100;

				if (tmpX_1 < tmpX_2) {
					tmpX_1 += 10;
					tmpX_2 -= 10;
				} else {
					tmpX_1 -= 10;
					tmpX_2 += 10;
				}

				if (tmpY_1 < tmpY_2) {
					tmpY_1 += 10;
					tmpY_2 -= 10;
				} else {
					tmpY_1 -= 10;
					tmpY_2 += 10;
				}

				if (tmpX_3 < tmpX_4) {
					tmpX_3 += 10;
					tmpX_4 -= 10;
				} else {
					tmpX_3 -= 10;
					tmpX_4 += 10;
				}

				if (tmpY_3 < tmpY_4) {
					tmpY_3 += 10;
					tmpY_4 -= 10;
				} else {
					tmpY_3 -= 10;
					tmpY_4 += 10;
				}

				//Controleer of de lijnen elkaar snijden
				if (Line2D.linesIntersect(cor_1.x, cor_1.y, cor_2.x, cor_2.y, cor_3.x, cor_3.y, cor_4.x, cor_4.y)) {
					//System.out.println("Found intersection (" + (cor_1_i + 1) + ", " + (cor_2_i + 1) + ")-("+ (cor_3_i + 1) + ", " + (cor_4_i + 1) + ")");
					//System.out.println("Found intersection (" + cor_1.x + ", " + cor_1.y + ")-(" + cor_2.x + ", "+ cor_2.y + ") (" + cor_3.x + ", " + cor_3.y + ")-(" + cor_4.x + ", " + cor_4.y + ")");
					//System.out.println("Found intersection (" + tmpX_1 + ", " + tmpY_1 + ")-(" + tmpX_2 + ", " + tmpY_2+ ") (" + tmpX_3 + ", " + tmpY_3 + ")-(" + tmpX_4 + ", " + tmpY_4 + ")");
					Collections.swap(coords, cor_2_i, cor_3_i);

					fixIntersections();
					return true;
				}
				//System.out.println("No intersections found");

			}
		}
		return false;
	}
}
