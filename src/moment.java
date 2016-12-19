public class moment {
	public static void main(String[] args) {
		LegoFabrik fabrik = new LegoFabrik();

		System.out.println(fabrik);

		Produkt bestalldProdukt = fabrik.order("polisstation");
		System.out.println(bestalldProdukt);
		System.out.println(fabrik);
		
		bestalldProdukt = fabrik.order("perfekt_lycka");
		System.out.println(bestalldProdukt);

		// testar igen
		
		System.out.println(fabrik);
	}
}
