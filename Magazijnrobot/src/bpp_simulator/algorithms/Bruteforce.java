package bpp_simulator.algorithms;

import java.util.ArrayList;

import bpp_simulator.Bin;
import bpp_simulator.Product;

public class Bruteforce extends Algorithm {

    // Code komt deels van:
    // https://software-talk.org/blog/2014/08/bin-packing-algorithm-java/
    private ArrayList<Product> arrayProducts = new ArrayList<>();
    private ArrayList<Bin> bins, currentBestBins = new ArrayList<>();
    private int currentBestSolution;

    public Bruteforce(ArrayList<Product> arrayProducts, int binSize) {
        super("Bruteforce");
        this.arrayProducts = arrayProducts;

        this.bins = new ArrayList<>();

        for (Product p : arrayProducts) {
            bins.add(new Bin(binSize)); // create maximum of needed bins
            currentBestBins.add(new Bin(p, binSize));
        }
        currentBestSolution = arrayProducts.size(); // worst case solution:
        // every item one bin
    }

    public ArrayList<Bin> start() {
        bruteforce(arrayProducts, 0);
        return currentBestBins;
    }

    public int getFilledBinsCount() {
        int filledBins = 0;
        for (Bin bin : bins) {
            if (bin.getProductAmount() != 0) {
                filledBins++;
            }
        }
        return filledBins;
    }

    private void bruteforce(ArrayList<Product> in, int currentPosition) {
        if (currentPosition >= in.size()) { // Laatste product bereikt, nog een keer proberen
            int filledBins = getFilledBinsCount();
            if (filledBins < currentBestSolution) {
                currentBestSolution = filledBins;
                currentBestBins = deepCopy(bins);
            }
            return;
        }
        Product currentItem = in.get(currentPosition);
        for (Bin bin : bins) {
            if (bin.addProduct(currentItem, true)) { // Kijken of er ruimte is voor een product
                try {
                    bruteforce(in, currentPosition + 1); // Opnieuw proberen +1 item
                } catch (StackOverflowError e) {
                    return;
                }
                bin.removeProduct(currentItem);
            }
        }
    }

    // Maak een kloon van de bins arraylist
    public ArrayList<Bin> deepCopy(ArrayList<Bin> bins) {
        ArrayList<Bin> copy = new ArrayList<>();

        for (Bin bin : bins) {
            if (bin.getProductAmount() > 0) {
                copy.add(bin.deepCopy());
            }
        }
        return copy;
    }
}
