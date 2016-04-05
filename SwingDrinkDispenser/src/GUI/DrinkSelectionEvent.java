package GUI;
import java.awt.event.ActionListener;
import java.util.EventObject;


public class DrinkSelectionEvent extends EventObject{

	private int drinkID;
	
	public DrinkSelectionEvent(Object source, int id) {
		super(source);
		drinkID = id;
	}

	public int getDrinkCategory() {
		// TODO Auto-generated method stub
		return drinkID;
	}
	
}
