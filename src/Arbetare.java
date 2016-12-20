import java.util.Map;

public class Arbetare {
	
	private int time = 7 * 60; // 7h work time
	private static final int workPerpiece = 5; // 5 minutes per 1 dimension, 1x1 would equal a total of 10 minutes work.
	private int totalWorkamount;
	
	public void save() {
	}


	public void assignWork(Produkt workAssignment, int workUnits) {
		
		for (Map.Entry<Legobit, Integer> produktEntry : workAssignment.getPieces()) {
			Legobit testLego = produktEntry.getKey();
			int testAmount = produktEntry.getValue();
			
			System.out.println("Assign work: ");
			System.out.println("legobit length: " + testLego.getLength());
			System.out.println("legobit height: " + testLego.getHeight());
			totalWorkamount += (testLego.getLength() * workUnits);

			
		}
		System.out.println("Your product will require a total of: " + totalWorkamount + "minutes to complete!");
	}
}
