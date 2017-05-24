package bpp_simulator;

import java.awt.Color;

public class Product {

    private final int length;
    private final Color color;

    // Constructor voor product. Tijdens het aanmaken zal er ook een kleur worden meegegeven.
    public Product(int length) {
        this.length = length;

        // Wij maken gebruik van HSB zodat je de brightness en saturation zelf kan instellen.
        // Dit zorgt ervoor dat je geen felle kleuren krijgt.
        double hsb = Math.random();
        int rgb;
        rgb = Color.HSBtoRGB((float) hsb, 0.8f, 0.8f);
        this.color = new Color(rgb);
    }

    public Color getColor() {
        return color;
    }

    public int getLength() {
        return length;
    }

}
