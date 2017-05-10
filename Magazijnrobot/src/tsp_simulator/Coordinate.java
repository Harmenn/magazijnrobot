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

	public int distanceBetween(Coordinate c) {
		int distanceX;
		int distanceY;
		if (this.x > c.x) {
			distanceX = this.x - c.x;
		} else {
			distanceX = c.x - this.x;
		}

		if (this.y > c.y) {
			distanceY = this.y - c.y;
		} else {
			distanceY = c.y - this.y;
		}
		return distanceX + distanceY;
	}
}
