import java.util.*;

public class Legobit {
	private int height;
	private int length;
	private String heightNumber;
	private String lengthNumber;
	
	private static final Map<String, Integer> textNummer;
	static {
		Map<String, Integer> map = new HashMap<String, Integer>();	
		map.put("Ett", 1);
		map.put("Tva", 2);
		map.put("Tre", 3);
		map.put("Fyra", 4);
		map.put("Fem", 5);
		map.put("Sex", 6);
		map.put("Sju", 7);
		map.put("Atta", 8);
		map.put("Nio", 9);
		map.put("Tio", 10);
		textNummer = Collections.unmodifiableMap(map);
	}
	
	private static final Map<Integer, String> nummerText;
	static {
		Map<Integer, String> map = new HashMap<Integer, String>();	
		map.put(1, "Ett");
		map.put(2, "Tva");
		map.put(3, "Tre");
		map.put(4, "Fyra");
		map.put(5, "Fem");
		map.put(6, "Sex");
		map.put(7, "Sju");
		map.put(8, "Atta");
		map.put(9, "Nio");
		map.put(10, "Tio");
		nummerText = Collections.unmodifiableMap(map);
	}
	public String getheightNumber(){
		return this.heightNumber;
	}
	public String getlengthNumber(){
		return this.lengthNumber;
	}
	public int getHeight() {
		return this.height;
	}
	public int getLength() {
		return this.length;
	}
	
	public Legobit (String type) {
		
		if(type.length() > 5){
			String[] typeParts = type.split("X");

			this.height = textNummer.get(typeParts[0]); 
			this.length = textNummer.get(typeParts[1]);
		} else {
			String[] typeParts = type.split("x");
			
			this.heightNumber = nummerText.get(Integer.parseInt(typeParts[0]));
			this.lengthNumber = nummerText.get(Integer.parseInt(typeParts[1]));
		}
	}
	public String toString() {
		return this.nummerText.get(this.height) + "X" + this.nummerText.get(this.length);
	}

	public boolean equals(Legobit that) {
		return (this.length == that.length && this.height == that.height);
	}
}
