package bpp_simulator.algoritmes;

import java.util.ArrayList;

import bpp_simulator.Bin;
import bpp_simulator.Product;

public class Bruteforce extends Algoritme {

	// Code komt deels van:
	// https://software-talk.org/blog/2014/08/bin-packing-algorithm-java/
	private ArrayList<Product> ArrayProducts = new ArrayList<>();
	private ArrayList<Bin> bins, currentBestBins = new ArrayList<>();
	private int currentBestSolution;

	public Bruteforce(ArrayList<Product> ArrayPakketten, int DoosInhoud) {
		this.ArrayProducts = ArrayPakketten;

		this.bins = new ArrayList<>();

		for (Product p : ArrayPakketten) {
			bins.add(new Bin(DoosInhoud)); // create maximum of needed bins
			currentBestBins.add(new Bin(p, DoosInhoud));
		}
		currentBestSolution = ArrayPakketten.size(); // worst case solution:
														// every item one bin
	}

	public ArrayList<Bin> start() {
		bruteforce(ArrayProducts, 0);
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
		if (currentPosition >= in.size()) { // bereikt laatste product, laatste
											// keer proberen
			int filledBins = getFilledBinsCount();
			if (filledBins < currentBestSolution) {
				currentBestSolution = filledBins;
				currentBestBins = deepCopy(bins);
			}

			return;
		}
		Product currentItem = in.get(currentPosition);
		for (Bin bin : bins) {
			if (bin.addProduct(currentItem, true)) { // checkt als er ruimte is
														// voor een product
				try {
					bruteforce(in, currentPosition + 1); // weer opnieuw
															// proberen
				} catch (StackOverflowError e) {
					return;
				}
				bin.removeProduct(currentItem);
			}
		}
	}

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
