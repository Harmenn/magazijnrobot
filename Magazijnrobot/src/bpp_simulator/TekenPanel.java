package bpp_simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TekenPanel extends JPanel {

    Resultaat result;
    private int startX, startY, startYItem, index = 0, volume, volumeDozen;
    ArrayList<Bin> bins = new ArrayList<Bin>();
    private ArrayList<Color> colors;
    private Algoritme Algoritme;

    public TekenPanel(Resultaat result, ArrayList<Bin> bins, Algoritme Algrotime, int Volume, int VolumeDozen) {
        this.result = result;
        this.bins = bins;
        this.volume = Volume;
        this.Algoritme = Algrotime;
        this.volumeDozen = VolumeDozen;
        this.setPreferredSize(new Dimension(700, 800));

        this.colors = new ArrayList<>(); //add a variety of colors to switch between
        colors.add(Color.red);
        colors.add(Color.BLUE);
        colors.add(Color.PINK);
        colors.add(Color.GREEN);
        this.startX = 50;
        this.startY = 50;
        this.startYItem = 50;

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(3000, 3000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        startX = 50;
        startY = 90;
        startYItem = 90;
        g.drawString("Algoritme: " + Algoritme.getNaam() + "    Tijdsduur: " + Algoritme.getEindtijd() + "ms", startX, 20);
        g.drawString("Aantal dozen: " + bins.size(), startX, 40);
        g.drawString("Totaal volume producten: " + volume + "     Totaal volume dozen: " + volumeDozen, startX, 60);

        for (Bin bin : bins) {
            g.setColor(Color.BLACK);
            g.drawString("Grootte: " + bin.getHuidigeGrootte(), startX, startY - 10);
            g.drawRect(startX, startY, 50, bin.getMaxGrootte() * 10);

            startYItem = startY;
            for (Product product : bin.getProducten()) {
                if (index == 3) {
                    index = 0;
                }
                g.setColor(colors.get(index));
                index++;
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
