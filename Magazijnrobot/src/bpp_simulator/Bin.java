package bpp_simulator;

/**
 *
 * @author leroy
 */
public class Bin {

    private int HuidigeGrootte, Grootte;
    private int id;

    public Bin() {

    }

    public Bin(int grootte) {

        this.HuidigeGrootte = grootte;
    }

    public int getId() {
        return id;
    }

    public int getHuidigeGrootte() {
        return HuidigeGrootte;
    }

    public void setHuidigeGrootte(int HuidigeGrootte) {
        this.HuidigeGrootte = HuidigeGrootte;
    }

}
