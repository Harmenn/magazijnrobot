package bpp_simulator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import bpp_simulator.algoritmes.Algoritme;

public class TekenPanel extends JPanel {

    Resultaat result;
    private int startX = 50, startY = 90, startYItem = 90, colorcounter = 0, volume = 0, volumeDozen = 0;
    ArrayList<Bin> bins = new ArrayList<Bin>();
    private Algoritme Algoritme;

    public TekenPanel(Resultaat result, ArrayList<Bin> bins, Algoritme Algrotime, int Volume, int VolumeDozen) {
        this.result = result;
        this.bins = bins;
        this.volume = Volume;
        this.Algoritme = Algrotime;
        this.volumeDozen = VolumeDozen;
        this.setPreferredSize(new java.awt.Dimension(1200, 800));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        startX = 50;
        startY = 90;
        startYItem = 90;
        g.drawString("Algoritme: " + Algoritme.getName() + "    Tijdsduur: " + Algoritme.getEndTime() + "ms", startX, 20);
        g.drawString("Aantal dozen: " + bins.size(), startX, 40);
        g.drawString("Totaal volume producten: " + volume + "     Totaal volume dozen: " + volumeDozen, startX, 60);

        for (Bin bin : bins) {
            g.setColor(Color.BLACK);
            g.drawString("Grootte: " + bin.getCurrentSize(), startX, startY - 10);
            g.drawRect(startX, startY, 50, bin.getMaxSize() * 10);

            startYItem = startY;
            for (Product product : bin.getProducts()) {
                switch (colorcounter) {
                    case 0:
                        g.setColor(Color.yellow);
                        break;
                    case 1:
                        g.setColor(Color.red);
                        break;
                    case 2:
                        g.setColor(Color.blue);
                        break;
                    case 3:
                        g.setColor(Color.cyan);
                        break;
                    case 4:
                        g.setColor(Color.yellow);
                        colorcounter = 0;
                        break;
                }
                colorcounter++;
                g.fillRect(startX, startYItem, 50, product.getLength() * 10);
                startYItem += product.getLength() * 10;
                g.setColor(Color.black);
                g.drawString("" + product.getLength(), startX + 55, startYItem);
            }
            startX += 100;
            if (startX > 1000) {
                startY += 200;
                startX = 50;
            }
        }

    }

}
