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
	  if(this.x==c.x && this.y==c.y){ 
		  return true;
	  } else {
		  return false;
	  }
	  
  }
  
  public String toString() {
	  return "x: " + this.x + ", y: " + this.y;
  }
}
