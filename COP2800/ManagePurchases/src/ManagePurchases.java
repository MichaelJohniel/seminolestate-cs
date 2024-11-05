//Name: Michael Maldonado
//Date: 06/24/2024

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ManagePurchases {

	// Define file name
	private static final String FILE_NAME = "purchases.txt";
	
	// Define input options
	private static final int INPUT_ADD_PURCHASE = 1;
	private static final int INPUT_DISPLAY_PURCHASES = 2;
	private static final int INPUT_EXIT = 3;
		
	public static void main(String[] args) {
		// Initialize input scanner
		Scanner input = new Scanner(System.in);
		int intUserInput = 0;
		DateTimeFormatter formatted = DateTimeFormatter.ofPattern("M/d/yyyy");
		
		// Initialize Purchase ArrayList
		ArrayList<Purchase> purchaseList = new ArrayList<>();
		
		// Load purchase file if exists
		File purchases = new File(FILE_NAME);
		
		if (purchases.exists()) {
			try (Scanner reader = new Scanner(purchases)) {
				while (reader.hasNextLine()) {
					String productName = reader.nextLine();
                    String storeName = reader.nextLine();
                    LocalDate purchaseDate = LocalDate.parse(reader.nextLine(), formatted);
                    double cost = Double.parseDouble(reader.nextLine());
					
					// Instantiate
					purchaseList.add(new Purchase(productName, storeName, purchaseDate, cost));
				}
			} catch (FileNotFoundException | IllegalPurchaseArgumentException error) {
				System.out.println("Error while loading purchases.");
				System.out.println(error.getMessage() + "\n");
			}
		}
		
		// Menu
		do {
			// Display options & capture user input
			displayMenu();
			try {
				intUserInput = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException error) {
				System.out.println("You must enter an integer number!");
			}
			
			// Declare constructor variables
			String productName;
			String storeName;
			double cost;
			
			switch(intUserInput) {
			case INPUT_ADD_PURCHASE: // Add purchase
				System.out.print("Enter the product name: ");
				productName = input.nextLine(); // Save product name
				
				System.out.print("Enter the store name: ");
				storeName = input.nextLine(); // Save store name
				
				System.out.print("Enter a date (mm/dd/yyyy): ");
				LocalDate datePurchased = null;
				try {
					datePurchased = LocalDate.parse(input.nextLine(), formatted); // Save date submitted
				} catch (DateTimeParseException error) {
					System.out.println("You Entered an invalid date.\nPlease try again");
					break;
				}
				
				System.out.print("Enter the cost: ");
				try {
					cost = Double.parseDouble(input.nextLine());
				} catch (NumberFormatException error) {
					System.out.println("Enter only decimal numbers for the cost.\nPlease try again");
					break;
				}
				
				// Instantiate purchase
				try {
					purchaseList.add(new Purchase( productName, storeName, datePurchased, cost));
				} catch (IllegalPurchaseArgumentException error) {
					System.out.println("Couldn't create the purchase object but application will continue.");
					System.out.println(error.getMessage() + "\n");
				}
				break;
			
			case INPUT_DISPLAY_PURCHASES: // Display
				if (!purchaseList.isEmpty()) {
					for (Purchase purchase : purchaseList) {
						System.out.println(purchase.toString());
					} 
				} else
					System.out.println("There are no purchases.");
				break;
			
			case INPUT_EXIT: // Save purchases to file on exit
				if (!purchaseList.isEmpty()) {
					// Overwrite purchases
					File newPurchases = new File(FILE_NAME);
					
					try (PrintWriter writer = new PrintWriter(newPurchases)) {
						for (Purchase purchase : purchaseList) {
							writer.println(purchase.getProductName());
					        writer.println(purchase.getStoreName());
					        writer.println(purchase.getPurchaseDate().format(formatted));
					        writer.println(purchase.getCost());
						}
					} catch (IOException error) {
						System.out.println("Error while saving purchases.");
						error.printStackTrace();
					}
				}
				System.out.println("Thank you for using the purchase manager.");
				break;
				
			default: // Invalid input
				System.out.println("Invalid menu option entered. Try again.");
			}
			
		} while (intUserInput != INPUT_EXIT);
		
		
		// Close Scanner
		input.close();
		}

	private static void displayMenu() {
		// Print Menu Options
		System.out.println("Enter your choice: "
				+ "\n1. Add Purchase"
				+ "\n2. Display All Purchases"
				+ "\n3. Exit");
	}

}
