import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Arbetare {
	
	private int time = 7 * 60; // 7h work time
	private static final int workPerpiece = 5; // 5 minutes per 1 dimension, 1x1 would equal a total of 10 minutes work.
	private int totalWorkamount;
	private String productName;
	private List<String> arbetare = new ArrayList<String>();
	
	public String toString(){
		String retVal = "";
		
		retVal += productName + "," + totalWorkamount;
		
		return retVal;
	}

	public void assignWork(Produkt workAssignment, int workUnits) {
		this.load();
		for (Map.Entry<Legobit, Integer> produktEntry : workAssignment.getPieces()) {
			Legobit testLego = produktEntry.getKey();
			int testAmount = produktEntry.getValue();
			
			System.out.println("Assign work: ");
			System.out.println("legobit length: " + testLego.getLength());
			System.out.println("legobit height: " + testLego.getHeight());
			totalWorkamount += (testLego.getLength() * workUnits); // calculate total worktime
			
			
		}
		productName = workAssignment.getName();
		System.out.println(productName + " will require a total of: " + totalWorkamount + "minutes to complete!");
		this.arbetare.add(productName + "," + totalWorkamount);
		this.save();
	}
	
	public void save() { 	// spara till arbetare.txt
		String table = "";
		
		try(  PrintWriter out = new PrintWriter("arbetare.txt")  ){
		    for(String lines : arbetare){
		    	table += lines + "\n";
		    }
			
			out.print(table);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void load() {
		String csvFile = "arbetare.txt";
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
				String[] productAndminutes = line.split(",");
				String type = productAndminutes[0];
				String amount = productAndminutes[1];
				
				this.arbetare.add(type + "," + amount);	
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
