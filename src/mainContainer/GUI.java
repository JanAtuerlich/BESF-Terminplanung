package mainContainer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import java.awt.Button;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JTextField textField_2;
	private Button button;
	private JLabel lblErgebnis;
	public static String wert1 ="22";
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel_3 = new JLabel("Terminplanung nach dem Evolutionsprinzip");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_3, 47, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 15));
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Bitte Anzahl der Tage eingeben:");
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 3, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -6, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 56, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 257, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, 378, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Bitte Anzahl der Termine eingeben:");
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, lblNewLabel);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 3, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 82, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 257, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 378, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Bitte Anzahl der Generationen eingeben:");
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, 0, SpringLayout.EAST, lblNewLabel);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 3, SpringLayout.NORTH, textField_2);
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, 108, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 257, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField_2, 378, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		button = new Button("Start");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				wert1 = textField.getText();
				lblNewLabel_4.setText(wert1);
				textField_1.getText();
				textField_2.getText();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, button, 134, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, button, 257, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button, 378, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(button);
		
		lblErgebnis = new JLabel("Ergebnis:");
		springLayout.putConstraint(SpringLayout.NORTH, lblErgebnis, 39, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, lblErgebnis, 0, SpringLayout.WEST, lblNewLabel_3);
		frame.getContentPane().add(lblErgebnis);
		
		lblNewLabel_4 = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 0, SpringLayout.NORTH, lblErgebnis);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_4, 6, SpringLayout.EAST, lblErgebnis);
		frame.getContentPane().add(lblNewLabel_4);
	}
}
