package bpp_simulator.algoritmes;

import java.util.ArrayList;

import bpp_simulator.Bin;
import bpp_simulator.Product;

public class Firstfit extends Algoritme {

	private final ArrayList<Bin> bins = new ArrayList<>();

    public Firstfit() {
        super("Firstfit");
    }

	public ArrayList<Bin> start(ArrayList<Product> arrayProducts, int binSize) {
		int berekening = 0;
		producttenloop: for (Product product : arrayProducts) {
			if (bins.isEmpty()) {
				bins.add(new Bin());
			}
			for (Bin doos : bins) {
				berekening = doos.getCurrentSize() + product.getLength();
				if (berekening <= binSize) {
					doos.addProduct(product);
					continue producttenloop;
				}
			}
			if (berekening >= binSize) {
				bins.add(new Bin(product, binSize));
			}
		}

		return bins;
	}
}
