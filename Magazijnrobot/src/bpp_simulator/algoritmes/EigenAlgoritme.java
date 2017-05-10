package bpp_simulator.algoritmes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import bpp_simulator.Bin;
import bpp_simulator.Product;

/**
 *
 * @author leroy
 */
public class EigenAlgoritme extends Algoritme {

	private ArrayList<Bin> Dozen = new ArrayList<Bin>();

	public ArrayList<Bin> start(ArrayList<Product> pk, int grootte) {
		int berekening = 0;
		Collections.sort(pk, new Comparator<Product>() {
			public int compare(Product a, Product b) {
				return ((Integer) (grootte - a.getLength())).compareTo(grootte - b.getLength());
			}
		});
		producttenloop: for (Product product : pk) {
			if (Dozen.isEmpty()) {
				Dozen.add(new Bin());
			} else {
				Collections.sort(Dozen, new Comparator<Bin>() {
					public int compare(Bin a, Bin b) {
						return ((Integer) (grootte - a.getCurrentSize())).compareTo(grootte - b.getCurrentSize());
					}
				});
			}
			for (Bin doos : Dozen) {
				berekening = doos.getCurrentSize() + product.getLength();
				if (berekening <= grootte) {
					doos.addProduct(product);
					continue producttenloop;
				}
			}
			if (berekening >= grootte) {
				Dozen.add(new Bin(product, grootte));
			}
		}
		return Dozen;
	}
}
