package mainContainer;


import javax.swing.*;

@SuppressWarnings("serial")
public class JTableUI extends JFrame {
	public JFrame frame;

  public static void main( String[] argv ) {
//    JTableUI myExample = new JTableUI( "JTable Example" );
  }

  public JTableUI( String title , String fields[], String data[][]) {
    super( title );
    setSize( 150, 150 );
    init(fields,data);
    pack();
    setVisible( true );
  }

  private void init(String fields[], String data[][]) {
	frame = new JFrame();
	frame.setResizable(false);
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JTable jt = new JTable(data,fields);
    jt.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    if (jt.isEditing())
        jt.getCellEditor().stopCellEditing();
    JScrollPane pane = new JScrollPane( jt );
    getContentPane().add( pane );
  }
}
