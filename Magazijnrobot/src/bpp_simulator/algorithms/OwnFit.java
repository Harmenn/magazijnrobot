package bpp_simulator.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import bpp_simulator.Bin;
import bpp_simulator.Product;

public class OwnFit extends Algorithm {

    private ArrayList<Bin> bins = new ArrayList<Bin>();

    public OwnFit() {
        super("Eigenfit");
    }

    public ArrayList<Bin> start(ArrayList<Product> arrayProducts, int binSize) {
        int berekening = 0;
        Collections.sort(arrayProducts, new Comparator<Product>() {
            public int compare(Product a, Product b) {
                return ((Integer) (binSize - a.getLength())).compareTo(binSize - b.getLength());
            }
        });
        producttenloop:
        for (Product product : arrayProducts) {
            if (bins.isEmpty()) {
                bins.add(new Bin());
            } else {
                Collections.sort(bins, new Comparator<Bin>() {
                    public int compare(Bin a, Bin b) {
                        return ((Integer) (binSize - a.getCurrentSize())).compareTo(binSize - b.getCurrentSize());
                    }
                });
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
