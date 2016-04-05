package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import Controller.Controller;


public class MainFrame extends JFrame{

	
	private TextPanel textPanel;
	private TextPanel outputPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	
	
	public MainFrame(){
		super("DrinkDispenser");
		
		setLayout(new BorderLayout());
		
		toolbar = new Toolbar();
		textPanel = new TextPanel();
		outputPanel = new TextPanel();
		Dimension dim = outputPanel.getPreferredSize();
		dim.height = 100;
		outputPanel.setSize(dim);
		outputPanel.setPreferredSize(dim);
		
		formPanel = new FormPanel();
		
		Controller controller = new Controller(this);
		
		toolbar.setToolbarListener(new ToolbarListener(){
			public void performMaintenance(String text){
				textPanel.appendText(text);
				if(text.contains("Drink"))
					controller.fillDrinks();
				
				if(text.contains("Coin"))
					controller.fillCoins();
			}

		});
		
		formPanel.setMoneyListener(new MoneyListener(){
			public void paymentOccurred(PayingEvent e){
				formPanel.clearFields();
				String coinName = e.getCoinInput();
				String billName = e.getBillInput();
				
				int coinAmountInserted = controller.checkMoney(coinName);
				controller.storeUserMoney(coinName);
				int billAmountInserted = controller.checkMoney(billName);
				controller.storeUserMoney(billName);
				
				int totalInput = controller.calculateUserInput(coinAmountInserted, billAmountInserted);
				
				if(totalInput < controller.getTotalPrice()){
					textPanel.appendText("Insert more money\n");
					textPanel.appendText("Coin Value: " + coinAmountInserted + " Bill Value: " + billAmountInserted + " Total: " + totalInput + "\n");
				}
				else {
					controller.calculateChange();
				}
				
				
			}
		});
		
		
		formPanel.setDrinkListener(new DrinkListener() {
			public void drinkSelectionOccurred(DrinkSelectionEvent e){
				int drinkID = e.getDrinkCategory();
				String drinkName = controller.getDrinkName(drinkID);
				int drinkPrice = controller.getDrinkPrice(drinkID);
				controller.addDrinkSelection(drinkID);
				int totalPrice = controller.calcUserRequestPrice(drinkID);
				
				textPanel.appendText(drinkName + "Price: " + drinkPrice + "  Total: " + totalPrice + "\n");
			}
		});
		
		
		formPanel.setDrinkDeselectListener(new DrinkDeselectListener(){
			public void drinkDeselectionOccurred(DrinkDeselectEvent e){
				int drinkID = e.getDrinkCategory();
				String drinkName = controller.getDrinkName(drinkID);
				int drinkPrice = controller.getDrinkPrice(drinkID);
				controller.removeDrinkSelection(drinkID);
				int totalPrice = controller.calcUserRequestPrice(drinkID);
				
				textPanel.appendText(drinkName + "Price: " + drinkPrice + " Total: " + totalPrice + "\n");
				
			}

		
		});
		
		formPanel.setCardListener(new CardListener(){
			public void cardInsertionOccurred(BankEvent e){
				formPanel.clearFields();
				String cardPIN = e.getPinCode();
				controller.verifyCard(cardPIN);
			}
		});
		
		add(formPanel, BorderLayout.WEST);
		add(toolbar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		add(outputPanel, BorderLayout.SOUTH);
		
		setSize(600, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public TextPanel getTextPanel() {
		return textPanel;
	}
	
	public TextPanel getOutputPanel(){
		return outputPanel;
	}
	
	public FormPanel getFormPanel(){
		return formPanel;
	}
	
	
}
