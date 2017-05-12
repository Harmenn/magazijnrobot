package magazijnrobot;

public class Product {

	private int id;
	private String name;
	private int volume;
	private int x;
	private int y;
	private String status;

	public Product(int id, String name, int volume, int x, int y, String status) {
		this.id = id;
		this.name = name;
		this.volume = volume;
		this.x = x;
		this.y = y;
		this.status = status;
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
		// return new Object({});
		return "";
	}

}
