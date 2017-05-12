package tsp_simulator;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.PlainDocument;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import magazijnrobot.Product;

public class SelectieScherm {

	private ArrayList<Coordinate> punten = new ArrayList<Coordinate>();
	private JFrame frmSelecteerTspParameters;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private DefaultListModel<String> model = new DefaultListModel<>();
	private final JList<String> list = new JList<>(model);
	private JTextField txtX;
	private JTextField txtY;
	private SimulatiePanel panel;

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

	public void dontExit() {
		frmSelecteerTspParameters.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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

		JLabel lblNewLabel = new JLabel("Grootte veld: ");
		lblNewLabel.setBounds(10, 21, 139, 14);
		frmSelecteerTspParameters.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Hoogte:");
		lblNewLabel_1.setBounds(10, 46, 64, 14);
		frmSelecteerTspParameters.getContentPane().add(lblNewLabel_1);

		JLabel lblBreedte = new JLabel("Breedte:");
		lblBreedte.setBounds(10, 71, 64, 14);
		frmSelecteerTspParameters.getContentPane().add(lblBreedte);

		NumberFormat amountFormat = NumberFormat.getNumberInstance();
		txtHeight = new JFormattedTextField(amountFormat);
		txtHeight.setText("5");
		txtHeight.setBounds(77, 46, 72, 20);
		frmSelecteerTspParameters.getContentPane().add(txtHeight);
		txtHeight.setColumns(3);
		

		txtHeight.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent k) {
			}

			@Override
			public void keyReleased(KeyEvent k) {
				int height = Integer.parseInt(txtHeight.getText());
				if (height < 4)
					height = 4;
				panel.setHeight(height);
				frmSelecteerTspParameters.repaint();
			}

