package bpp_simulator;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author leroy
 */
public class Resultaat extends JFrame {

    ArrayList<Bin> Bins = new ArrayList<>();
    private JButton jbOpslaan;

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

    public void Opslaan(String Locatie) {
        BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        this.paint(g);
        g.dispose();
        try {
            ImageIO.write(bi, "png", new File(Locatie));
        } catch (Exception er) {
        }
    }
}
