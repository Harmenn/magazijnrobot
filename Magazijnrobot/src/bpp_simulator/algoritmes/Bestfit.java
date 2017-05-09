package bpp_simulator.algoritmes;

import bpp_simulator.Bin;
import bpp_simulator.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Bestfit extends Algoritme {

    private ArrayList<Bin> Dozen = new ArrayList<Bin>();

    public ArrayList<Bin> start(ArrayList<Product> pk, int grootte) {
        int berekening = 0;
        producttenloop:
        for (Product product : pk) {
            if (Dozen.isEmpty()) {
                Dozen.add(new Bin());
            } else {
                Collections.sort(Dozen, new Comparator<Bin>() {
                    public int compare(Bin a, Bin b) {
                        return ((Integer) (grootte - a.getHuidigeGrootte()))
                                .compareTo(grootte - b.getHuidigeGrootte());
                    }
                });
            }
            for (Bin doos : Dozen) {
                berekening = doos.getHuidigeGrootte() + product.getLength();
                if (berekening <= grootte) {
                    doos.addProduct(product);
                    continue producttenloop;
                }
            }
            if (berekening >= grootte) {
                Dozen.add(new Bin(product, grootte));
            }
        }
//        for (Bin doos : Dozen) {
//            System.out.println(doos.getHuidigeGrootte());
//        }
        return Dozen;
    }
}
