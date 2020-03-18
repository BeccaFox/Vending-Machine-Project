package com.techelevator.model;

public class Candy extends Item {
	
	public Candy(String name, String price) {
		 super(name, price);
	}
	
	String candy = "Munch Munch, yum!";
	
	public String toString() {
		return candy;
	}

}
