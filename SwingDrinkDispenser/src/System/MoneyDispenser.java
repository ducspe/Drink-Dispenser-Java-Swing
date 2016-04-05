package System;

public class MoneyDispenser {
	Money moneyArray[] = new Money[8];
	int moneyStock[] = new int[8];
	
	public MoneyDispenser(){
		moneyArray[0] = new Money("10c", 10);
		moneyArray[1] = new Money("20c", 20);
		moneyArray[2] = new Money("50c", 50);
		moneyArray[3] = new Money("1e", 100);
		moneyArray[4] = new Money("2e", 200);
		moneyArray[5] = new Money("5e", 500);
		moneyArray[6] = new Money("10e", 1000);
		moneyArray[7] = new Money("20e", 2000);

		for(int i = 0; i < 8; i++){
			moneyStock[i] = 20;
		}
	}
	
	public int getValue(String moneyName){
		for(Money m : moneyArray){
			if(m.getName().equals(moneyName)){
				return m.getValue();
			}
		}
		return 0; // if the dispenser does not recognize such money
	}

	public void putInStock(String moneyName){
		for(int i = 0; i < moneyArray.length; i++){
			if(moneyArray[i].getName().equals(moneyName)){
				moneyStock[i]++;
			}
		}
	}
	
	public int getCoinAmount(String moneyName){
		for(int i = 0; i < 5; i++ ){
			if(moneyArray[i].getName().equals(moneyName)){
				return moneyStock[i];
			}
		}
		return 0;
	}
	
	public void outputCoin(String moneyName, int amount){
		for(int i = 0; i < 5; i++){
			if(moneyArray[i].getName().equals(moneyName)){
				moneyStock[i] = moneyStock[i] - amount;
			}
		}
	}
	
	public void fill(){
		for(int i = 0; i < 8; i++){
			moneyStock[i] = 20;
		}
	}
}







