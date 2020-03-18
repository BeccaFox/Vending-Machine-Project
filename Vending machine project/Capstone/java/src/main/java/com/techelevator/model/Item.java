package com.techelevator.model;

import java.math.BigDecimal;

public class Item {
	private String name;
	private BigDecimal price;
	private String type;
	private int itemCount = 5;
	private int itemsSold = 0;
	private boolean soldOut = false;
	
	public Item(String name, String price) {
		this.name = name;
		this.price = new BigDecimal(price);
		
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getItemCount() {
		return this.itemCount;
	}
	
	public int getItemsSold() {
		return itemsSold;
	}
	
	public void decrementCount()	{
		itemCount--;
		itemsSold++;
		if(itemCount == 0) {
			soldOut = true;
		}
	}
	
	public boolean isSoldOut() {
		return this.soldOut;
	}
	
	
}
