package bpp_simulator;

import java.util.ArrayList;

public class Firstfit extends Algoritme {

    private ArrayList<Bin> Dozen = new ArrayList<Bin>();

    public int start(ArrayList<Pakket> pk, int grootte) {
        int s = grootte;
        int binCount = 0;
        int berekening = 0;
        Dozen.add(new Bin(binCount));
        for (Pakket pakket : pk) {
            for (Bin doos : Dozen) {
                berekening = doos.getHuidigeGrootte() + pakket.getLength();
                if (berekening <= grootte) {
                    doos.setHuidigeGrootte(berekening);
                    s = berekening;
                }
                System.out.println("Huidige grootte: " + doos.getHuidigeGrootte());
                System.out.println("Huidig doos id: " + doos.getId());
            }
            if (berekening + pakket.getLength() >= grootte) {

                Dozen.add(new Bin(nieuwnummer));
            }
        }
        return Dozen.size();
    }
}
