package bpp_simulator;

import java.util.ArrayList;

public class Bin {

	private int CurrentSize = 0;
	private int MaxSize;
	private ArrayList<Product> Products = new ArrayList<>();

	public Bin() {
	}

	public int getMaxSize() {
		return MaxSize;
	}

	public ArrayList<Product> getProducts() {
		return Products;
	}

	public Bin(Product Product, int MaxGrootte) {
		Products.add(Product);
		this.MaxSize = MaxGrootte;
		CurrentSize += Product.getLength();
	}

	public Bin(Product Product) {
		Products.add(Product);
	}

	public int getVolume() {
		int volume = 0;
		for (Product product : Products) {
			volume += product.getLength();
		}
		return volume;
	}

	public Bin(int MaxGrootte) {
		this.MaxSize = MaxGrootte;
	}

	public void addProduct(Product Product) {
		Products.add(Product);
		CurrentSize += Product.getLength();
	}

	public boolean addProduct(Product Product, boolean Check) {
		if (Check) {
			if (CurrentSize + Product.getLength() <= MaxSize) {
				addProduct(Product);
				return true; // past wel
			} else {
				return false; // past niet
			}
		} else {
			return false;
		}
	}

	public void removeProduct(Product Product) {
		CurrentSize -= Product.getLength();
		Products.remove(Product);
	}

	public int getCurrentSize() {
		return CurrentSize;
	}

	public void setCurrentSize(int CurrentSize) {
		this.CurrentSize = CurrentSize;
	}

	public void addHuidigeGrootte(int HuidigeGrootte) {
		this.CurrentSize = Integer.sum(this.CurrentSize, HuidigeGrootte);
	}

	public int getProductAmount() {
		return Products.size();
	}

	public Bin deepCopy() {
		Bin copy = new Bin();
		copy.Products = new ArrayList<>(Products);
		copy.CurrentSize = CurrentSize;
		copy.MaxSize = MaxSize;
		return copy;
	}

}
