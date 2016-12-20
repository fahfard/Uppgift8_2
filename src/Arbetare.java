import java.util.Map;

public class Arbetare {
	
	private int time = 7 * 60; // 7h work time
	
	public void save() {
	}


	public void assignWork(Produkt workAssignment, int workUnits) {
		
		for (Map.Entry<Legobit, Integer> produktEntry : workAssignment.getPieces()) {
			Legobit testLego = produktEntry.getKey();
			int testAmount = produktEntry.getValue();
			
			System.out.println("Assign work: ");
			System.out.println("legobit length: " + testLego.getLength());
			System.out.println("legobit height: " + testLego.getHeight());
			System.out.println(testLego);

			System.out.println(testLego);
			System.out.println(testAmount);
		}

	}
}
