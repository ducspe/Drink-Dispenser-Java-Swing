package GUI;

public class DrinkCategory {
	private int id;
	private String text;
	
	public DrinkCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public int getId() {
		return id;
	}
}
