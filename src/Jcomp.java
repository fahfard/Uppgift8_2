import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
     private List<String> workerInfo = new ArrayList<String>();
     
     private String table = "";
     private String workerTable = "";

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
	    loadWorkerinfo();
	    getWorkerlist();
	    workerArea.setText(workerTable);
	    workerTable = "";
	    
	    JPanel panel = new JPanel();
	    JPanel panel2 = new JPanel();
	    BorderLayout layout = new BorderLayout();
	    layout.setHgap(10);
	    layout.setVgap(10);
	    
		JButton lagerButton = new JButton("Show Lego Storage");
		JButton bestallButton = new JButton("Order Product");
		JButton addlegoButton = new JButton("Add Lego");
		
		LegoFabrik legoWorld = new LegoFabrik();
		
		Lager lager = new Lager();
	    
		lagerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	    	   lagerArea.setText("" + lager);
			}
	    });
		
		bestallButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String indata = showInputDialog(null, "Beställ produkt: ");
				if(indata != null){
					Produkt orderedProduct = legoWorld.order(indata);
					produktArea2.setText("" + orderedProduct);
					findFiles();
					produktArea.setText(table);
					loadWorkerinfo();
					getWorkerlist();
				    workerArea.setText(workerTable);
				    workerTable = "";
				} else {
					// do nothing
				}
		    }
		});
		
		addlegoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	    	   String indata = showInputDialog(null, "Add lego (one model and amount at a time)");
	    	   if(indata != null){
	    		   String bits[] = indata.split("_");
	    		   
	    		   Legobit nyLegobit = new Legobit(bits[1]);
	    		   String tempLego = nyLegobit.getheightNumber() + "X" + nyLegobit.getlengthNumber();
	    		    
	    		   Lager lager = new Lager();
	    		   lager.add(tempLego, Integer.parseInt(bits[0]));
	    		   lager.save();
	    		   lagerArea.setText("" + lager);
	    	   } else {
	    		   // do nothing
	    	   }
			}
	    });
		
		panel.add(lagerArea, BorderLayout.CENTER);
		panel.add(produktArea, BorderLayout.LINE_START);
		panel2.add(produktArea2, BorderLayout.CENTER);
		panel.add(workerArea, BorderLayout.LINE_END);
		panel2.add(lagerButton, BorderLayout.NORTH);
		panel2.add(bestallButton, BorderLayout.SOUTH);
		panel2.add(addlegoButton, BorderLayout.EAST);
		
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
	
	private void loadWorkerinfo(){
		String csvFile = "arbetare.txt";
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
				if(line != null){	// incase file is empty
	            	String[] productAndminutes = line.split(",");
					
					this.workerInfo.add(productAndminutes[0] + "," + productAndminutes[1] + "," + productAndminutes[2] + "," + productAndminutes[3] + "," + productAndminutes[4]);
				}
			} 

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	private void getWorkerlist(){
		for(String lines: workerInfo){
			workerTable += lines + "\n";
		}
	this.workerInfo.clear();
	}
}

