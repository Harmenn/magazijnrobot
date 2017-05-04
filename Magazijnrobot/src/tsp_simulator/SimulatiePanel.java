package tsp_simulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;


public class SimulatiePanel extends JPanel {
	public static final int BRUTE_FORCE_ALGORITHM = 0;
	public static final int NEAREST_NEIGBOUR_ALGORITHM = 1;
	public static final int TWO_OPT_ALGORITHM = 2;
	public static final int OWN_ALGORITHM = 3;
		
	private TSPAlgorithm currentAlgorithm;
	
	int gridWidth = 5;
	int gridHeight = 5;

	int startX = 9;
	
	int calcHeight = this.getHeight()-50;
	int calcWidth = this.getWidth()-50;
	
	int squareWidth = calcWidth/gridWidth;
	int squareHeight = calcHeight/gridHeight;
	int pointWidth = 20;
	int pointHeight = 20;
	
	
	ArrayList<Coordinate> coords;// = new ArrayList<Coordinate>();
	public SimulatiePanel(int gridWidth, int gridHeight, ArrayList<Coordinate> coords) {
		setPreferredSize(new Dimension(400, 400));		
		
		this.gridHeight = gridHeight;
		this.gridWidth = gridWidth;
		this.coords = coords;
		this.coords.add(new Coordinate(1,1));
		this.coords.add(new Coordinate(3,3));
		this.coords.add(new Coordinate(5,1));
		this.coords.add(new Coordinate(4,2));
		this.coords.add(new Coordinate(2,1));
	}
	
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(gridWidth==0) gridWidth = 5;
		if(gridHeight==0) gridHeight = 5;
		startX = 9;
		
		calcHeight = this.getHeight()-50;
		calcWidth = this.getWidth()-50;
		
		squareWidth = calcWidth/gridWidth;
		squareHeight = calcHeight/gridHeight;
		pointWidth = 20;
		pointHeight = 20;
		
        g.setColor(Color.BLACK);
        for(int i = 0; i <= gridWidth; i++) {

            g.drawLine(startX, i*squareWidth, calcWidth + startX , i*squareWidth);
        }

        for(int i = 0; i <= gridHeight; i++) {

            g.drawLine( i*squareHeight+startX, 0, i*squareHeight+startX, calcHeight);
        }
        
        for(Coordinate c : coords) {
        	g.setColor(Color.RED);
        	g.fillOval(c.x*squareHeight - (pointHeight/2) + startX, c.y*squareWidth - (pointWidth/2), pointWidth, pointHeight);
        }
        
        if(currentAlgorithm != null) {
        	for(int i = 0; i < coords.size()-1; i++){
        		g.setColor(Color.RED);
        		drawLineBetween(g, coords.get(i), coords.get(i+1));
        	}

        	for(int i = 0; i < coords.size(); i++){
        		g.setColor(Color.BLACK);
        		g.setFont(new Font("TimesRoman", Font.BOLD, 24));
        		g.drawString(Integer.toString(i+1), coords.get(i).x*squareHeight - (pointHeight/2) + startX, coords.get(i).y*squareWidth - (pointWidth/2));
        	}
    		drawLineBetween(g, coords.get(0), coords.get(coords.size()-1));
        }
	}
	

	private void drawLineBetween(Graphics g, Coordinate c1, Coordinate c2) {

	    Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.red);
		g2.drawLine(c1.x*squareHeight+startX, c1.y*squareWidth, c2.x*squareHeight+startX, c2.y*squareWidth);
	}
	
	public void setAlgorithm(int algorithm) {
		System.out.println("Set algorithm: " + algorithm);
		switch(algorithm){
			case BRUTE_FORCE_ALGORITHM:
				currentAlgorithm =  new TSPBruteForce(coords);
				break;
			case NEAREST_NEIGBOUR_ALGORITHM:
				currentAlgorithm =  new TSPNearestNeighbour(coords);
				break;
			case TWO_OPT_ALGORITHM:
				
				break;
			case OWN_ALGORITHM:
				
				break;
		}
		this.coords = currentAlgorithm.getSortedList();
		repaint();
	}
}
