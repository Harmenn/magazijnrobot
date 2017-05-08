package bpp_simulator;

import java.util.ArrayList;

public class Nextfit extends Algoritme {

    ArrayList<Bin> Dozen = new ArrayList<>();

    public ArrayList<Bin> start(ArrayList<Product> pk, int grootte) {
        int s = grootte;
        for (Product product : pk) {

            if (s - product.getLength() > 0) {
                s -= product.getLength();
                if (s - product.getLength() < 0) {
                    Dozen.add(new Bin(product, grootte));
                    s = grootte;
                }
                continue;
            } else {
                Dozen.add(new Bin());
                s = grootte;
            }
        }
        return Dozen;
    }
}
