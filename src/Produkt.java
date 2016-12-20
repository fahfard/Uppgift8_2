import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;
import static javax.swing.JOptionPane.*;

public class Produkt {
	private Map<Legobit, Integer> legobitarMap;
	private String name;

	public void save() {
		try(  PrintWriter out = new PrintWriter("produkt.txt")  ){
		    out.print( this );
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Produkt(String name) {
		this.name = name;	
		this.load();
	}


	public void print() {
		System.out.print(this);
	}


	public String toString() {
		String retVal = this.name + " consists of the following pieces: \n";
		

		for (Map.Entry<Legobit, Integer> entry : this.legobitarMap.entrySet()) {
			retVal += entry.getKey() + ", " + entry.getValue() + "\n";
		}

		return retVal;
	}	
	public Set<Map.Entry<Legobit, Integer>> getPieces() {
		return this.legobitarMap.entrySet();
	}
	
	public String getName(){
		return this.name;
	}

	public void add(String type, int amount) {
		Legobit nyLegobit = new Legobit(type);

		
		for (Map.Entry<Legobit, Integer> entry : this.legobitarMap.entrySet()) {
			Legobit gammalLegobit = entry.getKey();
			
			if (nyLegobit.equals(gammalLegobit)) {
				this.legobitarMap.put(gammalLegobit, entry.getValue() + amount);
				return;
			}
		}

		this.legobitarMap.put(nyLegobit, amount);


	}

	private void load() {
		String csvFile = this.name + "_produkt.txt";
        BufferedReader br = null;
        String line = "";
        String table = "";
        
 		this.legobitarMap = new HashMap<Legobit, Integer>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
				String[] typeAndAmount = line.split(", ");
				String type = typeAndAmount[0];

				int amount = Integer.parseInt(typeAndAmount[1]);
				Legobit legobit = new Legobit(type);
				
				this.legobitarMap.put(legobit, amount);
			}
        } catch (FileNotFoundException e) {
        	try(  PrintWriter out = new PrintWriter(this.name + "_produkt.txt")  ){
    		    String indata = showInputDialog(null, "This product doesnt exist! Please enter Lego bits you wish it to contain!"
    		    		+ "e.g (5_1x2,8_3x1 etc)");
        		String newBits[] = indata.split(",");
        		for(String lines: newBits){
        			String bits[] = lines.split("_");
        			Legobit legobit = new Legobit(bits[1]);
        			table += legobit.getheightNumber() + "X" + legobit.getlengthNumber() + ", " + bits[0] + "\n"; 
        		}
    		    out.print( table );
    		} catch (Exception ex) {
    			System.out.println(ex);
    		}
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
