package tsp_simulator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SelectieScherm {

	private ArrayList<Coordinate> punten = new ArrayList<Coordinate>();
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
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
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 272, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Grootte veld:");
		lblNewLabel.setBounds(10, 21, 139, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Lengte:");
		lblNewLabel_1.setBounds(10, 46, 64, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblBreedte = new JLabel("Breedte:");
		lblBreedte.setBounds(10, 71, 64, 14);
		frame.getContentPane().add(lblBreedte);
		
		textField = new JTextField();
		textField.setText("5");
		textField.setBounds(77, 46, 72, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("5");
		textField_1.setBounds(77, 71, 72, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPunten = new JLabel("Punten:");
		lblPunten.setBounds(159, 21, 139, 14);
		frame.getContentPane().add(lblPunten);
		list.setBounds(159, 45, 95, 112);
		frame.getContentPane().add(list);
		
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
		frame.getContentPane().add(btnNewButton);
		
		txtX = new JTextField();
		txtX.setText("0");
		txtX.setBounds(182, 188, 72, 20);
		frame.getContentPane().add(txtX);
		txtX.setColumns(10);
		
		txtY = new JTextField();
		txtY.setText("0");
		txtY.setBounds(181, 214, 72, 20);
		frame.getContentPane().add(txtY);
		txtY.setColumns(10);
		
		JLabel lblX = new JLabel("X:");
		lblX.setBounds(159, 188, 20, 14);
		frame.getContentPane().add(lblX);
		
		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(158, 217, 20, 14);
		frame.getContentPane().add(lblY);
		
		JButton btnToevoegen = new JButton("Toevoegen");
		btnToevoegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				punten.add(new Coordinate(Integer.parseInt(txtX.getText()), Integer.parseInt(txtY.getText())));
				loadPunten();
			}
		});
		btnToevoegen.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnToevoegen.setBounds(157, 237, 95, 23);
		frame.getContentPane().add(btnToevoegen);
		
		JButton btnVoltooien = new JButton("Voltooien");
		btnVoltooien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				SimulatieScherm ss = new SimulatieScherm();
			}
		});
		btnVoltooien.setBounds(84, 281, 89, 23);
		frame.getContentPane().add(btnVoltooien);
		frame.setVisible(true);
	}
	
	public void loadPunten() {
		model.clear();
		for(Coordinate c : punten) {
			model.addElement(c.x + ", " + c.y);
		}
	}
}
