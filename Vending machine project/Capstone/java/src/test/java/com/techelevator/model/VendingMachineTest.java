package com.techelevator.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
	
	File file;
	PrintWriter writer;
	VendingMachine vm;
	@Before
	public void setUp() {
		file = new File("test-input.txt");
		try{
			writer = new PrintWriter(file);
			writer.println("A1|Potato Crisps|3.05|Chip");
			writer.println("A2|cheesey Crisps|2.05|Chip");
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file.");
		}
		 vm = new VendingMachine(file);
	}
	@After
	public void tearDown() {
		file.delete();
	}
	
	@Test
	public void vending_machine_imports_File_works_as_expected() {
		
		Item item = vm.getItem("A1");
		Assert.assertEquals("Potato Crisps",item.getName());
		
	}
	
	@Test
	public void calls_Butterfingers_When_Given_Incorrect_Slot_Number() {
		Assert.assertEquals("Invalid slot number, Butterfingers!", vm.purchaseItem("sdosa"));
	}
	@Test
	public void purchase_Item_Decrements_Item_Count_And_Increments_Items_Sold() {
		vm.addToBalance(new BigDecimal("20"));
		vm.purchaseItem("A1");
		Assert.assertEquals(4, vm.getItem("A1").getItemCount());
		Assert.assertEquals(1, vm.getItem("A1").getItemsSold());
	}
	@Test
	public void returns_Sold_Out_When_Sold_Out() {
		vm.addToBalance(new BigDecimal("20"));
		for(int i = 0; i < 5; i++ ) {
			vm.purchaseItem("A1");
		}
		Assert.assertEquals("Sorry, sold out, choose something else.", vm.purchaseItem("A1"));
	}
	
	@Test      ///change name before putting on gitHub
	public void does_Not_Let_Poor_People_Buy() {
		Assert.assertEquals("Please enter more money", vm.purchaseItem("A1"));
		vm.addToBalance(new BigDecimal("1"));
		Assert.assertEquals("Please enter more money", vm.purchaseItem("A1"));
	}
	
	
}