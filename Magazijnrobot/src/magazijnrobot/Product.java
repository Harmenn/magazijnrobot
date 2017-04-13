package magazijnrobot;

public class Product {

	private int id;
	private String omschrijving;
	private float prijs;
	private int grootte;
	private String status;
	public Product(int id, String omschrijving, float prijs, int grootte, String status){
		this.id = id;
		this.omschrijving = omschrijving;
		this.prijs = prijs;
		this.grootte = grootte;
		this.status = status;
	}


}
