package magazijnrobot;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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

public class StartScherm {

	private JFrame frmMagazijnrobot;
	private JTable table;

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
		frmMagazijnrobot.setBounds(100, 100, 573, 300);
		frmMagazijnrobot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMagazijnrobot.getContentPane().setLayout(null);

		JLabel lblGekozenOrder = new JLabel("Gekozen order");
		lblGekozenOrder.setBounds(10, 45, 130, 14);
		frmMagazijnrobot.getContentPane().add(lblGekozenOrder);

		JButton btnStart = new JButton("Start");
		btnStart.setBounds(10, 70, 66, 23);
		frmMagazijnrobot.getContentPane().add(btnStart);

		JButton btnNewButton = new JButton("Stop");
		btnNewButton.setBounds(81, 70, 59, 23);
		frmMagazijnrobot.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


			}
		});

		btnNewButton.setBounds(81, 70, 59, 23);
		frmMagazijnrobot.getContentPane().add(btnNewButton);

		JButton btnNieuweOrder = new JButton("Nieuwe order");
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

						System.out.println("ordernummer: " + doc.getElementsByTagName("ordernummer").item(0).getTextContent());
						System.out.println("voornaam: " + doc.getElementsByTagName("voornaam").item(0).getTextContent());
						
						for (int temp = 0; temp < nList.getLength(); temp++) {
							Node nNode = nList.item(temp);
							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;

								System.out.println("ordernummer: " + eElement.getTextContent());
							}
						}
					} catch (Exception error) {
						error.printStackTrace();
					}

				}
			}
		});
		btnNieuweOrder.setBounds(10, 11, 130, 29);
		frmMagazijnrobot.getContentPane().add(btnNieuweOrder);

		Product p1 = new Product(1, "test", (float) 1.00, 1, "tset");
		ArrayList<Product> tmpProducten = new ArrayList();
		tmpProducten.add(p1);
		Order order = new Order(tmpProducten);

		ArrayList<Product> producten = order.getProducten();
		Object[][] data = { {} };
		String[] columnNames = { "#", "Omschrijving", "Prijs", "Grootte", "Status" };

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		model.removeRow(0);
		JPanel tablePanel = new JPanel(new BorderLayout());
		table = new JTable(model);
		tablePanel.add(table, BorderLayout.CENTER);
		tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
		tablePanel.setBounds(150, 11, 408, 228);
		frmMagazijnrobot.getContentPane().add(tablePanel);

		JButton btnTspSimulatie = new JButton("TSP Simulatie");
		btnTspSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tsp_simulator.SelectieScherm tsp = new tsp_simulator.SelectieScherm();
			}
		});
		btnTspSimulatie.setBounds(10, 206, 130, 23);
		frmMagazijnrobot.getContentPane().add(btnTspSimulatie);

		JButton btnBppSimulatie = new JButton("BPP Simulatie");
		btnBppSimulatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bpp_simulator.SelectieScherm bpp = new bpp_simulator.SelectieScherm();
			}
		});
		btnBppSimulatie.setBounds(10, 236, 130, 23);
		frmMagazijnrobot.getContentPane().add(btnBppSimulatie);

		for (Product i : producten) {
			Object[] product = { i.getId(), i.getOmschrijving(), i.getPrijs(), i.getGrootte(), i.getStatus() };
			model.addRow(product);
		}
	}
}
