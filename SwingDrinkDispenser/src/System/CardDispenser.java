package System;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CardDispenser {
	private Card insertedCard;

	public CardDispenser() {

	}

	public String verifyCard(String insertedPin, int priceToPay) {
		BufferedReader reader;
		BufferedWriter writer;

		ArrayList<String> lines = new ArrayList<String>();

		Boolean correctPIN = false;
		String status = new String("Unknown");

		try {

			reader = new BufferedReader(new FileReader(new File(
					"C:\\Users\\Danu\\Desktop\\JavaWorkSpace\\bank")));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split("\\s+");
				if (insertedPin.equals(lineSplit[0])) {
					correctPIN = true;
					int balance = Integer.valueOf(lineSplit[1]);
					if (balance >= priceToPay) {
						balance = balance - priceToPay;
					}
					String outLine = lineSplit[0] + " " + balance;
					lines.add(outLine); // debug here maybe ? 
				}
			}

			reader.close();
		} catch (FileNotFoundException e) {
			status = new String("Bank access failed");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			writer = new BufferedWriter(new FileWriter(new File(
					"C:\\Users\\Danu\\Desktop\\JavaWorkSpace\\bank")));

			for (String str : lines) {
				writer.write(str);
			}
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (correctPIN) {
			status = new String("Transfer completed successfully!");

		} else {
			status = new String("Incorrect PIN!");
		}

		return status;

	}
}
