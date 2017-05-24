package bpp_simulator.algorithms;

public abstract class Algorithm {

    private String name;
    private long endTime;
    private int bins;

    public Algorithm(String name) {
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

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
