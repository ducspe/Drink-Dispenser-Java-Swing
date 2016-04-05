package GUI;
import java.util.EventObject;


public class DrinkDeselectEvent extends EventObject{
private int drinkID;
	
	public DrinkDeselectEvent(Object source, int id) {
		super(source);
		drinkID = id;
	}

	public int getDrinkCategory() {
		return drinkID;
	}
}
