package com.techelevator.model;

public class Drink extends Item {
	
	
	public Drink(String name, String price) {
		 super(name, price);
	}
	
	String drink = "Glug Glug, yum!";
	
	public String toString() {
		return drink;
	}

}
