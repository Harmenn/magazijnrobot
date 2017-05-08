package bpp_simulator;

import java.util.ArrayList;

public class Firstfit extends Algoritme {

    private ArrayList<Bin> Dozen = new ArrayList<Bin>();

    public ArrayList<Bin> start(ArrayList<Product> pk, int grootte) {
        int berekening = 0;
        producttenloop:
        for (Product product : pk) {
            if (Dozen.isEmpty()) {
                Dozen.add(new Bin());
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

        return Dozen;
    }
}
