package bpp_simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class TekenPanel extends JPanel {

    Simulatie sim;
    ArrayList<Bin> bins = new ArrayList<Bin>();

    public TekenPanel(Simulatie sim, ArrayList<Bin> bins) {
        this.sim = sim;
        this.bins = bins;
        this.setPreferredSize(new Dimension(700, 100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        for (Bin k : bins) {
            g.setColor(Color.BLACK);
//            if (k instanceof Spongebob) {
//                g.drawRect(x, 0, 10, 15);
//                g.setColor(Color.YELLOW);
//                g.fillRect(x, 0, 10, 15);
//            }
//            if (k instanceof Red) {
//                g.drawOval(x, 0, 15, 15);
//                g.setColor(Color.RED);
//                g.fillOval(x, 0, 15, 15);
//            }
            x += 25;
        }
    }

}
