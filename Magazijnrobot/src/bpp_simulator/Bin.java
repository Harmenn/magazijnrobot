package bpp_simulator;

import java.util.ArrayList;

public class Bin {

    private int currentSize = 0;
    private int maxSize;
    private ArrayList<Product> products = new ArrayList<>();

    public Bin() {
    }

    public int getMaxSize() {
        return maxSize;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    // Constructor, gebruik Bin(Product, 10); of Bin(10); of Bin(Product);

    public Bin(Product product, int maxSize) {
        products.add(product);
        this.maxSize = maxSize;
        currentSize += product.getLength();
    }

    public Bin(Product Product) {
        products.add(Product);
    }

    public Bin(int maxSize) {
        this.maxSize = maxSize;
    }

    // addProduct functie om een product toe te voegen aan de huidige bin
    public void addProduct(Product Product) {
        products.add(Product);
        currentSize += Product.getLength();
    }

    // addProduct overloading met check om te kijken of het product wel in de doos past
    public boolean addProduct(Product product, boolean check) {
        if (check) {
            if (currentSize + product.getLength() <= maxSize) {
                addProduct(product);
                return true; // past wel
            } else {
                return false; // past niet
            }
        } else {
            return false;
        }
    }

    public void removeProduct(Product product) {
        currentSize -= product.getLength();
        products.remove(product);
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int getProductAmount() {
        return products.size();
    }

    // Kopieeer huidige bin naar een nieuwe bin en die returnen
    public Bin deepCopy() {
        Bin copy = new Bin(maxSize);
        copy.products = new ArrayList<>(products);
        copy.currentSize = currentSize;
        return copy;
    }
}
