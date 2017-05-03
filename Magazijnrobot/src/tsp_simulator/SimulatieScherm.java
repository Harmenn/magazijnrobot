package tsp_simulator;

import java.awt.FlowLayout;

import javax.swing.JFrame;


public class SimulatieScherm extends JFrame {
	
	public SimulatieScherm()
	{
		setSize(800,800);
		setTitle("KnuffelBase");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SimulatiePanel sp = new SimulatiePanel();
		add(sp);
		
		setVisible(true);
	}
}
