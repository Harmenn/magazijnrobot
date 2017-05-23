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
    
    private static SimulatiePanel tsp_panel;
    
    public LiveView(ArrayList<Product> producten, ArrayList<Bin> Bins) {
    	

		setTitle("LiveView");
		setPreferredSize(new Dimension(800, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
        for(Product p : producten) {
        	coords.add(new Coordinate(p.getX(),p.getY()));
        }
        
        this.Bins = Bins;
        setTitle("Resultaat");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Maak een nieuw tekenpaneel aan die wordt verpakt aan een scrollframe
        bpp_panel = new BPP_DrawPanel(Bins, BinSize);
	    tsp_panel = new SimulatiePanel(coords);
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
    
    public static void setCurrentCoord(Coordinate currentCoord) {
    	tsp_panel.setCurrentCoord(currentCoord);
    	tsp_panel.repaint();
    }
    public void addProduct(Product currentProduct) {
    	bpp_panel.setCurrentProduct(currentProduct);
    	bpp_panel.repaint();
    }
    public ArrayList<Bin> getBins() {
        return Bins;
    }

}
