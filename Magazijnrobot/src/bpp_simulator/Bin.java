package bpp_simulator;

import java.util.ArrayList;

/**
 *
 * @author leroy
 */
public class Bin {

    private int HuidigeGrootte = 0;
    private ArrayList<Product> Producten = new ArrayList<Product>();

    public Bin() {
    }

    public Bin(Product Product) {
        Producten.add(Product);
        HuidigeGrootte += Product.getLength();
    }

    public void addProduct(Product Product) {
        Producten.add(Product);
        HuidigeGrootte += Product.getLength();
    }

    public int getHuidigeGrootte() {
        return HuidigeGrootte;
    }

    public void setHuidigeGrootte(int HuidigeGrootte) {
        this.HuidigeGrootte = HuidigeGrootte;
    }

    public void addHuidigeGrootte(int HuidigeGrootte) {
        this.HuidigeGrootte = Integer.sum(this.HuidigeGrootte, HuidigeGrootte);
    }
}
