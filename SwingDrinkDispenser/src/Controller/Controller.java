package Controller;
import java.util.ArrayList;

import GUI.MainFrame;
import System.CardDispenser;
import System.DrinkDispenser;
import System.Money;
import System.MoneyDispenser;


public class Controller {

	private MoneyDispenser md;
	private DrinkDispenser dd;
	private CardDispenser cd;
	
	private int[] userDrinkCount = new int[10];
	private ArrayList<Money> userMoney = new ArrayList<Money>();
	
	private MainFrame mainFrame;
	
	private int totalPrice = 0;
	private int totalUserInput = 0;
	
	public Controller(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		
		md = new MoneyDispenser();
		dd = new DrinkDispenser();
		cd = new CardDispenser();
	}
	
	public int calculateUserInput(int coinAmountInserted, int billAmountInserted){
		totalUserInput = totalUserInput + coinAmountInserted + billAmountInserted;
		return totalUserInput;
	}
	
	public String getDrinkName(int id){
		return dd.getDrinkName(id);
	}
	
	public int getDrinkPrice(int id){
		return dd.getPrice(id);
	}
	
	public int calcUserRequestPrice(int id){
		totalPrice = 0;
		
		for(int i = 0; i < 10; i++){
			if(dd.getDrinkCount(i) < userDrinkCount[i]){
				totalPrice += dd.getDrinkCount(i) * dd.getPrice(i);
			}
			else {
				totalPrice += userDrinkCount[i] * dd.getPrice(i);
			}
		}
		return totalPrice;
	}
	
	public void addDrinkSelection(int drinkID){
		userDrinkCount[drinkID]++;
	}
	
	public void removeDrinkSelection(int drinkID){
		userDrinkCount[drinkID]--;
	}
	
	public int checkMoney(String m){
		return md.getValue(m);
	}
	
	public int getTotalPrice(){
		return totalPrice;
	}
	
	public void storeUserMoney(String moneyName){
		int moneyValue = md.getValue(moneyName);
		userMoney.add(new Money(moneyName, moneyValue));
	}
	
