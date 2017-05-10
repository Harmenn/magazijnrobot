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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		try {frmMagazijnrobot.setIconImage(ImageIO.read(new File("img/windesheim_icon.png")));} catch (IOException e1) {}
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					event.sendMessage("test");
				}catch(Exception ee) {}
			}
		});
		btnNewButton.setBounds(81, 70, 59, 23);
		frmMagazijnrobot.getContentPane().add(btnNewButton);
		
		JButton btnNieuweOrder = new JButton("Nieuwe order");
		btnNieuweOrder.setBounds(10, 11, 130, 29);
		frmMagazijnrobot.getContentPane().add(btnNieuweOrder);
		
		Product p1 = new Product(1, "test", (float) 1.00, 1, "tset");
		ArrayList<Product> tmpProducten = new ArrayList();
		tmpProducten.add(p1);
		Order order = new Order(tmpProducten);
		
		
		ArrayList<Product> producten = order.getProducten();
		Object[][] data = {{}};
		String[] columnNames = {"#",
                "Omschrijving",
                "Prijs",
                "Grootte",
                "Status"};
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		model.removeRow(0);
		JPanel tablePanel = new JPanel(new BorderLayout());
		table = new JTable(model);
		tablePanel.add(table, BorderLayout.CENTER);
		tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
		tablePanel.setBounds(150, 11, 408, 228);
		frmMagazijnrobot.getContentPane().add(tablePanel);
		
		
		for(Product i : producten) {
			Object[] product = {i.getId(), i.getOmschrijving(), i.getPrijs(), i.getGrootte(), i.getStatus()};
			model.addRow(product);
		}
	}
}
