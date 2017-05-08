package bpp_simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class TekenPanel extends JPanel {

    Resultaat result;
    private int startX, startY, startYItem, index = 0;
    ArrayList<Bin> bins = new ArrayList<Bin>();
    private ArrayList<Color> colors;

    public TekenPanel(Resultaat result, ArrayList<Bin> bins) {
        this.result = result;
        this.bins = bins;
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        startX = 50;
        startY = 50;
        startYItem = 50;

        for (Bin bin : bins) {
            g.setColor(Color.BLACK);
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
            }
            startX += 100;
            if (startX > 600) {
                startY += 200;
                startX = 50;
            }
        }
    }

}
