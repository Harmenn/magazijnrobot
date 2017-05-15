package tsp_simulator;

public class Coordinate {
	public int x;
	public int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Object o) {
		Coordinate c = (Coordinate) o;
		if (this.x == c.x && this.y == c.y) {
			return true;
		} else {
			return false;
		}

	}

	public String toString() {
		return "x: " + this.x + ", y: " + this.y;
	}

	public float distanceBetween(Coordinate c) {
		int x1 = this.x;
		int x2 = c.x;
		int y1 = this.y;
		int y2 = c.y;
		//Pythagoras
		float distance = (float) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		//System.out.println("Distance between "+this+" and "+c+" is "+distance);
		return distance;
	}
}
