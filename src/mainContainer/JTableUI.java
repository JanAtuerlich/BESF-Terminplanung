package mainContainer;


import javax.swing.*;

@SuppressWarnings("serial")
public class JTableUI extends JFrame {
	public JFrame frame;

  public static void main( String[] argv ) {
//    JTableUI myExample = new JTableUI( "JTable Example" );
  }

  public JTableUI( String title , String fields[], String data[][], int best_row) {
    super( title );
    setSize( 150, 150 );
    init(fields,data, best_row);
    pack();
    setVisible( true );
  }

  private void init(String fields[], String data[][], int best_row) {
	frame = new JFrame();
	frame.setResizable(false);
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JTable jt = new JTable(data,fields);
//    jt.setModel(new MyModel());
    jt.setEnabled(false);
    jt.getTableHeader().setReorderingAllowed(false);
    jt.getColumnModel().getColumn(0).setPreferredWidth(100);
    jt.setRowSelectionInterval(best_row, best_row);
    
    JScrollPane pane = new JScrollPane( jt );
    getContentPane().add( pane );
  }
}
