package magazijnrobot;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import magazijnrobot.Product;
import tsp_simulator.Coordinate;
import tsp_simulator.SimulatiePanel;
import tsp_simulator.TSPOwnAlgorithm;

public class LiveView extends JFrame {

    private ArrayList<Product> producten = new ArrayList<Product>();
    private ArrayList<Bin> Bins = new ArrayList<>();
    private ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
    private JButton jbSave;
    private final BPP_DrawPanel bpp_panel;
    private final int Volume = 10;
    private final int BinSize = 10;
    
    private SimulatiePanel tsp_panel;
    
    public LiveView(ArrayList<Product> producten) {
    	

		setTitle("LiveView");
		setPreferredSize(new Dimension(800, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);

        BPP_Algoritme alg = new BPP_Algoritme();
        ArrayList<Bin> binlist = alg.start(producten, BinSize); 
        
        for(Product p : producten) {
        	coords.add(new Coordinate(p.getX(),p.getY()));
        }
        
        this.Bins = binlist;
        setTitle("Resultaat");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Maak een nieuw tekenpaneel aan die wordt verpakt aan een scrollframe
        bpp_panel = new BPP_DrawPanel(this, Bins, Volume, BinSize);
	    tsp_panel = new SimulatiePanel(5,5,coords);
	    tsp_panel.setAlgorithm(tsp_panel.OWN_ALGORITHM);
        JScrollPane scrollFrame = new JScrollPane(bpp_panel);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bpp_panel.setAutoscrolls(true);
        
        tsp_panel.setCurrentCoord(tsp_panel.getSortedCoords().get(0));
        scrollFrame.setBounds(10, 10, 350, 350);
        tsp_panel.setBounds(380, 10, 350, 350);
        add(scrollFrame);
        add(tsp_panel);
        pack();
    }
    
    public void setCurrentCoord(Coordinate currentCoord) {
    	tsp_panel.setCurrentCoord(currentCoord);
    }

    public ArrayList<Bin> getBins() {
        return Bins;
    }

}
