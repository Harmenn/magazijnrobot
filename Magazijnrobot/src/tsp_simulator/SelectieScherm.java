package tsp_simulator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SelectieScherm {

	private ArrayList<Coordinate> punten = new ArrayList<Coordinate>();
	private JFrame frmSelecteerTspParameters;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private DefaultListModel<String> model = new DefaultListModel<>();
	private final JList<String> list = new JList<>(model);
	private JTextField txtX;
	private JTextField txtY;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectieScherm window = new SelectieScherm();
					window.frmSelecteerTspParameters.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectieScherm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSelecteerTspParameters = new JFrame();
		frmSelecteerTspParameters.setTitle("Selecteer TSP parameters");
		frmSelecteerTspParameters.setBounds(100, 100, 272, 345);
		frmSelecteerTspParameters.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSelecteerTspParameters.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Grootte veld:");
		lblNewLabel.setBounds(10, 21, 139, 14);
		frmSelecteerTspParameters.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hoogte:");
		lblNewLabel_1.setBounds(10, 46, 64, 14);
		frmSelecteerTspParameters.getContentPane().add(lblNewLabel_1);
		
		JLabel lblBreedte = new JLabel("Breedte:");
		lblBreedte.setBounds(10, 71, 64, 14);
		frmSelecteerTspParameters.getContentPane().add(lblBreedte);
		
		txtHeight = new JTextField();
		txtHeight.setText("5");
		txtHeight.setBounds(77, 46, 72, 20);
		frmSelecteerTspParameters.getContentPane().add(txtHeight);
		txtHeight.setColumns(10);
		
		txtWidth = new JTextField();
		txtWidth.setText("5");
		txtWidth.setBounds(77, 71, 72, 20);
		frmSelecteerTspParameters.getContentPane().add(txtWidth);
		txtWidth.setColumns(10);
		
		JLabel lblPunten = new JLabel("Punten:");
		lblPunten.setBounds(159, 21, 139, 14);
		frmSelecteerTspParameters.getContentPane().add(lblPunten);
		list.setBounds(159, 45, 95, 112);
		frmSelecteerTspParameters.getContentPane().add(list);
		
		JButton btnNewButton = new JButton("Verwijderen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedIndex = list.getSelectedIndex();
				if (selectedIndex != -1) {
				    model.remove(selectedIndex);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton.setBounds(159, 164, 95, 23);
		frmSelecteerTspParameters.getContentPane().add(btnNewButton);
		
		txtX = new JTextField();
		txtX.setText("0");
		txtX.setBounds(182, 188, 72, 20);
		frmSelecteerTspParameters.getContentPane().add(txtX);
		txtX.setColumns(10);
		
		txtY = new JTextField();
		txtY.setText("0");
		txtY.setBounds(181, 214, 72, 20);
		frmSelecteerTspParameters.getContentPane().add(txtY);
		txtY.setColumns(10);
		
		JLabel lblX = new JLabel("X:");
		lblX.setBounds(159, 188, 20, 14);
		frmSelecteerTspParameters.getContentPane().add(lblX);
		
		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(158, 217, 20, 14);
		frmSelecteerTspParameters.getContentPane().add(lblY);
		
		JButton btnToevoegen = new JButton("Toevoegen");
		btnToevoegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				punten.add(new Coordinate(Integer.parseInt(txtX.getText()), Integer.parseInt(txtY.getText())));
				loadPunten();
			}
		});
		btnToevoegen.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnToevoegen.setBounds(157, 237, 95, 23);
		frmSelecteerTspParameters.getContentPane().add(btnToevoegen);
		
		JButton btnVoltooien = new JButton("Voltooien");
		btnVoltooien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSelecteerTspParameters.setVisible(false);
				AlgoritmeScherm as = new AlgoritmeScherm(Integer.parseInt(txtWidth.getText()), Integer.parseInt(txtHeight.getText()), punten);
			}
		});
		btnVoltooien.setBounds(159, 281, 95, 23);
		frmSelecteerTspParameters.getContentPane().add(btnVoltooien);
		
		JButton btnLoadSimulatie = new JButton("Load Simulatie");
		btnLoadSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a file chooser
				final JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
					     "xml files (*.xml)", "xml");
				fc.setFileFilter(xmlfilter);
				fc.setDialogTitle("Open simulatie");
				//In response to a button click:
				int returnVal = fc.showOpenDialog(frmSelecteerTspParameters);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		    		try {
			            File file = fc.getSelectedFile();
			    		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			    		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
						Document doc = dBuilder.parse(file);
						System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
						NodeList nList = doc.getElementsByTagName("point");
						txtHeight.setText(doc.getElementsByTagName("gridHeight").item(0).getTextContent());
						txtWidth.setText(doc.getElementsByTagName("gridWidth").item(0).getTextContent());
						punten.clear();
						for (int temp = 0; temp < nList.getLength(); temp++) {
							Node nNode = nList.item(temp);
							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								punten.add(new Coordinate(Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent())));
								loadPunten();
							}
						}
						doc.getDocumentElement().normalize();
					} catch (Exception error) {
						error.printStackTrace();
					}
		            
		        }
			}
		});

		btnLoadSimulatie.setBounds(34, 281, 118, 23);
		frmSelecteerTspParameters.getContentPane().add(btnLoadSimulatie);
		frmSelecteerTspParameters.setVisible(true);
	}
	
	public void loadPunten() {
		model.clear();
		for(Coordinate c : punten) {
			model.addElement(c.x + ", " + c.y);
		}
	}
}
