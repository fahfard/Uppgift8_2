import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.*;

public class Jcomp extends JFrame {

	private static final long serialVersionUID = 1L;
	
	 private JFrame mainFrame;
     private JLabel headerLabel;
     private JLabel statusLabel;
     private JPanel controlPanel;
     private JTextArea textArea;

	public Jcomp(){
		prepareGUI();
		buttons();
	}
	
	private void prepareGUI(){
	      mainFrame = new JFrame("Lego");
	      mainFrame.setSize(800,600);
	      mainFrame.setLayout(new GridLayout(3,1));
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	      
	      headerLabel = new JLabel("", JLabel.CENTER);        
	      statusLabel = new JLabel("Test2",JLabel.CENTER);
	      
	      textArea = new JTextArea(10, 10);

	      statusLabel.setSize(10,10);

	      controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());

	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	      mainFrame.add(statusLabel);
	      mainFrame.add(textArea);
	      mainFrame.setVisible(true);  
	   }
	
	private void buttons(){
		
		JButton lagerButton = new JButton("Show Lego Storage");
		
		LegoFabrik legoWorld = new LegoFabrik();
	    
		lagerButton.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	    	   textArea.setText("" + legoWorld);
	       }
	    });
		
		controlPanel.add(lagerButton);
		mainFrame.setVisible(true);
	}
}
