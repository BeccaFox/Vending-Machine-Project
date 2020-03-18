package com.techelevator.model;

public class Chip extends Item {
	
	
	
	 public Chip(String name, String price) {
		 super(name, price);
	}
	
	String message = "Crunch Crunch, yum!";
	
	@Override
	public String toString() {
		return message;
	}

	
	

}
