package bpp_simulator;

import java.awt.Color;

public class Product {

    private final int length;
    private final Color color;
    public Product(int length) {
        this.length = length;
        double hue = Math.random();
        int rgb;
        rgb = Color.HSBtoRGB((float) hue, 0.8f, 0.8f);
        this.color = new Color(rgb);
    }

    public Color getColor() {
        return color;
    }

    public int getLength() {
        return length;
    }

}
