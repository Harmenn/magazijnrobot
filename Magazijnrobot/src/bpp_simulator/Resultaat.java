package bpp_simulator;

import bpp_simulator.algoritmes.Algoritme;
import java.awt.Dimension;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author leroy
 */
public class Resultaat extends JFrame {

    ArrayList<Bin> Bins = new ArrayList<>();
    private JButton jbOpslaan;

    public Resultaat(ArrayList<Bin> Bins, Algoritme Algoritme, int Volume, int VolumeDozen) {
        this.Bins = Bins;
        setSize(1200, 800);
        setTitle("Resultaat");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        TekenPanel tp = new TekenPanel(this, Bins, Algoritme, Volume, VolumeDozen);
        JScrollPane scrollFrame = new JScrollPane(tp);

        if (Bins.size() < 30) {
            scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        } else {
            scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        }
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tp.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(1200, 800));
        scrollFrame.setMinimumSize(new Dimension(1200, 800));
        this.add(scrollFrame);
        setResizable(false);
    }

    public void Opslaan(String Locatie) {
        //
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
