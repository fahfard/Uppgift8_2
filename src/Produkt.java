import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;


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
		String retVal = this.name + " best�r av f�ljande bitar: \n";
		

		for (Map.Entry<Legobit, Integer> entry : this.legobitarMap.entrySet()) {
			retVal += entry.getKey() + ", " + entry.getValue() + "\n";
		}

		return retVal;
	}	
	public Set<Map.Entry<Legobit, Integer>> getPieces() {
		return this.legobitarMap.entrySet();
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
        String cvsSplitBy = ",";

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