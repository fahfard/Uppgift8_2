import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static javax.swing.JOptionPane.*;

import javax.swing.*;
import javax.swing.border.*;

public class Jcomp extends JFrame {

	private static final long serialVersionUID = 1L;
	
	 private JFrame mainFrame;
     private JLabel headerLabel;
     private JLabel statusLabel;
     private JPanel controlPanel;
     private JTextArea lagerArea, produktArea, produktArea2, workerArea;
     
     private String table = "";

	public Jcomp(){
		prepareGUI();
		buttons();
	}
	
	private void prepareGUI(){
	      mainFrame = new JFrame("Lego");
	      mainFrame.setSize(800,500);
	      mainFrame.setLayout(new GridLayout(1,1));
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
     
	      controlPanel = new JPanel();
	      controlPanel.setLayout(new GridLayout(2,1));
	      
	      mainFrame.add(controlPanel);
	      mainFrame.setVisible(true);  
	   }
	
	private void buttons(){
	    
		lagerArea = new JTextArea(10, 20);
	    produktArea = new JTextArea(10, 20);
	    produktArea2 = new JTextArea(10, 20);
	    
	    findFiles();
	   
	    produktArea.setText(table);
	    
	    workerArea = new JTextArea(10, 20);
	    
	    JPanel panel = new JPanel();
	    JPanel panel2 = new JPanel();
	    BorderLayout layout = new BorderLayout();
	    layout.setHgap(10);
	    layout.setVgap(10);
	    
		JButton lagerButton = new JButton("Show Lego Storage");
		JButton bestallButton = new JButton("Order Product");
		
		LegoFabrik legoWorld = new LegoFabrik();
	    
		lagerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	    	   lagerArea.setText("" + legoWorld);
			}
	    });
		
		bestallButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String indata = showInputDialog(null, "Beställ produkt: ");
				Produkt orderedProduct = legoWorld.order(indata);
				produktArea2.setText("" + orderedProduct);
		    }
		});
		panel.add(lagerArea, BorderLayout.CENTER);
		panel.add(produktArea, BorderLayout.LINE_START);
		panel2.add(produktArea2, BorderLayout.CENTER);
		panel.add(workerArea, BorderLayout.LINE_END);
		panel2.add(lagerButton, BorderLayout.NORTH);
		panel2.add(bestallButton, BorderLayout.SOUTH);
		
		controlPanel.add(panel);
		controlPanel.add(panel2);
		
		mainFrame.setVisible(true);
	}
	
	private void findFiles(){
		File dir = new File(".");
		File [] files = dir.listFiles(new FilenameFilter() {
		    @Override
		    public boolean accept(File dir, String name) {
		        return name.endsWith("_produkt.txt");
		    }
		});

		for (File produktfile : files) {
		    table += produktfile + "\n";
		}
	}
}

