package GUI;
import java.util.EventObject;


public class BankEvent extends EventObject{

	String pinCode;
	
	public BankEvent(Object source, String pinCode) {
		super(source);
		this.pinCode = pinCode;
	}

	public String getPinCode() {
		
		return pinCode;
	}

	
}
