package com.techelevator.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Inventory {
	
	private Map<String, Item> products = new LinkedHashMap<>();
	
	public void addItem(String key, Item item) {
		products.put(key, item);
	}
	
	public boolean itemExists(String key) {
		return products.containsKey(key);
	}
	
	public Item getItem(String key) {
		return products.get(key);
	}
	
	public String printSalesReport() {
		String result = "";
		for(String key : products.keySet()) {
			Item item = products.get(key);
			result += item.getName() + " | " + item.getItemsSold() + " \n";
			
		}
		return result;
			
	}
	
	@Override
	public String toString() {
		String result = "";
		for(String key : products.keySet()) {
			Item item = products.get(key);
			if(!item.isSoldOut()) {
			result += key + " " + item.getName() + " " + item.getPrice() + "\n";
			} else {
				result += key + " " + item.getName() + " " + item.getPrice() + " SOLD OUT." + "\n";	
			}
		}
		return result;
	}

}
