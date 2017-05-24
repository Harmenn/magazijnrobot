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

import bpp_simulator.algorithms.Algorithm;

public class Result extends JFrame {

    private ArrayList<Bin> bins = new ArrayList<>();
    private JButton jbSave;
    private final Algorithm algoritme;
    private final int volume;
    private final DrawPanel drawPanel;

    public Result(ArrayList<Bin> bins, Algorithm algoritme, int volume, int boxSize) {
        this.bins = bins;
        this.algoritme = algoritme;
        this.volume = volume;
        setTitle("Resultaat");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Maak een nieuw tekenpaneel aan die wordt verpakt aan een scrollframe
        drawPanel = new DrawPanel(this, bins, algoritme, volume, boxSize);
        JScrollPane scrollFrame = new JScrollPane(drawPanel);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        drawPanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(1050, 800));
        add(scrollFrame);
        pack();
        setResizable(false);
    }

    // Deze functie wordt gebruikt voor het opslaan van het drawpanel, zodat resultaten als een plaatje worden opgeslagen    
    public void SaveScreen(String Location) {
        // Functie gekopieerd van:
        // http://stackoverflow.com/questions/5655908/export-jpanel-graphics-to-png-or-gif-or-jpg
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
        return bins;
    }

    // Functie wordt gebruikt voor de simulatie, zoals berekenen wat het snelste algoritme is.
    public Algorithm getAlgoritme() {
        return algoritme;
    }

}
