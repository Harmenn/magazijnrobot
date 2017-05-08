package bpp_simulator;

import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author leroy
 */
public class Resultaat extends JFrame {

    ArrayList<Bin> Bins = new ArrayList<>();

    public Resultaat(ArrayList<Bin> Bins) {
        this.Bins = Bins;
        setSize(1200, 800);
        setTitle("Resultaat");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);

        TekenPanel tp = new TekenPanel(this, Bins);
        add(tp);
    }

}
