package bpp_simulator;

import java.util.ArrayList;

public class Firstfit extends Algoritme {

    private ArrayList<Bin> Dozen = new ArrayList<Bin>();

    public int start(ArrayList<Product> pk, int grootte) {

        int binCount = 0;
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
                int nieuwedoos = Dozen.size() + 1;
                Dozen.add(new Bin(product));
            }
        }
//        for (Bin doos : Dozen) {
//            System.out.println(doos.getHuidigeGrootte());
//        }
        return Dozen.size();
    }
}
