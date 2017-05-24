package bpp_simulator.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import bpp_simulator.Bin;
import bpp_simulator.Product;

public class Bestfit extends Algorithm {

    private final ArrayList<Bin> bins = new ArrayList<>();

    public Bestfit() {
        super("Bestfit");
    }

    public ArrayList<Bin> start(ArrayList<Product> arrayProducts, int boxSize) {

        int berekening = 0;
        producttenloop:
        for (Product product : arrayProducts) {
            if (bins.isEmpty()) {
                bins.add(new Bin());
            } else {
                Collections.sort(bins, new Comparator<Bin>() {
                    public int compare(Bin a, Bin b) {
                        return ((Integer) (boxSize - a.getCurrentSize())).compareTo(boxSize - b.getCurrentSize());
                    }
                });
            }
            for (Bin doos : bins) {
                berekening = doos.getCurrentSize() + product.getLength();
                if (berekening <= boxSize) {
                    doos.addProduct(product);
                    continue producttenloop;
                }
            }
            if (berekening >= boxSize) {
                bins.add(new Bin(product, boxSize));
            }
        }
        return bins;
    }
}
