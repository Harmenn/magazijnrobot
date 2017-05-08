package bpp_simulator;

import java.util.ArrayList;

public class Algoritme {

    protected ArrayList<Algoritme> Algoritmes = new ArrayList<>();

    public ArrayList<Algoritme> getAlgoritmes() {
        return Algoritmes;
    }

    public void addAlgoritme(Algoritme Algoritme) {
        this.Algoritmes.add(Algoritme);
    }

    public ArrayList<Bin> deepCopy(ArrayList<Bin> bins) {
        ArrayList<Bin> copy = new ArrayList<Bin>();
        for (int i = 0; i < bins.size(); i++) {
            copy.add(bins.get(i).deepCopy());
        }
        return copy;
    }
}
