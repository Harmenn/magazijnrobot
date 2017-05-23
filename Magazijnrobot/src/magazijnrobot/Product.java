package magazijnrobot;

import java.awt.Color;

public class Product {

	private int id;
	private String name;
	private int volume;
	private int x;
	private int y;
	private String status;
        private Color color;

	public Product(int id, String name, int volume, int x, int y, String status) {
		this.id = id;
		this.name = name;
		this.volume = volume;
		this.x = x;
		this.y = y;
		this.status = status;
	    double hue = Math.random();
	    int rgb;
	    rgb = Color.HSBtoRGB((float) hue, 0.8f, 0.8f);
	    this.color = new Color(rgb);
	}

    public Color getColor() {
        return color;
    }

	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public int getVolume() {
		return volume;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public String getStatus() {
		return status;
	}

	public String toString() {
		return "x: "+this.x + ", y: " + this.y;
	}
	
	public boolean equals(Object o){ 
		Product tmpO = (Product) o;
		if(this.id==tmpO.id) return true;
		return false;
	}

}
