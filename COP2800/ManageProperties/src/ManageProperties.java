//Name: Michael Maldonado
//Date: 07-15-2024

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import properties.*;

public class ManageProperties {
	
	// Define file name
	private static final String FILE_NAME = "properties.dat";
	
	// Define input options
	private static final int INPUT_ADD_HOUSE = 1;
	private static final int INPUT_ADD_APARTMENT = 2;
	private static final int INPUT_LIST_ALL_PROPERTIES_BY_ID = 3;
	private static final int INPUT_LIST_ALL_PROPERTIES_BY_ADDRESS = 4;
	private static final int INPUT_EXIT = 5;

	public static void main(String[] args) {
		
		// Initialize input scanner
		Scanner input = new Scanner(System.in);
		int intUserInput = 0;
				
		// Initialize ArrayList
		File file = new File(FILE_NAME);
		ArrayList<Property> properties = new ArrayList<Property>();
		
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        Property property = (Property) ois.readObject();
                        properties.add(property);
                    } catch (EOFException error) {
                        // End of file reached
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException error) {
                System.out.println("Error reading properties from file: " + error.getMessage());
            }
        }
		
		
		//Menu
		do {
			// Display Options & Capture User Input
			displayMenu();
			try { 
				intUserInput = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException error) {
				System.out.println("You must enter an integer number!");
			}
			
			
			// Initialize Constructor Variables
			int propertyID = 0;
			String propertyAddress;
			double numberOfBaths = 0;
			int numberOfBedrooms = 0;
			double lotSize = 0;
			double value = 0;
			
			switch(intUserInput) {
			case INPUT_ADD_HOUSE:
				// Prompt property ID
				boolean validPID = false;
				while (!validPID) {
					System.out.print("Enter the properties ID: ");
					try {
						propertyID = Integer.parseInt(input.nextLine());
						
						if (propertyID > 0) {
							validPID = true;
						} else {
							System.out.println("Value must be > 0. Try again.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only integer numbers > 0. Try again.");
					}
				}
				
				// Prompt property address
				while (true) {
					System.out.print("Enter the property address: ");
					propertyAddress = input.nextLine();
					
					if (propertyAddress != null && propertyAddress.length() > 0) {
						break;
					} else {
						System.out.println("You must enter a value.");
					}
				}
				
				// Prompt number of baths
				boolean validNumBaths = false;
				while (!validNumBaths) {
					System.out.print("Enter the property number of bathrooms: ");
					try {
						numberOfBaths = Double.parseDouble(input.nextLine());
						
						if (numberOfBaths > 0) {
							validNumBaths = true;
						} else {
							System.out.println("Value must be > 0. Try again.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only decimal numbers. Try again.");
					}
				}
				
				// Prompt number of bedrooms
				boolean validNumRooms = false;
				while (!validNumRooms) {
					System.out.print("Enter the property number of bedrooms: ");
					try {
						numberOfBedrooms = Integer.parseInt(input.nextLine());
						
						if (numberOfBedrooms > 0) {
							validNumRooms = true;
						} else {
							System.out.println("Value must be > 0. Try again.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only integer numbers > 0. Try again.");
					}
				}
				
				// Prompt lot size
				boolean validLotSize = false;
				while (!validLotSize) {
					System.out.print("Enter the lot size: ");
					try {
						lotSize = Double.parseDouble(input.nextLine());
						
						if (lotSize > 0) {
							validLotSize = true;
						} else {
							System.out.println("Value must be > 0. Try again.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only decimal numbers. Try again.");
					}
				}
				
				// Prompt value
				boolean validValue = false;
				while (!validValue) {
					System.out.print("Enter the value of the property in dollars & cents (like 200000.50): ");
					try {
						value = Double.parseDouble(input.nextLine());
						
						if (value > 0) {
							validValue = true;
						} else {
							System.out.println("Value must be > 0. Try again.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only decimal numbers. Try again.");
					}
				}
				
				// Instantiate house
				try {
					properties.add(new House( propertyID, propertyAddress, numberOfBaths, numberOfBedrooms, lotSize, value));
				} catch (IllegalPropertyArgumentException error) {
					System.out.println("Couldn't create the House object but application will continue.");
					System.out.println(error.getMessage() + "\n");
				}
				break;
				
			case INPUT_ADD_APARTMENT:
				// Prompt property ID
				boolean validAPID = false;
				while (!validAPID) {
					System.out.print("Enter the properties ID: ");
					try {
						propertyID = Integer.parseInt(input.nextLine());
						
						if (propertyID > 0) {
							validAPID = true;
						} else {
							System.out.println("Value must be > 0. Try again.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only integer numbers > 0. Try again.");
					}
				}
				
				// Prompt property address
				while (true) {
					System.out.print("Enter the property address: ");
					propertyAddress = input.nextLine();
					
					if (propertyAddress != null && propertyAddress.length() > 0) {
						break;
					} else {
						System.out.println("You must enter a value.");
					}
				}
				
				// Prompt number of baths
				boolean validNumABaths = false;
				while (!validNumABaths) {
					System.out.print("Enter the property number of bathrooms: ");
					try {
						numberOfBaths = Double.parseDouble(input.nextLine());
						
						if (numberOfBaths > 0) {
							validNumABaths = true;
						} else {
							System.out.println("Value must be > 0. Try again.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only decimal numbers. Try again.");
					}
				}
				
				// Prompt number of bedrooms
				boolean validNumARooms = false;
				while (!validNumARooms) {
					System.out.print("Enter the property number of bedrooms: ");
					try {
						numberOfBedrooms = Integer.parseInt(input.nextLine());
						
						if (numberOfBedrooms > 0) {
							validNumARooms = true;
						} else {
							System.out.println("Value must be > 0. Try again.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only integer numbers > 0. Try again.");
					}
				}
				
				// Prompt value
				boolean validRent = false;
				while (!validRent) {
					System.out.print("Enter the rent of the property in dollars & cents (like 2000.50): ");
					try {
						value = Double.parseDouble(input.nextLine());
						
						if (value > 0) {
							validRent = true;
						} else {
							System.out.println("Value must be > 0. Try again.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only decimal numbers. Try again.");
					}
				}
				
				// Instantiate apartment
				try {
					properties.add(new Apartment( propertyID, propertyAddress, numberOfBaths, numberOfBedrooms, value));
				} catch (IllegalPropertyArgumentException error) {
					System.out.println("Couldn't create the Apartment object but application will continue.");
					System.out.println(error.getMessage() + "\n");
				}
				break;
				
			case INPUT_LIST_ALL_PROPERTIES_BY_ID:
				Collections.sort(properties);
				
				if (!properties.isEmpty()) {
					for (Property property : properties) {
						System.out.println(property);
					}
				} else
					System.out.println("There are no properties.");
				break;
				
			case INPUT_LIST_ALL_PROPERTIES_BY_ADDRESS:
				Collections.sort(properties, new PropertyAddressComparator());
				
				if (!properties.isEmpty()) {
					for (Property property : properties) {
						System.out.println(property.toString());
					}
				} else
					System.out.println("There are no properties.");
				break;
			
			case INPUT_EXIT: // Exit program
				if (!properties.isEmpty()) {
					try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			            for (Property property : properties) {
			                oos.writeObject(property);
			            }
			        } catch (IOException error) {
			            System.out.println("Error writing properties to file: " + error.getMessage());
			        }
                }
				System.out.println("Thank you for using the Property Manager.");
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
				+ "\n1. Add house"
				+ "\n2. Add apartment"
				+ "\n3. List all properties by ID"
				+ "\n4. List all properties by address"
				+ "\n5. Exit");
	}

}
