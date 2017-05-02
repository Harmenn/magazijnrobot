package bpp_simulator;

import java.util.ArrayList;

public class Algoritme {

    protected ArrayList<Algoritme> Algoritmes = new ArrayList<>();
    private String naam;

    public ArrayList<Algoritme> getAlgoritmes() {
        return Algoritmes;
    }

    public void addAlgoritme(Algoritme Algoritme) {
        this.Algoritmes.add(Algoritme);
    }

}
