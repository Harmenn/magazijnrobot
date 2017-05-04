package bpp_simulator;

import java.util.ArrayList;

public class Nextfit extends Algoritme {

    public int start(ArrayList<Product> pk, int grootte) {
        int binCount = 0;
        int s = grootte;
        for (Product product : pk) {

            if (s - product.getLength() > 0) {
                s -= product.getLength();
                if (s - product.getLength() < 0) {
                    binCount++;
                    s = grootte;
                }
                continue;
            } else {
                binCount++;
                s = grootte;
            }
        }
        return binCount;
    }
}
