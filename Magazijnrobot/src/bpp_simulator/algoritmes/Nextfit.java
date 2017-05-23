package bpp_simulator.algoritmes;

import java.util.ArrayList;

import bpp_simulator.Bin;
import bpp_simulator.Product;

public class Nextfit extends Algoritme {

    ArrayList<Bin> bins = new ArrayList<>();

    public Nextfit() {
        super("Nextfit");
    }

    public ArrayList<Bin> start(ArrayList<Product> arrayProducts, int binSize) {
        int s = binSize;

        for (Product product : arrayProducts) {
            if (s - product.getLength() >= 0) {
                s -= product.getLength();
                if (bins.size() > 0) {
                    bins.get(bins.size() - 1).addProduct(product);
                } else {
                    bins.add(new Bin(product, binSize));
                }
                if (s - product.getLength() < 0) {
                    bins.add(new Bin(binSize));
                    s = binSize;
                }
                continue;
            } else {
                bins.add(new Bin(binSize));
                s = binSize;
            }
        }
        // Hotfix voor lege doos
        for (int i = 0; i < bins.size(); i++) {
            if (bins.get(i).getCurrentSize() < 1) {
                bins.remove(i);
            }
        }
        return bins;
    }

}
