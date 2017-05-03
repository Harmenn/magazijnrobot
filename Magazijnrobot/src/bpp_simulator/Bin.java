package bpp_simulator;

/**
 *
 * @author leroy
 */
public class Bin {

    private int HuidigeGrootte;
    private int id;

    public Bin(int id) {
        this.id = id;
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
