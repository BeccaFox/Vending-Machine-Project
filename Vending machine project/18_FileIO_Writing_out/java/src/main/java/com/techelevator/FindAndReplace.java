package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FindAndReplace {
		
		private static Scanner userInput = new Scanner(System.in);
		
		public static void main(String[] args) {
			
			System.out.print("Enter the the word to be replaced: ");
			String oldWord = userInput.nextLine();
			
			System.out.print("Enter the the new word: ");
			String newWord = userInput.nextLine();
			
			System.out.print("Enter the path of the source file: ");
			String sourcePath = userInput.nextLine();
			File sourceFile = new File(sourcePath);
			
			if (sourceFile.exists() && sourceFile.isFile()) {
				System.out.print("Enter the path to the destination file: ");
				String destPath = userInput.nextLine();
				File destFile = new File(destPath);
				
				
					try (Scanner sourceScanner = new Scanner(sourceFile)) {
						if(destFile.exists() == true) {
							destFile.delete();
						}
						if (destFile.createNewFile()) {
							try (PrintWriter destWriter = new PrintWriter(destFile)) {
								while (sourceScanner.hasNextLine()) {
									String line = sourceScanner.nextLine();
									line = line.replace(oldWord, newWord);
									destWriter.println(line);
								}
							}
							System.out.println("replacement finished.");
						} else {
							throw new IOException();
						}
					} catch (FileNotFoundException e) {
						System.out.println("File was not found: " + e.getMessage());
					} catch (IOException e) {
						System.out.println("Couldn't create the new file!");
					}
				
			} else {
				System.out.println("That's not an existing file!");
			}

		}


	}
