import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LabSystem extends JFrame implements ActionListener
{
	JMenuBar menuBar;
	JMenu menuFile, menuHelp;
	JMenuItem menuExit, menuAbout, menuNew;
	JPanel jCenterPanel;
	JTextField text;
	JLabel panelLabel, fieldLabel, questionLabel;
	JButton jbPlaceOrder, jbCancelOrder;
	JCheckBox check1, check2, check3, check4, check5, check6, check7, check8, check9, check10;
	//JRadioButton;
	
	public LabSystem()
	{
		//set the name, size, location, and layout
		super("Lab Information System");
		setSize(500,400);
		setLocation(100,100);
		setLayout(new BorderLayout());
			
		//define the menu bar and menu items of the menu bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuFile = new JMenu("File");
		menuFile.setMnemonic('F');
		menuHelp = new JMenu("Help");
		menuHelp.setMnemonic('H');
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		menuExit = new JMenuItem("Exit");
		menuExit.setMnemonic('E');
		menuAbout = new JMenuItem("About");
		menuAbout.setMnemonic('A');
		menuFile.add(menuExit);
		menuHelp.add(menuAbout);
      
      //Create button panel label
		jCenterPanel = new JPanel();
		
		//Create the yes/no buttons
		jbPlaceOrder = new JButton("Place Lab Order");
		jbPlaceOrder.setMnemonic('P');
		jbCancelOrder = new JButton("Cancel Lab Order");
		jbCancelOrder.setMnemonic('C');
		jCenterPanel.add(jbPlaceOrder);
		jCenterPanel.add(jbCancelOrder);
		
		//add the action listeners
		menuExit.addActionListener(this);
		menuAbout.addActionListener(this);
		//jbPlaceOrder.addActionListener(this);
		//jbCancelOrder.addActionListener(this);
      
      add(jCenterPanel, BorderLayout.CENTER);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Exit"))
		{
			JOptionPane.showMessageDialog(null, "Are you Sure you want to exit?");
			System.exit(0);
		}
		else if(e.getActionCommand().equals("About"))
		{
			JOptionPane.showMessageDialog(null, 
            "Medical Application Integration Final Project\n" +
            "Lab Information System Group\n" +
            "Kov, Chris O, Nicolette"
            );
			System.exit(0);
		}

	}

}