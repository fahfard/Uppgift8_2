import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.JOptionPane.*;

public class Arbetare {
	
	private int time = 7 * 60; // 7h work time
	private int totalWorkTime;
	private int workerTotalLeftHours;
	private int workerTotalLeftMinutes;
	private double cost = 0.5; // cost per minute
	private double totalCost;
	private int totalWorkAmount;
	private String productName;
	private List<String> listofAssignedWork = new ArrayList<String>();
	
	public String toString(){
		
		String buffer = "";
		
		for(String lines: listofAssignedWork){
			buffer += lines + "\n";
		}
		return buffer;
	}

	public void assignWork(Produkt workAssignment, int workUnits) {
		for (Map.Entry<Legobit, Integer> produktEntry : workAssignment.getPieces()) {
			Legobit Lego = produktEntry.getKey();
			int Amount = produktEntry.getValue();
			
			totalWorkAmount += (Lego.getLength() * workUnits);
			totalWorkAmount += (Lego.getHeight() * workUnits); // calculate total worktime			
		}
		
		totalCost = cost * totalWorkAmount; // calculate total cost of produkt
		productName = workAssignment.getName();
		showMessageDialog(null, productName + " will require a total of: " + totalWorkAmount + " minutes to complete! Cost: " + totalCost + "€");
		
		totalWorkTime = time - totalWorkAmount; // calculate workers total time left
		
		workerTotalLeftHours = totalWorkTime / 60; // get hours remaining of project
		workerTotalLeftMinutes = totalWorkTime - (workerTotalLeftHours * 60); // get minutes remaining
		
		showMessageDialog(null, "Total work time remaining for worker is: " + workerTotalLeftHours + " hours and " + workerTotalLeftMinutes + " minutes");
		
		this.listofAssignedWork.add(productName + "," + totalWorkAmount + "," + workerTotalLeftHours + "," + workerTotalLeftMinutes + "," + totalCost);
		
		this.load();
		this.save();
	}
	
	public void save() { 	// spara till arbetare.txt
		String table = "";
		
		try(  PrintWriter out = new PrintWriter("arbetare.txt")  ){
		    for(String lines : listofAssignedWork){
		    	table += lines + "\n";
		    }
			
			out.print(table);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void load() {
		String csvFile = "arbetare.txt";
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
				if(line != null){	// incase file is empty
	            	String[] productAndminutes = line.split(",");
					
					this.listofAssignedWork.add(productAndminutes[0] + "," + productAndminutes[1] + "," + productAndminutes[2] + "," + productAndminutes[3] + "," + productAndminutes[4]);
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

}
