package mainContainer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Button;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTable;

public class GUI {

	public JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JTextField textField_2;
	private Button button;
	private JLabel lblErgebnis;
	private JLabel lblNewLabel_4;
	private GenApp evolution;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblTermine;
	private JLabel lblNewLabel_7;
	private JTable table_1;
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
				
				evolution = new GenApp();
				evolution.setMaxgen(textField.getText());
				evolution.setAnz_pruef(textField_1.getText());
				evolution.setMaxgenera(textField_2.getText());	
				evolution.berechnen();
	
				lblNewLabel_4.setText(String.valueOf(evolution.getBestleb()+1));
				lblNewLabel_6.setText(String.valueOf(evolution.getPool()[evolution.getMaxgen()][evolution.getBestleb()]));
				lblNewLabel_7.setText(evolution.getBestSeries());

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, button, 134, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, button, 257, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button, 378, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(button);
		
		lblErgebnis = new JLabel("Beste Terminserie:");
		springLayout.putConstraint(SpringLayout.NORTH, lblErgebnis, 39, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, lblErgebnis, 0, SpringLayout.WEST, lblNewLabel_3);
		frame.getContentPane().add(lblErgebnis);
		
		lblNewLabel_4 = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 0, SpringLayout.NORTH, lblErgebnis);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_4, 6, SpringLayout.EAST, lblErgebnis);
		lblNewLabel_4.setText("");
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Tabelle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int[][] pool = evolution.getPool();
				String[][] array_str = new String[evolution.getMaxgen()+3][12];	
				
				for(int i = 0; i < 12; i++) {
				System.out.println("");
				for (int j = 0; j < evolution.getMaxgen()+1; j++) {	
					if(j==0){
						int a=i+1;
						array_str[j][i] = "Terminserie " + a;
					}
					array_str[j+1][i] = String.valueOf(pool[j][i]);
					System.out.print(array_str[j+1][i]);
				}
				}
				
			    //Swap Array: This code assumes all rows have same number of columns
			    String[][] pivot = new String[array_str[0].length][];
			    for (int row = 0; row < array_str[0].length; row++)
			        pivot[row] = new String[array_str.length];

			    for (int row = 0; row < array_str.length; row++)
			        for (int col = 0; col < array_str[row].length; col++)
			            pivot[col][row] = array_str[row][col];
				
			    
			    
				String fields[] = new String[evolution.getMaxgen()+2];
				fields[0]= "";
				for (int i = 1; i < fields.length; i++) {
					fields[i] = "Tag " + i;
				}
				fields[fields.length-1]= "Strafpunkte";
						
				JTableUI table = new JTableUI( "Terminserien im Überblick", fields, pivot, evolution.getBestleb());

			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, lblNewLabel_3);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel_5 = new JLabel("Strafpunkte:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 6, SpringLayout.SOUTH, lblErgebnis);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_5, 0, SpringLayout.EAST, lblErgebnis);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_6, 0, SpringLayout.NORTH, lblNewLabel_5);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_6, 6, SpringLayout.EAST, lblNewLabel_5);
		frame.getContentPane().add(lblNewLabel_6);
		
		lblTermine = new JLabel("Terminverteilung:");
		springLayout.putConstraint(SpringLayout.NORTH, lblTermine, 6, SpringLayout.SOUTH, lblNewLabel_5);
		springLayout.putConstraint(SpringLayout.EAST, lblTermine, 0, SpringLayout.EAST, lblErgebnis);
		frame.getContentPane().add(lblTermine);
		
		lblNewLabel_7 = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_7, 0, SpringLayout.WEST, lblNewLabel_4);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_7, 0, SpringLayout.SOUTH, lblTermine);
		frame.getContentPane().add(lblNewLabel_7);
		
		table_1 = new JTable();
		table_1.setDragEnabled(true);
		springLayout.putConstraint(SpringLayout.SOUTH, table_1, 0, SpringLayout.SOUTH, lblNewLabel_5);
		springLayout.putConstraint(SpringLayout.EAST, table_1, 0, SpringLayout.EAST, lblNewLabel_3);
		frame.getContentPane().add(table_1);
		
	}
}
