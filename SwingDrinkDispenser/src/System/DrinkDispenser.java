package System;

public class DrinkDispenser {

	Drink drinkArray[] = new Drink[10];
	int inStock[] = new int[10];

	
	public DrinkDispenser(){
		drinkArray[0] = new Drink("Coke", 110);
		drinkArray[1] = new Drink("Sprite", 110);
		drinkArray[2] = new Drink("Beer", 230);
		drinkArray[3] = new Drink("LightBeer", 240);
		drinkArray[4] = new Drink("FunBeer", 210);
		drinkArray[5] = new Drink("MineralWater", 100);
		drinkArray[6] = new Drink("AppleJuice", 180);
		drinkArray[7] = new Drink("OrangeJuice", 190);
		drinkArray[8] = new Drink("TomatoJuice", 190);
		drinkArray[9] = new Drink("Wine", 280);
		
		for(int i = 0; i < 10; i++){
			inStock[i] = 20;
		}
	}

	public int getPrice(String drinkName){
		for(Drink d : drinkArray){
			if(d.getName().equals(drinkName)){
				return d.getPrice();
			}
		}
		return 0;
	}
	
	public String getDrinkName(int id){
		return drinkArray[id].getName();
	}
	
	public int getPrice(int id){
		return drinkArray[id].getPrice();
	}
	
	public int getDrinkCount(int id){
		return inStock[id];
	}
	
	public void removeFromStock(int id, int amount){
		inStock[id] -= amount;
	}
	
	public void fill(){
		for(int i = 0; i < 10; i++){
			inStock[i] = 20;
		}
	}
}
