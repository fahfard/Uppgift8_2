import java.util.Map;

public class Arbetare {
	
	private int time = 7 * 60; // 7h work time
	
	public void save() {
	}


	public void assignWorker(Produkt workAssignment, int workUnits) {
		
		for (Map.Entry<Legobit, Integer> produktEntry : workAssignment.getPieces()) {
			Legobit testLego = produktEntry.getKey();
			int testAmount = produktEntry.getValue();
			
			System.out.println(testLego);
			System.out.println(testAmount);
		}

	}
}
