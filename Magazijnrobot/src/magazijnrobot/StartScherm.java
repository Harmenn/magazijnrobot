package magazijnrobot;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Toolkit;

public class StartScherm {

	private JFrame frmMagazijnrobot;
	private JTable table;
	private JTable table_1;

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
			}
		});
		btnNewButton.setBounds(81, 70, 59, 23);
		frmMagazijnrobot.getContentPane().add(btnNewButton);
		
		JButton btnNieuweOrder = new JButton("Nieuwe order");
		btnNieuweOrder.setBounds(10, 11, 130, 29);
		frmMagazijnrobot.getContentPane().add(btnNieuweOrder);
		Object[][] data = {
			    {"1", "Lego doos",
			     "€5,00", new Integer(5), "Opgehaalt"},
			    {"2", "Lego tas",
			     "€3,00", new Integer(3), "Kwijt geraakt"}
			};
		String[] columnNames = {"#",
                "Omschrijving",
                "Prijs",
                "Grootte",
                "Status"};
		
		
		// = new JTable(data, columnNames);
		//table_1.setBounds(150, 18, 242, 105);
		//frame.getContentPane().add(new JScrollPane(table_1));


		JPanel tablePanel = new JPanel(new BorderLayout());
		table_1 = new JTable(data, columnNames);
		tablePanel.add(table_1, BorderLayout.CENTER);
		tablePanel.add(table_1.getTableHeader(), BorderLayout.NORTH);
		tablePanel.setBounds(150, 11, 408, 228);
		frmMagazijnrobot.getContentPane().add(tablePanel);
	}
}
