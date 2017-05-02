package magazijnrobot;

import java.util.ArrayList;

public class Order {
	ArrayList<Product> producten;
	

	public Order() {
		this.producten = new ArrayList();
	}
	
	public Order(ArrayList<Product> producten) {
		this.producten = producten;
	}
	
	public void addProduct(Product product) {
		producten.add(product);
	}
	
	public ArrayList<Product> getProducten() {
		/*
		//Object[][] data = {{},{},{}};
		int c = 0;
		
		Object[][] data = {
		    {"1", "Lego doos",
		     "€5,00", new Integer(5), "Opgehaalt"},
		    {"2", "Lego tas",
		     "€3,00", new Integer(3), "Kwijt geraakt"}
		};
		
		for(Product i : producten) {
			data[c][0] = i.getId();
			data[c][1] = i.getOmschrijving();
			data[c][2] = i.getPrijs();
			data[c][3] = i.getGrootte();
			data[c][4] = i.getStatus();
			c++;
		}
		return data;*/
		return producten;
	}
}
