package GUI;
import java.awt.event.ActionListener;
import java.util.EventObject;


public class PayingEvent extends EventObject{

	private String coinName;
	private String billName;
	
	public PayingEvent(Object source, String coinName, String billName) {
		super(source);
		
		this.coinName = coinName;
		this.billName = billName;
	}

	public String getCoinInput() {
		return coinName;
	}

	public String getBillInput() {
		
		return billName;
	}

}
