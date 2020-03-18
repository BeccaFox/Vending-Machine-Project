package com.techelevator.model;

public class Authentication {
	private String password = "password";
	
	public boolean verifyPassword(String password) {
		
		return this.password.equals(password);
	}
}
