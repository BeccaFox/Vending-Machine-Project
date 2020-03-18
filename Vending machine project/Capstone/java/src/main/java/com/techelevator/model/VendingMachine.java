package com.techelevator.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class VendingMachine {

	private Inventory inventory = new Inventory();
	private BigDecimal currentBalance = BigDecimal.ZERO;
	private Log auditLog = new Log("audit_log.txt");
	private BigDecimal totalSales = BigDecimal.ZERO;

	public VendingMachine(File file) {
		importInventory(file);
	}

	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private void logOperation(String line) {
		String result = getDate();
		auditLog.printLine(result + " " + line);

	}

	public Item getItem(String key) {
		return inventory.getItem(key);
	}

	public BigDecimal getPriceOfItem(String key) {
		return inventory.getItem(key).getPrice();
	}

	public int getCountOfItem(String key) {
		return inventory.getItem(key).getItemCount();
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void buyItem(BigDecimal price) {
		currentBalance = currentBalance.subtract(price);
	}

	public void addToBalance(BigDecimal inputMoney) {
		currentBalance = currentBalance.add(inputMoney);
	}

	public void importInventory(File file) {
		try (Scanner fileScanner = new Scanner(file)) {
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] input = line.split("\\|");
				Item item = null;
				if (input[3].equals("Chip")) {
					item = new Chip(input[1], input[2]);
				} else if (input[3].equals("Candy")) {
					item = new Candy(input[1], input[2]);
				} else if (input[3].equals("Gum")) {
					item = new Gum(input[1], input[2]);
				} else if (input[3].equals("Drink")) {
					item = new Drink(input[1], input[2]);
				}
				inventory.addItem(input[0].toUpperCase(), item);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not open the file.");
		}
	}

	public String getChange() {
		String totalChange = getCurrentBalance().toString();

		String result = "Your change is: ";
		int dollars = 0;
		int quarters = 0;
		int dimes = 0;
		int nickels = 0;
		while (currentBalance.compareTo(BigDecimal.ZERO) > 0) {
			// System.out.println(currentBalance);
			if (currentBalance.compareTo(BigDecimal.ONE) >= 0) {
				dollars++;
				currentBalance = currentBalance.subtract(BigDecimal.ONE);
			} else if (currentBalance.compareTo(new BigDecimal(".25")) >= 0) {
				quarters++;
				currentBalance = currentBalance.subtract(new BigDecimal(".25"));
			} else if (currentBalance.compareTo(new BigDecimal(".10")) >= 0) {
				dimes++;
				currentBalance = currentBalance.subtract(new BigDecimal(".10"));
			} else if (currentBalance.compareTo(new BigDecimal(".05")) >= 0) {
				nickels++;
				currentBalance = currentBalance.subtract(new BigDecimal(".05"));
			}
		}
		logOperation("GIVE CHANGE: $" + totalChange + " $" + currentBalance);

		result += dollars + " dollars, " + quarters + " quarters, " + dimes + " dimes, and " + nickels + " nickels.";
		return result;

	}

	public boolean balanceZero() {
		return getCurrentBalance().equals(BigDecimal.ZERO);

	}

	public String purchaseItem(String key) {
		key = key.toUpperCase();
		String result = "Invalid slot number, Butterfingers!";

		if (inventory.itemExists(key)) {
			Item item = this.getItem(key);
			BigDecimal itemPrice = item.getPrice();
			if (this.getCurrentBalance().compareTo(itemPrice) < 0) {
				result = "Please enter more money";
			} else if (item.getItemCount() == 0) {
				result = "Sorry, sold out, choose something else.";

			} else {
				result = item.toString();
				item.decrementCount();
				this.totalSales = totalSales.add(item.getPrice());
				this.buyItem(item.getPrice());
				result += "\nYour new balance is: " + this.getCurrentBalance();
				logOperation(item.getName() + " " + key + " $" + item.getPrice() + " $" + getCurrentBalance());
			}
		}
		return result;

	}

	public String getSalesReport() {
		String result = inventory.printSalesReport();
		result += " \n" + "**TOTAL SALES** " + this.totalSales;
		return result;
	}

	public String operationGetPeoplesMoney(int intMoney) {
		String result = "";
		try {

			BigDecimal inputMoney = BigDecimal.valueOf(intMoney);
			String inputString = inputMoney.toString();
			addToBalance(inputMoney);
			result = "Current Balance: $" + getCurrentBalance().toString();
			logOperation("FEED MONEY: $" + inputString + " $" + getCurrentBalance().toString());
		} catch (Exception e) {
			result = "Please use whole dollar amounts.";
		}

		return result;

	}

	public String displayItems() {

		return inventory.toString();
	}

}
