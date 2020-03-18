package com.techelevator.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Log {
	
	private File logFile;
	
	public Log(String fileName) {
		this.logFile = new File(fileName);		
	}
	
	public void printLine(String line) {
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))) {
			writer.println(line);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}

}
