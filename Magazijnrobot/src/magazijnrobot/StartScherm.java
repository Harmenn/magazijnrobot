package magazijnrobot;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tsp_simulator.Coordinate;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.Font;

public class StartScherm {

	Order order = new Order();
	
	private Connection conn;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
	
	private JFrame frmMagazijnrobot;
	private DefaultTableModel model;
	private JTable table;
	JButton btnStop;
	JButton btnStart;
	
	private JLabel lblGekozenOrder;
	private JLabel lblVoornaam;
	private JLabel lblAchternaam;
	private JLabel lblAdres;
	private JLabel lblWoonplaats;
	private JLabel lblPostcode;
	SerialEvent event = new SerialEvent();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScherm window = new StartScherm();
					window.frmMagazijnrobot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartScherm() {
		initialize();
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			conn = DriverManager.getConnection("jdbc:mysql://lmaokai.nl/?" +"user=pat&password=pat");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMagazijnrobot = new JFrame();
		try {
			frmMagazijnrobot.setIconImage(ImageIO.read(new File("img/windesheim_icon.png")));
		} catch (IOException e1) {
		}
		frmMagazijnrobot.setTitle("Magazijnrobot");
		frmMagazijnrobot.setBounds(100, 100, 614, 300);
		frmMagazijnrobot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMagazijnrobot.getContentPane().setLayout(null);

		lblGekozenOrder = new JLabel("Gekozen order");
		lblGekozenOrder.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblGekozenOrder.setBounds(10, 45, 198, 14);
		frmMagazijnrobot.getContentPane().add(lblGekozenOrder);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				btnStart.setEnabled(false);
				btnStop.setEnabled(true);
			}
		});
		btnStart.setEnabled(false);
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnStart.setBounds(11, 70, 66, 23);
		frmMagazijnrobot.getContentPane().add(btnStart);

		JButton btnNieuweOrder = new JButton("Nieuwe order");
		btnNieuweOrder.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNieuweOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a file chooser
				final JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
				fc.setFileFilter(xmlfilter);
				fc.setDialogTitle("Open simulatie");
				// In response to a button click:
				int returnVal = fc.showOpenDialog(frmMagazijnrobot);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fc.getSelectedFile();
						DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
						Document doc = dBuilder.parse(file);
						System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
						NodeList nList = doc.getElementsByTagName("artikelnr");
						
						lblGekozenOrder.setText("Gekozen order: "+doc.getElementsByTagName("ordernummer").item(0).getTextContent());
						
						lblVoornaam.setText("Voornaam: "+doc.getElementsByTagName("voornaam").item(0).getTextContent());
						lblAchternaam.setText("Achternaam: "+doc.getElementsByTagName("achternaam").item(0).getTextContent());
						lblAdres.setText("Adres: "+doc.getElementsByTagName("adres").item(0).getTextContent());
						lblPostcode.setText("Postcode: "+doc.getElementsByTagName("postcode").item(0).getTextContent());
						lblWoonplaats.setText("Woonplaats: "+doc.getElementsByTagName("plaats").item(0).getTextContent());
						
						String ids = "";
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
						
						statement = conn.createStatement();
			            preparedStatement = conn.prepareStatement("SELECT * FROM magazijnrobot.product WHERE "+ids);
			            resultSet = preparedStatement.executeQuery();
			            while (resultSet.next()) {
			                int id = resultSet.getInt("id");
			                String name = resultSet.getString("name");
			                int volume = resultSet.getInt("volume");
			                int x = resultSet.getInt("positionX");
			                int y = resultSet.getInt("positionY");
			                
			            	order.addProduct(new Product(id, name, volume, x, y, "Wordt verwerkt"));
			            }
			            refreshTable();
						btnStart.setEnabled(true);
					} catch (Exception error) {
						error.printStackTrace();
					}

				}
			}
		});
		btnNieuweOrder.setBounds(10, 11, 176, 29);
		frmMagazijnrobot.getContentPane().add(btnNieuweOrder);

		ArrayList<Product> producten = order.getProducten();
		Object[][] data = { {} };
		String[] columnNames = { "#", "Naam", "Volume", "xy", "Status" };

		model = new DefaultTableModel(data, columnNames);
		model.removeRow(0);
		JPanel tablePanel = new JPanel(new BorderLayout());
		table = new JTable(model);
		tablePanel.add(table, BorderLayout.CENTER);
		tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
		tablePanel.setBounds(196, 11, 408, 248);
		frmMagazijnrobot.getContentPane().add(tablePanel);

		JButton btnTspSimulatie = new JButton("TSP Simulatie");
		btnTspSimulatie.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnTspSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tsp_simulator.SelectieScherm tsp = new tsp_simulator.SelectieScherm();
				tsp.dontExit();
			}
		});
		btnTspSimulatie.setBounds(10, 208, 176, 23);
		frmMagazijnrobot.getContentPane().add(btnTspSimulatie);

		JButton btnBppSimulatie = new JButton("BPP Simulatie");
		btnBppSimulatie.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnBppSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bpp_simulator.SelectieScherm bpp = new bpp_simulator.SelectieScherm();
                                bpp.ExitButton(false);
			}
		});
		btnBppSimulatie.setBounds(10, 236, 176, 23);
		frmMagazijnrobot.getContentPane().add(btnBppSimulatie);
		
		Panel panel = new Panel();
		panel.setBounds(10, 99, 179, 103);
		frmMagazijnrobot.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblVoornaam = new JLabel("Voornaam:");
		lblVoornaam.setBounds(10, 11, 191, 14);
		panel.add(lblVoornaam);
		
		lblAchternaam = new JLabel("Achternaam:");
		lblAchternaam.setBounds(10, 30, 191, 14);
		panel.add(lblAchternaam);
		
		lblAdres = new JLabel("Adres: ");
		lblAdres.setBounds(10, 47, 191, 14);
		panel.add(lblAdres);
		
		lblWoonplaats = new JLabel("Woonplaats:");
		lblWoonplaats.setBounds(9, 78, 191, 14);
		panel.add(lblWoonplaats);
		
		lblPostcode = new JLabel("Postcode: ");
		lblPostcode.setBounds(9, 62, 191, 14);
		panel.add(lblPostcode);
				
		btnStop = new JButton("Stop");
		btnStop.setEnabled(false);
		btnStop.setBounds(132, 70, 59, 23);
		frmMagazijnrobot.getContentPane().add(btnStop);
		btnStop.setFont(new Font("Tahoma", Font.PLAIN, 10));
				btnStop.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnStart.setEnabled(true);
						btnStop.setEnabled(false);
					}
				});

	}
	
	public void refreshTable() {
		for(Product p : order.getProducten()) {
        	model.addRow(new Object[]{p.getId(), p.getName(), p.getVolume(),Integer.toString(p.getX()) + ", " + Integer.toString(p.getY()), p.getStatus()});
		}
	}
}