			@Override
			public void keyTyped(KeyEvent k) {
			}

		});

		txtWidth = new JTextField();
		txtWidth.setText("5");
		txtWidth.setBounds(77, 71, 72, 20);
		txtWidth.setColumns(3);

		txtWidth.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				int width = Integer.parseInt(txtWidth.getText());
				if (width < 4)
					width = 4;
				panel.setWidth(width);
				frmSelecteerTspParameters.repaint();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

		});
		frmSelecteerTspParameters.getContentPane().add(txtWidth);

		JLabel lblPunten = new JLabel("Punten:");
		lblPunten.setBounds(159, 21, 139, 14);
		frmSelecteerTspParameters.getContentPane().add(lblPunten);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(159, 45, 95, 112);
		scrollPane.setViewportView(list);
		//list.setBounds(159, 45, 95, 112);
		//frmSelecteerTspParameters.getContentPane().add(list);
		frmSelecteerTspParameters.add(scrollPane);
		
		JButton btnNewButton = new JButton("Verwijderen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedIndex = list.getSelectedIndex();
				System.out.println(selectedIndex);
				if (selectedIndex != -1) {
					punten.remove(selectedIndex);
				}
				loadPunten();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton.setBounds(159, 164, 95, 23);
		frmSelecteerTspParameters.getContentPane().add(btnNewButton);

		txtX = new JTextField();
		txtX.setText("0");
		txtX.setBounds(182, 188, 72, 20);
		frmSelecteerTspParameters.getContentPane().add(txtX);
		txtX.setColumns(3);

		txtY = new JTextField();
		txtY.setText("0");
		txtY.setBounds(181, 214, 72, 20);
		frmSelecteerTspParameters.getContentPane().add(txtY);
		txtY.setColumns(3);

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
				panel.setCoords(punten);
				loadPunten();
			}
		});
		btnToevoegen.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnToevoegen.setBounds(157, 237, 95, 23);
		frmSelecteerTspParameters.getContentPane().add(btnToevoegen);

		JButton btnVoltooien = new JButton("Run");
		btnVoltooien.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnVoltooien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSelecteerTspParameters.setVisible(false);
				AlgoritmeScherm as = new AlgoritmeScherm(Integer.parseInt(txtWidth.getText()),
						Integer.parseInt(txtHeight.getText()), punten, frmSelecteerTspParameters);
			}
		});
		btnVoltooien.setBounds(77, 281, 64, 23);
		frmSelecteerTspParameters.getContentPane().add(btnVoltooien);

		JButton btnLoadSimulatie = new JButton("Load Simulatie");
		btnLoadSimulatie.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLoadSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a file chooser
				final JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
				fc.setFileFilter(xmlfilter);
				fc.setDialogTitle("Open simulatie");
				// In response to a button click:
				int returnVal = fc.showOpenDialog(frmSelecteerTspParameters);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fc.getSelectedFile();
						DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
						Document doc = dBuilder.parse(file);
						System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
						if(doc.getDocumentElement().getNodeName()=="Simulation")
						{
							punten.clear();
							NodeList nList = doc.getElementsByTagName("point"); 
							
							String gridHeight = doc.getElementsByTagName("gridHeight").item(0).getTextContent();
							String gridWidth = doc.getElementsByTagName("gridWidth").item(0).getTextContent();
							
							txtHeight.setText(gridHeight);
							txtWidth.setText(gridWidth);
							panel.setHeight(Integer.parseInt(gridHeight));
							panel.setWidth(Integer.parseInt(gridWidth));
							
							for (int temp = 0; temp < nList.getLength(); temp++) {
								Node nNode = nList.item(temp);
								if (nNode.getNodeType() == Node.ELEMENT_NODE) {
									Element eElement = (Element) nNode;
									punten.add(new Coordinate(
											Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent()),
											Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent())));
								}
							}
						} else if (doc.getDocumentElement().getNodeName()=="bestelling") {
							punten.clear();
							try {
								DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
								Connection conn = DriverManager.getConnection("jdbc:mysql://lmaokai.nl/?" +"user=pat&password=pat");
								String ids = "";
								NodeList nList = doc.getElementsByTagName("artikelnr");
								for (int temp = 0; temp < nList.getLength(); temp++) {
									Node nNode = nList.item(temp);
									if (nNode.getNodeType() == Node.ELEMENT_NODE) {
										Element eElement = (Element) nNode;

										System.out.println("ordernummer: " + eElement.getTextContent());
										
										ids += "id="+eElement.getTextContent() + " OR ";
									}
								}
								if(ids.length()==0) {
									ids = "0=1";
								} else {
									ids = ids.substring(0, ids.length()-4);
								}
								
								Statement statement = conn.createStatement();
					            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM magazijnrobot.product WHERE "+ids);
					            ResultSet resultSet = preparedStatement.executeQuery();
					            
					            int highestX = 4;
					            int highestY = 4;
					            
					            while (resultSet.next()) {
					                int x = resultSet.getInt("positionX");
					                int y = resultSet.getInt("positionY");
					                punten.add(new Coordinate(x,y));
					                
					                if(x > highestX) highestX = x;
					                if(y > highestY) highestY = y;
					            }

								txtHeight.setText(Integer.toString(highestY+1));
								txtWidth.setText(Integer.toString(highestX+1));
								panel.setHeight(highestY+1);
								panel.setWidth(highestX+1);
							} catch(Exception ee) {
								ee.printStackTrace();
							}
						}
						doc.getDocumentElement().normalize();
					} catch (Exception error) {
						error.printStackTrace();
					}

				}

				loadPunten();
				frmSelecteerTspParameters.repaint();
			}
		});

		btnLoadSimulatie.setBounds(151, 281, 103, 23);
		frmSelecteerTspParameters.getContentPane().add(btnLoadSimulatie);

		panel = new SimulatiePanel(Integer.parseInt(txtWidth.getText()), Integer.parseInt(txtHeight.getText()), punten);
		panel.setBounds(10, 96, 162, 160);
		frmSelecteerTspParameters.getContentPane().add(panel);
		frmSelecteerTspParameters.setVisible(true);
	}

	public void loadPunten() {
		model.clear();
		for (Coordinate c : punten) {
			model.addElement(c.x + ", " + c.y);
		}
		frmSelecteerTspParameters.repaint();
	}
}
