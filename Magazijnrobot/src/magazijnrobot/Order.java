package magazijnrobot;

import java.util.ArrayList;

public class Order {
	ArrayList<Product> producten;
	

	public Order() {
		this.producten = new ArrayList();
		//testtt
	}
	
	public Order(ArrayList<Product> producten) {
		this.producten = producten;
	}
	
	public void addProduct(Product product) {
		producten.add(product);
	}
}
