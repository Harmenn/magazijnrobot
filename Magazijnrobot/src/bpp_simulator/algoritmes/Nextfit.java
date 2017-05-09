package bpp_simulator.algoritmes;

import bpp_simulator.Bin;
import bpp_simulator.Product;
import java.util.ArrayList;

public class Nextfit extends Algoritme {

    ArrayList<Bin> Dozen = new ArrayList<>();

    public ArrayList<Bin> start(ArrayList<Product> pk, int grootte) {
        int s = grootte;

        for (Product product : pk) {
            if (s - product.getLength() >= 0) {
                s -= product.getLength();
                if (Dozen.size() > 0) {
                    Dozen.get(Dozen.size() - 1).addProduct(product);
                } else {
                    Dozen.add(new Bin(product, grootte));
                }
                if (s - product.getLength() < 0) {
                    Dozen.add(new Bin(grootte));
                    s = grootte;
                }
                continue;
            } else {
                Dozen.add(new Bin(grootte));
                s = grootte;
            }
        }
        //Hotfix voor lege doos
        for (int i = 0; i < Dozen.size(); i++) {
            if (Dozen.get(i).getHuidigeGrootte() < 1) {
                Dozen.remove(i);
            }
        }
        return Dozen;
    }

}
