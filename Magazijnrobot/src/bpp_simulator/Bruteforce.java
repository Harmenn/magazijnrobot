package bpp_simulator;

import java.util.ArrayList;

public class Bruteforce extends Algoritme {

    private ArrayList<Product> ArrayPakketten = new ArrayList<>();
    private ArrayList<Bin> bins;
    private int currentBestSolution;
    private ArrayList<Bin> currentBestBins = new ArrayList<>();

    private int DoosInhoud;
    private int tijd;

    public Bruteforce(ArrayList<Product> ArrayPakketten, int DoosInhoud) {
        this.ArrayPakketten = ArrayPakketten;

        this.DoosInhoud = DoosInhoud;
        this.bins = new ArrayList<>();

        for (Product p : ArrayPakketten) {
            bins.add(new Bin(DoosInhoud)); // create maximum of needed bins
            currentBestBins.add(new Bin(p, DoosInhoud));
        }
        currentBestSolution = ArrayPakketten.size(); // worst case solution: every item one bin
    }

    public ArrayList<Product> getArrayPakketten() {
        return ArrayPakketten;
    }

    public ArrayList<Bin> starten() {
        bruteforce(ArrayPakketten, 0);
        return currentBestBins;
    }

    public int getFilledBinsCount() {
        int filledBins = 0;
        for (Bin bin : bins) {
            if (bin.getAantalProducten() != 0) {
                filledBins++;
            }
        }
        return filledBins;
    }

    private void bruteforce(ArrayList<Product> in, int currentPosition) {
        if (currentPosition >= in.size()) { // reached last item, done with this iteration
            int filledBins = getFilledBinsCount();
            if (filledBins < currentBestSolution) { // is this solution better than the current best?
                currentBestSolution = filledBins; // then save it
                currentBestBins = deepCopy(bins); //copies the solution to the attribute ArrayList currentBestBins
            }

            return;
        }
        // iterate over bins
        Product currentItem = in.get(currentPosition);
        for (Bin bin : bins) {
            if (bin.addProductCheck(currentItem)) { //checkt als er ruimte is voor een product, zoja
                try {
                    bruteforce(in, currentPosition + 1); //recurive approach for next item
                } catch (StackOverflowError e) {
                    return;
                }
                bin.removeProduct(currentItem);
            } // else: item did not fit in bin, ignore
        }
    }

    public void printBestBins() {
        System.out.println("Bins:");
        if (currentBestSolution == ArrayPakketten.size()) {
            System.out.println("each item is in its own bin");
        } else {
            for (Bin currentBin : currentBestBins) {
                if (currentBin.getAantalProducten() != 0) { // don't print empty bins
                    System.out.println(currentBin.toString());
                }
            }
        }
    }

    //Method to create a new array of bins
    public ArrayList<Bin> deepCopy(ArrayList<Bin> bins) {
        ArrayList<Bin> copy = new ArrayList<>(); //creates a new ArrayList to store all Bin objects in

        for (int i = 0; i < bins.size(); i++) {
            if (bins.get(i).getAantalProducten() != 0) { //Checks if the Bin isn't empty
                //copies all the data from the give ArrayList in the parameters
                copy.add(bins.get(i).deepCopy());
            }
        }

        //removes all Bin object that have no items in it
        for (Bin bin : copy) {
            if (bin.getAantalProducten() == 0) {
                copy.remove(bin);
            }
        }

        //returns the Bin ArrayList
        return copy;
    }
}
