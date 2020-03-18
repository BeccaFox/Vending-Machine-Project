package com.techelevator.model;

public class Gum extends Item {
	
	public Gum(String name, String price) {
		 super(name, price);
	}
	
	String gum = "Chew Chew, yum!";
	
	public String toString() {
		return gum;
	}

}
