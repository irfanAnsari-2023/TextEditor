package com.textEditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Irfan
 *
 */
public class TextEditor {
		
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		
		while(true) {
			System.out.println("Select an option: \n 1. Create a new File\n 2. Open an existing File \n 3. Delete a file \n 4. Exit1");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			case 1:
				createFile(scanner);
				break;
			case 2:
				openFile(scanner);
				break;
			case 3:
				deleteFile(scanner);
				break;
			case 4:
				running = false;
				break;
			default: 
				System.out.println("Invalid choice. Please try again.");
				
			}
		}
//		scanner.close();
	}
	
	private static void deleteFile(Scanner scanner) {
		System.out.println("Enter the file name to delete: ");
		String fileName = scanner.nextLine();
		
		File fileToDelete = new File(fileName);
		try {
			if(fileToDelete.exists()) {
				boolean isDeleted = fileToDelete.delete();
				
				if(isDeleted) {
					System.out.println("File deletd successfully!");
					
				}
				else {
					System.out.println("Unable to delete the file.");
				}
				
			}
			else {
				System.out.println("File not found.");
			}
		}
		catch(SecurityException e){
			System.out.println("Error deleting file : " + e.getMessage());
		}
	}

	private static void createFile(Scanner scanner) {
		System.out.println("Enter the file name: ");
		String fileName = scanner.nextLine();
		
		File file = new File(fileName);
		if(file.exists()) {
			System.out.println("File already exists.");
			return;
			
		}
		try {
			if(file.createNewFile()) {
				System.out.println("File created :" + file.getName());
			}
			else {
				System.out.println("File could not be created.");
			}
		}
		catch(IOException e) {
			System.out.println("An error occurred while creating the file.");
			e.printStackTrace();
		}
		
		editFile(file, scanner);
		
	}
	


	private static void openFile(Scanner scanner) {
		System.out.println("Enter the file name");
		String fileName = scanner.nextLine();
		
		File file = new File(fileName);
		
		if(!file.exists()) {
			System.out.println("File does not exist.");
			return;
		}
		
		editFile(file,scanner);
		
	}

	private static void editFile(File file, Scanner scanner) {
		System.out.println("Editing file: " + file.getName());
		System.out.println("Type your text (type 'quit' to save and exist) : ");
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))){
			String line;
			
			do {
				line = scanner.nextLine();
				
				if(!line.equals("quit")){
					writer.write(line);
					writer.newLine();
				}
			}while(!line.equals("quit"));
			System.out.println("File saved.");
		}
			
			catch(IOException e) {
				System.out.println("An error occurred while writing to the file.");
				e.printStackTrace();
			}
		}
}
		
	

	
