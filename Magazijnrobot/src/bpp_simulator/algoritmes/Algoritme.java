package bpp_simulator.algoritmes;

public abstract class Algoritme {

    private String name;
    private long endTime;
    private int bins;

    public Algoritme(String name) {
        this.name = name;
    }

    public int getBins() {
        return bins;
    }

    public void setBins(int bins) {
        this.bins = bins;
    }

    public String getName() {
        return name;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long eindtijd) {
        this.endTime = eindtijd;
    }
}
