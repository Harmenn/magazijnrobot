package magazijnrobot;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class BPP_DrawPanel extends JPanel {
    
    private ArrayList<Product> arrayProducts = new ArrayList<>();
    private int x, y, yProduct, volumeDozen, binSize;
    ArrayList<Bin> bins = new ArrayList<>();

    public BPP_DrawPanel(ArrayList<Bin> bins, int binSize) {
        this.bins = bins;
        Collections.reverse(this.bins);
        this.volumeDozen = binSize * bins.size();
        this.binSize = binSize;
        int prefsize = (bins.size() / 3) * (binSize * 10) + (bins.size() / 3 * 100);
        this.setPreferredSize(new java.awt.Dimension(350, prefsize + 100));
    }

    public void setCurrentProduct(Product currentProduct) {
        if(!arrayProducts.contains(currentProduct)){
         arrayProducts.add(currentProduct);           
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        x = 20;
        y = 90;
        yProduct = 90;
        g.drawString("Aantal dozen: " + bins.size(), x, 40);
        g.drawString("Totaal volume dozen: " + volumeDozen, x, 60);

        for (Bin bin : bins) {
            bin.setRealSize(0);
            g.setColor(Color.BLACK);
            g.drawString("Grootte: " + bin.getCurrentSize(), x, y - 10);
            g.drawRect(x, y, 50, 100);
            yProduct = y;
            int size = 0;
            for (Product product : bin.getProducts()) {

                for (Product ArrayProduct : arrayProducts) {
                    if(product.equals(ArrayProduct)){
                    size += ArrayProduct.getVolume();
                    g.setColor(ArrayProduct.getColor());
                    g.fillRect(x, yProduct, 50, product.getVolume() * 10);
                    yProduct += product.getVolume() * 10;
                    g.setColor(Color.black);
                    g.drawString(Integer.toString(product.getVolume()), x + 55, yProduct);
                     }   
                }
            }
            bin.setRealSize(size);
            //System.out.println("SIZE: " + size);
            if(bin.getRealSize() >= bin.getCurrentSize()){
                g.setColor(Color.red);
                g.drawString("VERWISSEL", x, y +  115);
            } else{
                g.setColor(Color.GREEN.darker());
                g.drawString("VULLEN", x, y +  115);                
            }
            x += 100;
            if (x > 250) {
                y += (binSize * 10) + 50;
                x = 20;
            }
        }
        
    }
    
}
