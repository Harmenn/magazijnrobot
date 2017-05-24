package bpp_simulator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import bpp_simulator.algorithms.Algorithm;

public class DrawPanel extends JPanel {
    
    Result result;
    private int x = 50, y = 90, yProduct = 90, volume = 0, volumeDozen = 0, binSize = 0;
    ArrayList<Bin> bins = new ArrayList<>();
    private final Algorithm algoritme;
    
    public DrawPanel(Result result, ArrayList<Bin> bins, Algorithm algoritme, int volume, int binSize) {
        this.result = result;
        this.bins = bins;
        this.volume = volume;
        this.algoritme = algoritme;
        this.volumeDozen = binSize * bins.size();
        this.binSize = binSize;
        int prefsize = (bins.size() / 10) * (binSize * 10) + (bins.size() / 10 * 50);
        this.setIgnoreRepaint(true);
        this.setPreferredSize(new java.awt.Dimension(1200, prefsize + 100));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        x = 50;
        y = 90;
        yProduct = 90;
        g.drawString("Algoritme: " + algoritme.getName() + "    Tijdsduur: " + algoritme.getEndTime() + "ms", x,
                20);
        g.drawString("Aantal dozen: " + bins.size(), x, 40);
        g.drawString("Totaal volume producten: " + volume + "     Totaal volume dozen: " + volumeDozen, x, 60);
        
        for (Bin bin : bins) {
            g.setColor(Color.BLACK);
            g.drawString("Grootte: " + bin.getCurrentSize(), x, y - 10);
            g.drawRect(x, y, 50, bin.getMaxSize() * 10);
            yProduct = y;
            for (Product product : bin.getProducts()) {
                g.setColor(product.getColor());
                g.fillRect(x, yProduct, 50, product.getLength() * 10);
                yProduct += product.getLength() * 10;
                g.setColor(Color.black);
                g.drawString(Integer.toString(product.getLength()), x + 55, yProduct);
            }
            x += 100;
            if (x > 1000) {
                y += (binSize * 10) + 50;
                x = 50;
            }
        }
        
    }
    
}
