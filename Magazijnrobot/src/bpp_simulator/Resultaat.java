package bpp_simulator;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import bpp_simulator.algoritmes.Algoritme;

public class Resultaat extends JFrame {

    private ArrayList<Bin> Bins = new ArrayList<>();
    private JButton jbSave;
    private final Algoritme Algoritme;
    private final int Volume;
    private final TekenPanel drawPanel;
    
    public Resultaat(ArrayList<Bin> Bins, Algoritme Algoritme, int Volume, int BoxSize) {
        this.Bins = Bins;
        this.Algoritme = Algoritme;
        this.Volume = Volume;
        setTitle("Resultaat");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Maak een nieuw tekenpaneel aan die wordt verpakt aan een scrollframe
        drawPanel = new TekenPanel(this, Bins, Algoritme, Volume, BoxSize);
        JScrollPane scrollFrame = new JScrollPane(drawPanel);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        drawPanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(1050, 800));
        add(scrollFrame);
        pack();
        setResizable(false);
    }

    public void SaveScreen(String Location) {
        // Functie gekopieerd van:
        // http://stackoverflow.com/questions/5655908/export-jpanel-graphics-to-png-or-gif-or-jpg
        // Deze functie wordt gebruikt voor het opslaan van het drawpanel, zodat resultaten als een plaatje worden opgeslagen
        BufferedImage bi = new BufferedImage(drawPanel.getSize().width, drawPanel.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        drawPanel.paint(g);
        g.dispose();
        try {
            ImageIO.write(bi, "png", new File(Location));
        } catch (Exception er) {
        }
    }

    public ArrayList<Bin> getBins() {
        return Bins;
    }

    public Algoritme getAlgoritme() {
        return Algoritme;
    }

}
