package com.techelevator;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

public class WordCount {

	public static void main(String[] args) {
		
		 int wordCount = 0;
		 int sentenceCount = 0;
		
		File myFile = new File("alices_adventures_in_wonderland.txt");
		
		try(Scanner scanner = new Scanner(myFile)){
			while(scanner.hasNextLine()) {
				String currentLineString = scanner.nextLine();
				currentLineString = currentLineString.trim();
				if(!currentLineString.isEmpty()) {
				//String[] wordArray = currentLineString.split(" +|, +|\\. +|\\? +|: +| +\\t +|\\s");
				String[] wordArray = currentLineString.split(" +");
				wordCount += wordArray.length;
				
				/*
				 * Sentence Count
				 */
			
				if(currentLineString.contains(".")||currentLineString.contains("?")||currentLineString.contains("!") ) {
				//sentenceArray = currentLineString.split(".+ +|!+ +|\\?+ +");
					sentenceCount ++;
				}
			}
			}
		}catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
		
		System.out.println("word count: " + wordCount);
		System.out.println("sentence count: " + sentenceCount);
		
		
	}
	
	

}
