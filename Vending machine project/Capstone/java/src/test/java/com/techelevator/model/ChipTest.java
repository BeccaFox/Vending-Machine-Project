package com.techelevator.model;

import org.junit.Assert;
import org.junit.Test;

public class ChipTest {
	
	@Test
	public void chip_returns_correct_verbiage() {
		Item chip = new Chip("salt n vino", "10.00");
		Assert.assertEquals("Crunch Crunch, yum!", chip.toString());
	}

}
