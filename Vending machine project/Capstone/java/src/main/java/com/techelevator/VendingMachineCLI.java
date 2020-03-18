package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.model.Item;
import com.techelevator.model.VendingMachine;
import com.techelevator.model.Authentication;
import com.techelevator.model.Inventory;
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION };

	private VendingMachine snacks = new VendingMachine(new File("VendingMachine.txt"));
	private Menu menu;
	private Menu subMenu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;

	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println(snacks.displayItems());
				
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				subMenu = new Menu(System.in, System.out);
				boolean inPurchaseMenu = true;
				while (inPurchaseMenu) {
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					Scanner inputScanner = new Scanner(System.in);
					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						System.out.println("Enter amount: ");
						String stringMoney = inputScanner.nextLine();
						int intMoney = 0;
						try {
							intMoney = Integer.parseInt(stringMoney);
							if(intMoney > 0) {
								System.out.println(snacks.operationGetPeoplesMoney(intMoney));
								} else {
									System.out.println("Please enter more than nothing!");
								}
						} catch (Exception e){
							System.out.println("Please enter money, not words!");
						}
						

					} else if (purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
						
						
						if (snacks.balanceZero()) {
							System.out.println("Please Enter More Money: ");
						} else {
							System.out.println("Enter the Slot Number: ");
							String key = inputScanner.nextLine();
							System.out.println(snacks.purchaseItem(key));
						}
						

					} else if (purchaseChoice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
						System.out.println(snacks.getChange());

						inPurchaseMenu = false;
					}
				}
			}else if(choice.equals("SALES_REPORT")) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter the password: ");
				String password = sc.nextLine();
				Authentication auth = new Authentication();
				if(auth.verifyPassword(password)) {
					System.out.println(snacks.getSalesReport());
				} else {
					System.out.println("Nice try, suckah!!!!");
				}
				
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

}
