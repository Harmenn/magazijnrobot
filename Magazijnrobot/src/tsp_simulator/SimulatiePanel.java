package tsp_simulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;

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

	int calcHeight = this.getHeight() - 50;
	int calcWidth = this.getWidth() - 50;

	int squareWidth = calcWidth / gridWidth;
	int squareHeight = calcHeight / gridHeight;
	int pointWidth = 20;
	int pointHeight = 20;

	// Used for drawing points differently when used by the robot
	boolean forRobot = false;

	private Coordinate currentCoord = null;

	ArrayList<Coordinate> coords;
	ArrayList<Coordinate> sortedCoords = new ArrayList<Coordinate>();

	public SimulatiePanel(int gridWidth, int gridHeight, ArrayList<Coordinate> coords) {
		setPreferredSize(new Dimension(400, 400));

		this.gridHeight = gridHeight;
		this.gridWidth = gridWidth;
		this.coords = coords;
		/*
		 * this.coords.add(new Coordinate(5, 1)); this.coords.add(new
		 * Coordinate(4, 2)); this.coords.add(new Coordinate(2, 1));
		 * 
		 * this.coords.add(new Coordinate(1, 1)); this.coords.add(new
		 * Coordinate(3, 1)); this.coords.add(new Coordinate(1, 3));
		 * this.coords.add(new Coordinate(3, 3));
		 */

		System.out.println(coords);
	}

	public SimulatiePanel(ArrayList<Coordinate> coords) {
		this(5, 5, coords);
		forRobot = true;
	}

	public void setHeight(int height) {
		this.gridHeight = height;
	}

	public void setWidth(int width) {
		this.gridWidth = width;
		repaint();
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public void setCoords(ArrayList<Coordinate> coords) {
		this.coords = coords;
		repaint();
	}

	public ArrayList<Coordinate> getSortedCoords() {
		return this.sortedCoords;
	}

	public void setCurrentCoord(Coordinate currentCoord) {
		this.currentCoord = currentCoord;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (gridWidth == 0)
			gridWidth = 5;
		if (gridHeight == 0)
			gridHeight = 5;

		startX = 9;

		calcHeight = this.getHeight() - 50;
		calcWidth = this.getWidth() - 50;

		squareWidth = calcWidth / gridWidth;
		squareHeight = calcHeight / gridHeight;
		pointWidth = squareWidth / 3;
		pointHeight = squareHeight / 3;

		// Het Grid tekenen
		g.setColor(Color.BLACK);
		for (int i = 0; i <= gridWidth; i++) {

			g.drawLine(startX + i * squareWidth, 0, startX + i * squareWidth, gridHeight * squareHeight);
		}

		for (int i = 0; i <= gridHeight; i++) {

			g.drawLine(startX, i * squareHeight, startX + gridWidth * squareWidth, i * squareHeight);
		}

		// De coordinaten tekenen
		for (Coordinate c : coords) {
			g.setColor(Color.RED);
			if(currentCoord!=null) {
				if (c.equals(currentCoord))
					g.setColor(Color.BLUE);
			}
			if (forRobot) {
				g.fillOval(startX + (c.x - 1) * squareWidth, gridHeight * squareHeight - c.y * squareHeight,
						squareWidth, squareHeight);
			} else {
				g.fillOval(startX + c.x * squareWidth - pointWidth / 2, c.y * squareHeight - pointHeight / 2,
						pointWidth, pointHeight);
			}
		}

		// Er is nog geen algoritme uitgevoerd?
		if (sortedCoords != null || sortedCoords.size() != 0) {
			// Als er een algoritme uitgevoerd is, laat de route zien
			if (currentAlgorithm != null && sortedCoords.size() != 0) {
				for (int i = 0; i < sortedCoords.size() - 1; i++) {
					g.setColor(Color.RED);
					drawLineBetween(g, sortedCoords.get(i), sortedCoords.get(i + 1));
				}

				drawLineBetween(g, sortedCoords.get(0), sortedCoords.get(sortedCoords.size() - 1));

				for (int i = 0; i < sortedCoords.size(); i++) {
					g.setColor(Color.BLACK);
					g.setFont(new Font("TimesRoman", Font.BOLD, 24));
					if (forRobot) {
						g.drawString(Integer.toString(i + 1), startX + (sortedCoords.get(i).x - 1) * squareWidth,
								gridHeight * squareHeight - sortedCoords.get(i).y * squareHeight + squareHeight);
					} else {
						g.drawString(Integer.toString(i + 1),
								startX + sortedCoords.get(i).x * squareWidth - (pointWidth / 2),
								sortedCoords.get(i).y * squareHeight - (pointHeight / 2));
					}
				}
			}
		}
	}

	private void drawLineBetween(Graphics g, Coordinate c1, Coordinate c2) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.red);
		if (forRobot) {
			g2.drawLine(startX + (c1.x - 1) * squareWidth + squareWidth / 2,
					gridHeight * squareHeight - c1.y * squareHeight + squareHeight / 2,
					startX + (c2.x - 1) * squareWidth + squareWidth / 2,
					gridHeight * squareHeight - c2.y * squareHeight + squareHeight / 2);
		} else {
			g2.drawLine(startX + c1.x * squareWidth, c1.y * squareHeight, startX + c2.x * squareWidth,
					c2.y * squareHeight);
		}
	}

	public void setAlgorithm(int algorithm) {
		System.out.println("Set algorithm: " + algorithm);
		switch (algorithm) {
		case BRUTE_FORCE_ALGORITHM:
			currentAlgorithm = new TSPBruteForce((ArrayList<Coordinate>) coords.clone());

			break;
		case NEAREST_NEIGBOUR_ALGORITHM:
			currentAlgorithm = new TSPNearestNeighbour((ArrayList<Coordinate>) coords.clone());
			break;
		case TWO_OPT_ALGORITHM:
			currentAlgorithm = new TSPOptTwo((ArrayList<Coordinate>) coords.clone());
			break;
		case OWN_ALGORITHM:
			currentAlgorithm = new TSPOwnAlgorithm((ArrayList<Coordinate>) coords.clone());
			break;
		}
		this.sortedCoords = currentAlgorithm.getSortedList();
		repaint();
	}

	public float getAlgorithmDistance() {
		// Do not execute this function before calculating the algorithm!
		// It returns the distance of the current algorithm result
		Route calculatedRoute = new Route(sortedCoords);
		return calculatedRoute.getDistance();
	}

}
