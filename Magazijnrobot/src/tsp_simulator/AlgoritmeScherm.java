package tsp_simulator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AlgoritmeScherm {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	int gridWidth;
	int gridHeight;
	ArrayList<Coordinate> coords;
	public AlgoritmeScherm(int gridWidth, int gridHeight, ArrayList<Coordinate> coords) {
		this.gridHeight = gridHeight;
		this.gridWidth = gridWidth;
		this.coords = coords;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 684, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		SimulatiePanel panel = new SimulatiePanel(gridWidth, gridHeight, coords);
		panel.setBounds(6, 11, 310, 310);
		frame.getContentPane().add(panel);
		
		JRadioButton rdbtnAlgoritme = new JRadioButton("Brute force");
		rdbtnAlgoritme.setBounds(6, 328, 109, 23);
		frame.getContentPane().add(rdbtnAlgoritme);
		
		JRadioButton rdbtnNearestNeighbour = new JRadioButton("Nearest Neighbour");
		rdbtnNearestNeighbour.setSelected(true);
		rdbtnNearestNeighbour.setBounds(6, 354, 180, 23);
		frame.getContentPane().add(rdbtnNearestNeighbour);
		
		JRadioButton rdbtnoptTour = new JRadioButton("2-opt tour");
		rdbtnoptTour.setBounds(6, 380, 109, 23);
		frame.getContentPane().add(rdbtnoptTour);
		
		JRadioButton rdbtnEigen = new JRadioButton("Eigen");
		rdbtnEigen.setBounds(6, 405, 109, 23);
		frame.getContentPane().add(rdbtnEigen);

	    ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnAlgoritme);
	    group.add(rdbtnNearestNeighbour);
	    group.add(rdbtnoptTour);
	    group.add(rdbtnEigen);
		
		JButton btnNewButton = new JButton("Start Simulatie");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnNearestNeighbour.isSelected()) {
					panel.setAlgorithm(SimulatiePanel.NEAREST_NEIGBOUR_ALGORITHM);
					frame.repaint();
				} else if(rdbtnAlgoritme.isSelected()) {
					panel.setAlgorithm(SimulatiePanel.BRUTE_FORCE_ALGORITHM);
					frame.repaint();
				}
			}
		});
		btnNewButton.setBounds(10, 435, 105, 23);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
}
