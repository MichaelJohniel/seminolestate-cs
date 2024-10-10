// Name: Michael Maldonado
// Date: 09-09-2024
package Chapter10;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ManageSales {
	
	// Define file name
	private static final String FILE_NAME = "inventory.txt";
	
	// Define input options
	private static final int MENU_OPTION_ADD_INVENTORY = 1;
	private static final int MENU_OPTION_DEL_INVENTORY = 2;
	private static final int MENU_OPTION_EDIT_INVENTORY = 3;
	private static final int MENU_OPTION_LIST_INVENTORY = 4;
	private static final int MENU_OPTION_FIND_INVENTORY = 5;
	private static final int MENU_OPTION_EXIT = 6;
	private static final int MENU_OPTION_SEARCH_BARCODE = 1;
	private static final int MENU_OPTION_SEARCH_NAME = 2;
	
	public static void main(String[] args) throws IllegalItemArgumentException {
		// Initialize input scanner
		Scanner input = new Scanner(System.in);
		int userInput = 0;
		
		// Initialize inventory arraylist
		ArrayList<Item> inventoryList = new ArrayList<>();
		
		// Load iventory file if it exists
		File inventory = new File(FILE_NAME);
		
		if (inventory.exists()) {
			try (Scanner reader = new Scanner(inventory)) {
				while (reader.hasNextLine()) {
					String itemName = reader.nextLine();
					String itemBarcode = reader.nextLine();
					int itemQuantity = Integer.parseInt(reader.nextLine());
					int itemAmtSold = Integer.parseInt(reader.nextLine());
					double itemPrice = Double.parseDouble(reader.nextLine());
					
					// Create inventory item
					inventoryList.add(new Item( itemName, itemBarcode, itemQuantity, itemAmtSold, itemPrice));
				}
			} catch (FileNotFoundException | IllegalItemArgumentException error) {
				System.out.println("Error while loading inventory...");
				System.out.println(error.getMessage() + "\n");
			}
		}
		
		// Menu 
		do {
			// Display menu options & capture user input
			displayMenu();
			try {
				userInput = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException error) {
				System.out.println("You must enter an integer!");
			}
			
			// Declare constructor variable
			String itemName;
			String itemBarcode;
			int itemQuantity;
			int itemAmtSold;
			double itemPrice;
			
			switch(userInput) {
			case MENU_OPTION_ADD_INVENTORY: // Add item
				System.out.print("Enter the item name: ");
				itemName = input.nextLine(); // Save item name
				
				System.out.print("Enter the 12-digit item barcode: ");
				itemBarcode = input.nextLine().strip(); // Save item barcode
				
				System.out.print("Enter the item quantity: ");
				try {
					itemQuantity = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException error) {
					System.out.println("Enter only Integers for the quatity.\nPlease try again.");
					break;
				}
				
				System.out.print("Enter the item amount sold: ");
				try {
					itemAmtSold = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException error) {
					System.out.println("Enter only Integers for the amount sold.\nPlease try again.");
					break;
				}
				
				System.out.print("Enter the item price: ");
				try {
					itemPrice = Double.parseDouble(input.nextLine());
				} catch (NumberFormatException error) {
					System.out.println("Enter only decimal numbers for the price.\nPlease try again.");
					break;
				}
				
				// Instantiate item
				try {
					inventoryList.add( new Item(itemName, itemBarcode, itemQuantity, itemAmtSold, itemPrice));
				} catch (IllegalItemArgumentException error) {
					System.out.println("The item could not be created but the application will continue.");
					System.out.println(error.getMessage() + "\n");
				}
				break;
			
			case MENU_OPTION_DEL_INVENTORY: // Delete item from Inventory
				System.out.println("Enter the barcode of the item to delete:");
			    String deleteBarcode = input.nextLine().strip();

			    // Search for the item by barcode and remove it
			    boolean itemRemoved = false;
			    for (int i = 0; i < inventoryList.size(); i++) {
			        if (inventoryList.get(i).getBarcode().equals(deleteBarcode)) {
			            inventoryList.remove(i);
			            itemRemoved = true;
			            System.out.println("Item successfully removed.");
			            break;
			        }
			    }

			    if (!itemRemoved) {
			        System.out.println("Item with barcode " + deleteBarcode + " not found.");
			    }
			    break;
				
			case MENU_OPTION_EDIT_INVENTORY: // Edit item in Inventory	
				System.out.println("Enter the barcode of the item to edit: ");
			    String editBarcode = input.nextLine().strip();

			    // Search for the item by barcode
			    Item itemToEdit = null;
			    for (Item item : inventoryList) {
			        if (item.getBarcode().equals(editBarcode)) {
			            itemToEdit = item;
			            break;
			        }
			    }

			    if (itemToEdit != null) {
			        // Allow the user to choose which property to edit
			        System.out.println("Select the property to edit: "
			            + "\n1. Name"
			            + "\n2. Quantity"
			            + "\n3. Amount Sold"
			            + "\n4. Price");
			        
			        int editChoice = 0;
			        
			        // Validate input as int
			        try {
						editChoice = Integer.parseInt(input.nextLine());
					} catch (NumberFormatException error) {
						System.out.println("You must enter an integer!");
					}

			        switch (editChoice) {
			            case 1:
			                System.out.print("Enter the new name: ");
			                itemToEdit.setName(input.nextLine());
			                System.out.println("Item name updated.");
			                break;
			            case 2:
			                System.out.print("Enter the new quantity: ");
			                itemToEdit.setQuantity(Integer.parseInt(input.nextLine()));
			                System.out.println("Item quantity updated.");
			                break;
			            case 3:
			                System.out.print("Enter the new amount sold: ");
			                itemToEdit.setAmtSold(Integer.parseInt(input.nextLine()));
			                System.out.println("Item amount sold updated.");
			                break;
			            case 4:
			                System.out.print("Enter the new price: ");
			                itemToEdit.setPrice(Double.parseDouble(input.nextLine()));
			                System.out.println("Item price updated.");
			                break;
			            default:
			                System.out.println("Invalid option selected.");
			        }
			    } else {
			        System.out.println("Item with barcode " + editBarcode + " not found.");
			    }
			    break;
				
			case MENU_OPTION_LIST_INVENTORY: // Display Inventory
				if (!inventoryList.isEmpty()) {
					for (Item item : inventoryList) {
						System.out.println(item.toString());
					}
				} else
					System.out.println("There are no items in inventory.");
				break;
			
			case MENU_OPTION_FIND_INVENTORY:
				System.out.println("Search by: "
				        + "\n1. Barcode"
				        + "\n2. Name");
				    
				    int searchOption = 0;
				    
				    // Validate input as int
			        try {
						searchOption = Integer.parseInt(input.nextLine());
					} catch (NumberFormatException error) {
						System.out.println("You must enter an integer!");
					}

				    switch (searchOption) {
				        case MENU_OPTION_SEARCH_BARCODE: // Search by barcode
				            System.out.print("Enter the barcode to search: ");
				            String searchBarcode = input.nextLine().strip();

				            Item foundItemByBarcode = null;
				            for (Item item : inventoryList) {
				                if (item.getBarcode().equals(searchBarcode)) {
				                    foundItemByBarcode = item;
				                    break;
				                }
				            }

				            if (foundItemByBarcode != null) {
				                System.out.println("Item found: " + foundItemByBarcode.toString());
				            } else {
				                System.out.println("Item with barcode " + searchBarcode + " not found.");
				            }
				            break;

				        case MENU_OPTION_SEARCH_NAME: // Search by partial name
				            System.out.print("Enter the name to search: ");
				            String partialName = input.nextLine().strip().toLowerCase();

				            boolean itemsFound = false;
				            for (Item item : inventoryList) {
				                if (item.getName().toLowerCase().contains(partialName)) {
				                    System.out.println("Item found: " + item.toString());
				                    itemsFound = true;
				                }
				            }

				            if (!itemsFound) {
				                System.out.println("No items found with the name containing '" + partialName + "'.");
				            }
				            break;

				        default:
				            System.out.println("Invalid option. Please choose 1 or 2.");
				    }
				    break;
				
			case MENU_OPTION_EXIT: // Save inventory to file on exit
				if (!inventoryList.isEmpty()) {
					// Overwrite inventory
					File newInventory = new File(FILE_NAME);
					
					try (PrintWriter writer = new PrintWriter(newInventory)) {
						for (Item item : inventoryList) {
							writer.println(item.getName());
							writer.println(item.getBarcode());
							writer.println(item.getQuantity());
							writer.println(item.getAmtSold());
							writer.println(item.getPrice());
						}
					} catch (IOException error) {
						System.out.println("Error while saving inventory.");
						error.printStackTrace();
					}
				}
				System.out.println("Thank you for using the Sales Inventory Manager.\nHave a nice day! :)");
				break;
				
			default: // Invalid input	
				System.out.println("Invalid menu option entered. Please try again.");
			}
		} while (userInput != MENU_OPTION_EXIT);
		
		// Close scanner
		input.close();
	}
	
	private static void displayMenu() {
		// Print menu Options
		System.out.println("\nEnter your choice: "
				+ "\n1. Add item to inventory"
				+ "\n2. Delete item from inventory"
				+ "\n3. Edit item in inventory"
				+ "\n4. List inventory"
				+ "\n5. Search inventory"
				+ "\n6. Exit\n"
				);
	}
}