	public void calculateChange(){
		int amount = totalUserInput;
		int price = totalPrice;
		
		int oneEuroAmount = md.getCoinAmount("1e");
		int twoEuroAmount = md.getCoinAmount("2e");
		int fiftyCentAmount = md.getCoinAmount("50c");
		int twentyCentAmount = md.getCoinAmount("20c");
		int tenCentAmount = md.getCoinAmount("10c");
		
		int change = amount - price;
		
		int oneEuroInitialBalance = oneEuroAmount;
		int twoEuroInitialBalance = twoEuroAmount;
		int fiftyCentInitialBalance = fiftyCentAmount;
		int twentyCentInitialBalance = twentyCentAmount;
		int tenCentInitialBalance = tenCentAmount;
		
		//--------------------------------------------------------------------------------------------------------------
		int twoEuroCoins = change / 200;
		
		if(twoEuroCoins <= twoEuroAmount){
			twoEuroAmount = twoEuroAmount - twoEuroCoins;
			change = change - twoEuroCoins * 200;
		}
		
		else if(twoEuroCoins > twoEuroAmount){
			change = change - twoEuroAmount * 200;
			twoEuroAmount = 0;
		}
		
		//--------------------------------------------------------------------------------------------------------------
		int oneEuroCoins = change / 100;
		
		if(oneEuroCoins <= oneEuroAmount){
			twoEuroAmount = oneEuroAmount - oneEuroCoins;
			change = change - oneEuroCoins * 100;
		}
		
		else if(oneEuroCoins > oneEuroAmount){
			change = change - oneEuroAmount * 100;
			oneEuroAmount = 0;
		}
		
		//--------------------------------------------------------------------------------------------------------------
		int fiftyCentCoins = change / 50;
		
		if(fiftyCentCoins <= fiftyCentAmount){
			fiftyCentAmount = fiftyCentAmount - fiftyCentCoins;
			change = change - fiftyCentCoins * 50;
		}
		
		else if(fiftyCentCoins > fiftyCentAmount){
			change = change - fiftyCentAmount * 50;
			fiftyCentAmount = 0;
		}
		
		
		//--------------------------------------------------------------------------------------------------------------
		int twentyCentCoins = change / 20;
		
		if(twentyCentCoins <= twentyCentAmount){
			twentyCentAmount = twentyCentAmount - twentyCentCoins;
			change = change - twentyCentCoins * 20;
		}
		
		else if(twentyCentCoins > twentyCentAmount){
			change = change - twentyCentAmount * 20;
			twentyCentAmount = 0;
		}
		
		

		//--------------------------------------------------------------------------------------------------------------
		int tenCentCoins = change / 10;
		
		if(tenCentCoins <= tenCentAmount){
			tenCentAmount = tenCentAmount - tenCentCoins;
			change = change - tenCentCoins * 10;
			
			md.outputCoin("1e", oneEuroInitialBalance - oneEuroAmount);
			md.outputCoin("2e", twoEuroInitialBalance - twoEuroAmount);
			md.outputCoin("50c", fiftyCentInitialBalance - fiftyCentAmount);
			md.outputCoin("20c", twentyCentInitialBalance - twentyCentAmount);
			md.outputCoin("10c", tenCentInitialBalance - tenCentAmount);
			
			for(int i = 0; i < userMoney.size(); i++){
				md.putInStock(userMoney.get(i).getName());
			}
			
			System.out.println("Money in stock:" + "2e: " + md.getCoinAmount("2e") + " 1e: " + md.getCoinAmount("1e") + " 50c: " + md.getCoinAmount("50c") + 
					" 20c: " + md.getCoinAmount("20c") + " 10c: " +  md.getCoinAmount("10c") + "\n");
			
			this.updateCoinPanel("Change 2e: " + (twoEuroInitialBalance - twoEuroAmount) + " 1e: " + (oneEuroInitialBalance - oneEuroAmount) + " 50c: " + 
			(fiftyCentInitialBalance - fiftyCentAmount) + " 20c: " + (twentyCentInitialBalance - twentyCentAmount) + " 10c: " + (tenCentInitialBalance - tenCentAmount));
			returnToInitialState();
		}
		
		else if (tenCentCoins > tenCentAmount){
			this.updateTextPanel("There are not enough coins to give change \nPlease take your money back and pay by card");
			fiftyCentAmount = fiftyCentInitialBalance;
			twentyCentAmount = twentyCentInitialBalance;
			tenCentAmount = tenCentInitialBalance;
			
			System.out.println("Money in stock:" + "2e: " + md.getCoinAmount("2e") + " 1e: " + md.getCoinAmount("1e") + " 50c: " + md.getCoinAmount("50c") + 
					" 20c: " + md.getCoinAmount("20c") + " 10c: " +  md.getCoinAmount("10c") + "\n");
			
			System.out.println("Return to customer: ");
			for(int i = 0; i < userMoney.size(); i++){
				updateCoinPanel((userMoney.get(i).getName()));
			}
			
			giveMoneyBack();
			this.updateCoinPanel("Change 2e: " + (twoEuroInitialBalance - twoEuroAmount) + " 1e: " + (oneEuroInitialBalance - oneEuroAmount) + " 50c: " + 
					(fiftyCentInitialBalance - fiftyCentAmount) + " 20c: " + (twentyCentInitialBalance - twentyCentAmount) + " 10c: " + (tenCentInitialBalance - tenCentAmount));
		
			returnToInitialState();
			}
		}
		//--------------------------------------------------------------------------------------------------------------------------------------------------
		
		public void returnToInitialState(){
			userMoney.clear();
			totalPrice = 0;
			totalUserInput = 0;
			for(int i = 0; i < userDrinkCount.length; i++){
				userDrinkCount[i] = 0;
			}
		}
		
		public void giveMoneyBack(){
			for(int i = 0; i < userMoney.size(); i++){
				totalUserInput = totalUserInput - userMoney.get(i).getValue();
			}
			
			userMoney.clear();
		}
		
		public void updateFormPanelDisplay(String t){
			mainFrame.getFormPanel().getCoinField().setText(t);
			mainFrame.getFormPanel().getBillField().setText(t);
		}
		
		public void updateTextPanel(String t){
			mainFrame.getTextPanel().setText(t);
		}
		
		public void updateCoinPanel(String t){
			mainFrame.getOutputPanel().setText(t);
		}
		
		public void verifyCard(String cardPIN){
			String status = cd.verifyCard(cardPIN, totalPrice);
			updateTextPanel(status);
		}
		
		public void fillDrinks(){
			dd.fill();
		}
		
		public void fillCoins(){
			md.fill();
		}
}
