/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mediappint;

/**
 *
 * @author student
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

public class LabGUI extends JFrame implements ActionListener
{
    JMenuBar menuBar;
    JMenu menuFile, menuHelp;
    JMenuItem menuExit, menuAbout, menuNew;
    JPanel jNorthPanel, jCenterPanel;
    JTextField text;
    JLabel panelLabel, fieldLabel, questionLabel;
    JButton jbPlaceOrder, jbCancelOrder;
    JCheckBox check1, check2, check3, check4, check5, check6, check7, check8, check9, check10;
    JTextField jtMRN;
    JTextArea jData;
    JLabel jlMRN;
    String field;
    String mrn;
    DBLoader dbloader;
    
    public LabGUI(ArrayList arr) throws Exception {
    
        //set the name, size, location, and layout
        super("Lab Information System");
        setSize(500,400);
        setLocation(100,100);
        setLayout(new BorderLayout());

        dbloader = new DBLoader();
        
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
        jNorthPanel = new JPanel();
        jCenterPanel = new JPanel();

        //create jTextField for input
        jlMRN = new JLabel("Enter MRN: ");
        jtMRN = new JTextField(20);
        jNorthPanel.add(jlMRN);
        jNorthPanel.add(jtMRN);

        //Create the yes/no buttons
        jbPlaceOrder = new JButton("Place Lab Order");
        jbPlaceOrder.setMnemonic('P');
        //jbCancelOrder = new JButton("Cancel Lab Order");
        //jbCancelOrder.setMnemonic('C');
        jNorthPanel.add(jbPlaceOrder);
        //jCenterPanel.add(jbCancelOrder);
        jData = new JTextArea("First   Last        MRN \n");
        for(int i=0; i<arr.size(); i++) {
           field = ((arr.get(i)).toString() + "\n"); 
           jData.append(field);
        }
        
        jCenterPanel.add(jData);

        //add the action listeners
        menuExit.addActionListener(this);
        menuAbout.addActionListener(this);
        //jbPlaceOrder.addActionListener(this);
        //jbCancelOrder.addActionListener(this);

        add(jNorthPanel, BorderLayout.NORTH);
        add(jCenterPanel, BorderLayout.CENTER);
        
        jbPlaceOrder.addActionListener(this);

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
        }
        else if(e.getActionCommand().equals("Place Lab Order"))
        {
            mrn = jtMRN.getText();
            try{
                dbloader.send_Request(mrn);
            }
            catch(SQLException ex) {}
            System.out.println("IN BUTTON: " + mrn);
        }

    }

}
