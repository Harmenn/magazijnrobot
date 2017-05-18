package magazijnrobot.bpp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import bpp_simulator.Bin;
import bpp_simulator.Product;

public class Algoritme {

    private ArrayList<Bin> Bins = new ArrayList<>();

    public Algoritme() {
        
    }

    public ArrayList<Bin> start(ArrayList<Product> pk, int grootte) {
        int berekening = 0;
        Collections.sort(pk, new Comparator<Product>() {
            public int compare(Product a, Product b) {
                return ((Integer) (grootte - a.getLength())).compareTo(grootte - b.getLength());
            }
        });
        producttenloop:
        for (Product product : pk) {
            if (Bins.isEmpty()) {
                Bins.add(new Bin());
            } else {
                Collections.sort(Bins, new Comparator<Bin>() {
                    public int compare(Bin a, Bin b) {
                        return ((Integer) (grootte - a.getCurrentSize())).compareTo(grootte - b.getCurrentSize());
                    }
                });
            }
            for (Bin doos : Bins) {
                berekening = doos.getCurrentSize() + product.getLength();
                if (berekening <= grootte) {
                    doos.addProduct(product);
                    continue producttenloop;
                }
            }
            if (berekening >= grootte) {
                Bins.add(new Bin(product, grootte));
            }
        }
        return Bins;
    }
}
