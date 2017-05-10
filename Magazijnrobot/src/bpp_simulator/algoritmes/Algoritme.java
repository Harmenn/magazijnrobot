package bpp_simulator.algoritmes;

import java.util.ArrayList;

public class Algoritme {

    protected ArrayList<Algoritme> Algoritmes = new ArrayList<>();
    private String name;
    private long EndTime;

    public String getName() {
        return name;
    }

    public long getEndTime() {
        return EndTime;
    }

    public void setName(String naam) {
        this.name = naam;
    }

    public void setEndTime(long eindtijd) {
        this.EndTime = eindtijd;
    }

    public ArrayList<Algoritme> getAlgoritmes() {
        return Algoritmes;
    }

    public void addAlgoritme(Algoritme Algoritme) {
        this.Algoritmes.add(Algoritme);
    }
}
