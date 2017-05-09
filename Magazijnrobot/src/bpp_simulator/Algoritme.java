package bpp_simulator;

import java.util.ArrayList;

public class Algoritme {

    protected ArrayList<Algoritme> Algoritmes = new ArrayList<>();
    private String naam;
    private long eindtijd;

    public String getNaam() {
        return naam;
    }

    public long getEindtijd() {
        return eindtijd;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setEindtijd(long eindtijd) {
        this.eindtijd = eindtijd;
    }

    public ArrayList<Algoritme> getAlgoritmes() {
        return Algoritmes;
    }

    public void addAlgoritme(Algoritme Algoritme) {
        this.Algoritmes.add(Algoritme);
    }
}
