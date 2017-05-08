package bpp_simulator;

import java.util.ArrayList;

public class Bruteforce extends Algoritme {

    private ArrayList<Product> ArrayPakketten = new ArrayList<>();
    private ArrayList<Bin> bins = new ArrayList<Bin>();
    private int currentBestSolution;
    private ArrayList<Bin> currentBestBins = new ArrayList<>();
    private int DoosInhoud;
    private int tijd;

    public Bruteforce(ArrayList<Product> ArrayPakketten, int DoosInhoud) {
        this.ArrayPakketten = ArrayPakketten;

        this.DoosInhoud = DoosInhoud;
    }

    public ArrayList<Product> getArrayPakketten() {
        return ArrayPakketten;
    }

    public ArrayList<Bin> start() {
        System.out.println("STARTEN BRUTEFORCE");
        bruteforce(0);
        return currentBestBins;
    }

    private int getFilledBinsCount() {
        int filledBins = 0;
        for (Bin bin : bins) {
            if (bin.getAantalProducten() != 0) {
                filledBins++;
            }
        }
        return filledBins;
    }

    private void bruteforce(int currentPosition) {
        if (currentPosition >= ArrayPakketten.size()) { // reached last item, done with this iteration
            int filledBins = getFilledBinsCount();
            if (filledBins < currentBestSolution) { // is this solution better than the current best?
                currentBestSolution = filledBins; // then save it
                currentBestBins = deepCopy(bins);
            }
            return;
        }
        // iterate over bins
        Product currentItem = ArrayPakketten.get(currentPosition);
        for (Bin bin : bins) {
            if (bin.addProductCheck(currentItem)) {
                bruteforce(currentPosition + 1);
                bin.removeProduct(currentItem);
            } // else: item did not fit in bin, ignore
        }
    }

}
