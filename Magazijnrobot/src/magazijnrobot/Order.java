package magazijnrobot;

import java.util.ArrayList;

public class Order {
	ArrayList<Product> producten;
	private int id;
	
	public Order() {
		this.producten = new ArrayList();
	}

	public Order(int id, ArrayList<Product> producten) {
		this.producten = producten;
		this.id=id;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void addProduct(Product product) {
		producten.add(product);
	}

	public ArrayList<Product> getProducten() {
		return producten;
	}
	
	public void clear() {
		producten.clear();
	}
}
