package bpp_simulator;

import java.util.ArrayList;

public class Firstfit extends Algoritme {

    private ArrayList<Bin> Dozen = new ArrayList<Bin>();

    public int start(ArrayList<Pakket> pk, int grootte) {

        int binCount = 0;
        int berekening = 0;
        Dozen.add(new Bin());
        for (Pakket pakket : pk) {
            for (Bin doos : Dozen) {
                berekening = doos.getHuidigeGrootte() + pakket.getLength();
                if (berekening <= grootte) {
                    doos.setHuidigeGrootte(berekening);

                    break;
                }
                System.out.println("Huidige grootte: " + doos.getHuidigeGrootte());
            }

            if (berekening >= grootte) {
                int nieuwedoos = Dozen.size() + 1;
                Dozen.add(new Bin(pakket.getLength()));
            }
        }
        return Dozen.size();
    }
}
