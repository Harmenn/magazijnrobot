package bpp_simulator;

import java.util.ArrayList;

public class Bruteforce extends Algoritme {

    private ArrayList<Pakket> ArrayPakketten = new ArrayList<>();
    private int DoosInhoud;
    private int tijd;

    public Bruteforce(ArrayList<Pakket> ArrayPakketten, int DoosInhoud) {
        this.ArrayPakketten = ArrayPakketten;

        this.DoosInhoud = DoosInhoud;
    }

    public void start() {
        System.out.println("STARTEN BRUTEFORCE");
        System.out.println(ArrayPakketten.size());
        bruteforce();
    }

    private void bruteforce() {
        for (Pakket pakket : ArrayPakketten) {

        }
    }

}
