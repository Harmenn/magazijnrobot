package tsp_simulator;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartScherm extends JFrame implements ActionListener {

	private JLabel label;
	private JButton jbStart, jbResultaat;

	public StartScherm() {
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Traveling Salesman Problem Simulation");
		setLayout(new FlowLayout());

		label = new JLabel("TSP Simulator");
		label.setFont(label.getFont().deriveFont(24.0f));
		setResizable(false);
		add(label);
		add(new JLabel("Welkom bij de TSP Simulator."));
		jbStart = new JButton("Nieuwe simulatie starten");
		jbStart.addActionListener(this);
		add(jbStart);
		jbResultaat = new JButton("Opgeslagen simulaties bekijken");
		jbResultaat.addActionListener(this);
		add(jbResultaat);
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbStart) {
			SelectieScherm scherm = new SelectieScherm();
			setVisible(false);
		}
		if (e.getSource() == jbResultaat) {
			System.out.println("resultaat");
		}
	}

}
