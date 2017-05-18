package magazijnrobot;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import magazijnrobot.Product;

public class BPP_Result extends JFrame {

    private ArrayList<Bin> Bins = new ArrayList<>();
    private JButton jbSave;
    private final int Volume;
    private final BPP_DrawPanel drawPanel;
    
    public BPP_Result(ArrayList<Bin> Bins, int Volume, int BoxSize) {
        this.Bins = Bins;
        this.Volume = Volume;
        setTitle("Resultaat");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Maak een nieuw tekenpaneel aan die wordt verpakt aan een scrollframe
        drawPanel = new BPP_DrawPanel(this, Bins, Volume, BoxSize);
        JScrollPane scrollFrame = new JScrollPane(drawPanel);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        drawPanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(1050, 800));
        add(scrollFrame);
        pack();
        setResizable(false);
    }

    public ArrayList<Bin> getBins() {
        return Bins;
    }

}
