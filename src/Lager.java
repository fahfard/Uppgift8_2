import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;


public class Lager {
	private Map<Legobit, Integer> legobitarMap;
	private int totalAmount;

	public void save() { 	// spara till lager.txt
		try(  PrintWriter out = new PrintWriter("lager.txt")  ){
		    out.print( this );
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Lager() {
		this.load();
	}

	public static void main(String[] args) {
		Lager lager = new Lager();
		lager.print();
	}

	public void print() {
		System.out.print(this);
	}

	public String toString() {
		String retVal = "";

		for (Map.Entry<Legobit, Integer> entry : this.legobitarMap.entrySet()) {
			retVal += entry.getKey() + ", " + entry.getValue() + "\n";
		}

		return retVal;
	}	

	public void add(String type, int amount) { // add
		Legobit nyLegobit = new Legobit(type);

		for (Map.Entry<Legobit, Integer> entry : this.legobitarMap.entrySet()) {
			Legobit gammalLegobit = entry.getKey();
			
			if (nyLegobit.equals(gammalLegobit)) { // equals overridden
				this.legobitarMap.put(gammalLegobit, entry.getValue() + amount);
				return;
			}
		}

		this.legobitarMap.put(nyLegobit, amount);
	}

	public void removeParts(Produkt produkt) { // remove legobitar
		
		for (Map.Entry<Legobit, Integer> produktEntry : produkt.getPieces()) {
			Legobit removeLegobit = produktEntry.getKey();
			int amount = produktEntry.getValue();
			
			totalAmount += amount;
			
			boolean legobitToBeUpdatedHasBeenFound = false;
			
			for (Map.Entry<Legobit, Integer> lagerEntry : this.legobitarMap.entrySet()) {
				Legobit lagerLegobit = lagerEntry.getKey();


				if (lagerLegobit.equals(removeLegobit)) {
					this.legobitarMap.put(lagerLegobit, lagerEntry.getValue() - amount);
					legobitToBeUpdatedHasBeenFound = true;
				}
			}

			if (legobitToBeUpdatedHasBeenFound == false) {
				this.legobitarMap.put(removeLegobit, -amount);
			}

		}
		
		Arbetare arbetare = new Arbetare();
		
		arbetare.assignWorker(produkt, totalAmount);
		
	}
		
	public boolean checkLager(){ // check if lager is almost empty (5) "trigger"
	
		for (Map.Entry<Legobit, Integer> entry : this.legobitarMap.entrySet()) {
			if(entry.getValue() <= 5)
				return false;
		}
	return true;
	}
		
		

	private void load() {
		String csvFile = "lager.txt";
        BufferedReader br = null;
        String line = "";

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
