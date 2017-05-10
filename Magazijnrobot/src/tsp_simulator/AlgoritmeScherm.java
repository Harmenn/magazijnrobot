package tsp_simulator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
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
				} else if(rdbtnAlgoritme.isSelected()) {
					panel.setAlgorithm(SimulatiePanel.BRUTE_FORCE_ALGORITHM);
				} else if(rdbtnoptTour.isSelected()) {
					panel.setAlgorithm(SimulatiePanel.TWO_OPT_ALGORITHM);
				} else if(rdbtnEigen.isSelected()) {
					panel.setAlgorithm(SimulatiePanel.OWN_ALGORITHM);
				}
				frame.repaint();
			}
		});
		btnNewButton.setBounds(10, 435, 105, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSlaSimulatieOp = new JButton("Save Simulatie");
		btnSlaSimulatieOp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {

						DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

						Document doc = docBuilder.newDocument();
						Element rootElement = doc.createElement("Simulation");
						doc.appendChild(rootElement);

						Element eleGridHeight = doc.createElement("gridHeight");
						eleGridHeight.appendChild(doc.createTextNode(Integer.toString(gridHeight)));
						rootElement.appendChild(eleGridHeight);

						Element eleGridWidth = doc.createElement("gridWidth");
						eleGridWidth.appendChild(doc.createTextNode(Integer.toString(gridWidth)));
						rootElement.appendChild(eleGridWidth);

						Element points = doc.createElement("points");
						rootElement.appendChild(points);
						
						int count = 0;
						for(Coordinate c : coords) {
							Element point = doc.createElement("point");
							point.setAttribute("id", Integer.toString(count));
							
							Element pointX = doc.createElement("x");
							pointX.appendChild(doc.createTextNode(Integer.toString(c.x)));
							point.appendChild(pointX);
							

							Element pointY = doc.createElement("y");
							pointY.appendChild(doc.createTextNode(Integer.toString(c.y)));
							point.appendChild(pointY);
							
							points.appendChild(point);
							count++;
						}

						TransformerFactory transformerFactory = TransformerFactory.newInstance();
						Transformer transformer = transformerFactory.newTransformer();
						DOMSource source = new DOMSource(doc);
						
						JFileChooser chooser = new JFileChooser(); 
					    chooser.setCurrentDirectory(new java.io.File("."));
					    chooser.setDialogTitle("Simulatie opslaan");
					    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					    
					    chooser.setAcceptAllFileFilterUsed(false);
					    if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) { 
					    	System.out.println("getCurrentDirectory(): " +  chooser.getCurrentDirectory());
					    	System.out.println("getSelectedFile() : " +  chooser.getSelectedFile());
						    StreamResult result = new StreamResult(new File(chooser.getSelectedFile().getAbsolutePath() + ".xml"));
							transformer.transform(source, result);
							System.out.println("File saved!");
					    } else {
					      System.out.println("No Selection ");
					    }

					  } catch (Exception pce) {
						  pce.printStackTrace();
					  }
			}
		});
		btnSlaSimulatieOp.setBounds(125, 435, 105, 23);
		frame.getContentPane().add(btnSlaSimulatieOp);
		
		frame.setVisible(true);
	}
}
