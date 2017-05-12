package bpp_simulator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import bpp_simulator.algoritmes.Algoritme;

public class TekenPanel extends JPanel {

    Resultaat result;
    private int x = 50, y = 90, yProduct = 90, colorcounter = 0, volume = 0, volumeDozen = 0, BinSize = 0;
    ArrayList<Bin> bins = new ArrayList<>();
    private final Algoritme Algoritme;

    public TekenPanel(Resultaat result, ArrayList<Bin> Bins, Algoritme Algrotime, int Volume, int VolumeDozen) {
        this.result = result;
        this.bins = Bins;
        this.volume = Volume;
        this.Algoritme = Algrotime;
        this.volumeDozen = VolumeDozen;
        BinSize = Bins.get(0).getMaxSize();
        int prefsize = (Bins.size() / 10) * (BinSize * 10);
        System.out.println(prefsize);
        for (int i = 0; i < Bins.size() / 10; i++) {
            prefsize += 50;
        }
        System.out.println(prefsize);
        this.setIgnoreRepaint(true);
        this.setPreferredSize(new java.awt.Dimension(1200, prefsize + 100 ));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        x = 50;
        y = 90;
        yProduct = 90;
        g.drawString("Algoritme: " + Algoritme.getName() + "    Tijdsduur: " + Algoritme.getEndTime() + "ms", x,
                20);
        g.drawString("Aantal dozen: " + bins.size(), x, 40);
        g.drawString("Totaal volume producten: " + volume + "     Totaal volume dozen: " + volumeDozen, x, 60);

        for (Bin bin : bins) {
            g.setColor(Color.BLACK);
            g.drawString("Grootte: " + bin.getCurrentSize(), x, y - 10);
            g.drawRect(x, y, 50, bin.getMaxSize() * 10);
            yProduct = y;
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
                g.fillRect(x, yProduct, 50, product.getLength() * 10);
                yProduct += product.getLength() * 10;
                g.setColor(Color.black);
                g.drawString(Integer.toString(product.getLength()), x + 55, yProduct);
            }
            x += 100;
            if (x > 1000) {
                y += (BinSize * 10) + 50;
                x = 50;
            }
        }

    }

}
