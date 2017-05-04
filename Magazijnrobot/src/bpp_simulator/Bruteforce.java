package bpp_simulator;

import java.util.ArrayList;

public class Bruteforce extends Algoritme {

    private ArrayList<Product> ArrayPakketten = new ArrayList<>();
    private int DoosInhoud;
    private int tijd;

    public Bruteforce(ArrayList<Product> ArrayPakketten, int DoosInhoud) {
        this.ArrayPakketten = ArrayPakketten;

        this.DoosInhoud = DoosInhoud;
    }

    public void start() {
        System.out.println("STARTEN BRUTEFORCE");
        System.out.println(ArrayPakketten.size());
        bruteforce();
    }

    private void bruteforce() {
        for (Product pakket : ArrayPakketten) {

        }
    }

}
