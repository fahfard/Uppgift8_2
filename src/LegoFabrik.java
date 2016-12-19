public class LegoFabrik {
	
	private Lager lager;
	
	public LegoFabrik() {
		this.lager = new Lager();
	}

	public String toString() {
		return this.lager.toString();
	}


	public Produkt order(String productName) {
		Produkt bestalldProdukt = new Produkt(productName);

		this.lager.removeParts(bestalldProdukt);	
		this.lager.save();
		
		if(!this.lager.checkLager())
			System.out.print("Lagret håller på ta slut!\n"); // change this later!
		return bestalldProdukt;

	}
}
