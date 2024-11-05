//Name: Michael Maldonado
//Date: 07-01-2024

import bill.*;
import employees.*;
import payable.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ManagePayables {
	
	// Define input options
	private static final int INPUT_ADD_HOURLY_EMPLOYEE = 1;
	private static final int INPUT_ADD_MANAGER = 2;
	private static final int INPUT_ADD_BILL = 3;
	private static final int INPUT_LIST_ALL_PAYABLES = 4;
	private static final int INPUT_EXIT = 5;

	public static void main(String[] args) {
		// Initialize input scanner
		Scanner input = new Scanner(System.in);
		int intUserInput = 0;
		DateTimeFormatter formatted = DateTimeFormatter.ofPattern("M/d/yyyy");
		
		// Initialize ArrayList
		ArrayList<Payable> payableList = new ArrayList<>();
		
		// Menu
		do {
			// Display options & capture user input
			displayMenu();
			try { 
				intUserInput = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException error) {
				System.out.println("You must enter an integer number!");
			}
			
			// Declare Constructor variables
			String firstName;
			String lastName;
			int employeeID = 0;
			
			switch(intUserInput) {
			case INPUT_ADD_HOURLY_EMPLOYEE: // Add Hourly Employee       
				double hoursWorked;
				double hourlyRate;
				
				// Prompt first name
				while (true) {
					System.out.print("Enter the first name: ");
					firstName = input.nextLine(); // Store first name
					
					if (firstName != null && firstName.length() > 0) {
						break;
					} else {
						System.out.println("You must enter a value.");
					}
				}
				
				// Prompt last name
				while (true) {
					System.out.print("Enter the last name: ");
					lastName = input.nextLine(); // Store last name
					
					if (lastName != null && lastName.length() > 0) {
						break;
					} else {
						System.out.println("You must enter a value.");
					}
				}
				
				// Prompt employee ID
				boolean hourlyEmployeeID = false;
				while (!hourlyEmployeeID) {
					System.out.print("Enter employee's ID: ");
					try {
						employeeID = Integer.parseInt(input.nextLine());
						
						if (employeeID > 0) {
							hourlyEmployeeID = true;
						} else {
							System.out.println("Value must be > 0.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only integer numbers");
					}
				}
				
				// Prompt hours worked
				while (true) {
					System.out.print("Enter hours worked: ");
					try {
						hoursWorked = Double.parseDouble(input.nextLine());
						if (hoursWorked > 0) {
							break;
						} else {
							System.out.println("Value must be > 0.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only decimal values");
					}
				}
				
				// Prompt hourly rate
				while (true) {
					System.out.print("Enter hourly rate: ");
					try {
						hourlyRate = Double.parseDouble(input.nextLine());
						if (hourlyRate > 0) {
							break;
						} else {
							System.out.println("Value must be > 0.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only decimal values");
					}
				}
				
				// Instantiate payable
				try {
					payableList.add(new HourlyEmployee( firstName, lastName, employeeID, hoursWorked, hourlyRate));
				} catch (IllegalEmployeeArgumentException error) {
					System.out.println("Couldn't create the Hourly Employee objext but application will continue.");
					System.out.println(error.getMessage() + "\n");
				}
				break;
				
			case INPUT_ADD_MANAGER: // Add manager
				double annualSalary;
				
				// Prompt first name
				while (true) {
					System.out.print("Enter the first name: ");
					firstName = input.nextLine(); // Store first name
					
					if (firstName != null && firstName.length() > 0) {
						break;
					} else {
						System.out.println("You must enter a value.");
					}
				}
				
				// Prompt last name
				while (true) {
					System.out.print("Enter the last name: ");
					lastName = input.nextLine(); // Store last name
					
					if (lastName != null && lastName.length() > 0) {
						break;
					} else {
						System.out.println("You must enter a value.");
					}
				}
				
				// Prompt employee ID
				boolean managerID = false;
				while (!managerID) {
					System.out.print("Enter employee's ID: ");
					try {
						employeeID = Integer.parseInt(input.nextLine());
						
						if (employeeID > 0) {
							managerID = true;
						} else {
							System.out.println("Value must be > 0.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only integer numbers");
					}
				}
				
				// Prompt annual salary
				while (true) {
					System.out.print("Enter manager's annual salary: ");
					try {
						annualSalary = Double.parseDouble(input.nextLine());
						
						if (annualSalary > 0) {
							break;
						} else {
							System.out.println("Value must be > 0.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only decimal values");
					}
				}
				
				// Instantiate payable
				try {
					payableList.add(new Manager( firstName, lastName, employeeID, annualSalary));
				} catch (IllegalEmployeeArgumentException error) {
					System.out.println("Couldn't create the Manager object but application will continue.");
					System.out.println(error.getMessage() + "\n");
				}
				break;
				
			case INPUT_ADD_BILL: // Add bill
				String vendor;
				double amountOwed;
				LocalDate dueDate = null;
				
				// Prompt due date
				while (true) {
					System.out.print("Enter a date (mm/dd/yyyy): ");
					try {
						dueDate = LocalDate.parse(input.nextLine(), formatted); // Store due date
						break;
					} catch (DateTimeParseException error) {
						System.out.println("You Entered an invalid date.\nPlease try again");
					}
				}
				
				// Prompt vendor
				while (true) {
					System.out.print("Enter the vendor name: ");
					vendor = input.nextLine(); // Store vendor
					
					if (vendor != null && vendor.length() > 0) {
						break;
					} else {
						System.out.println("You must enter a value.");
					}
				}
				
				// Prompt amount owed
				while (true) {
					System.out.print("Enter the bill amount: ");
					try {
						amountOwed = Double.parseDouble(input.nextLine());
						
						if (amountOwed > 0) {
							break;
						} else {
							System.out.println("Value must be > 0.");
						}
					} catch (NumberFormatException error) {
						System.out.println("Enter only decimal numbers");
					}
				}
				
				// Instantiate payable
				try {
					payableList.add(new Bill( vendor, amountOwed, dueDate));
				} catch (IllegalBillArgumentException error) {
					System.out.println("Couldn't create the Bill object but application will continue.");
					System.out.println(error.getMessage() + "\n");
				}
				break;
			
			case INPUT_LIST_ALL_PAYABLES: // List payables
				if (!payableList.isEmpty()) {
					for (Payable payable : payableList) {
						System.out.println(payable.toString());
					}
				} else
					System.out.println("There are no items to pay.");
				break;
				
			case INPUT_EXIT: // Exit program
				System.out.println("Thank you for using payable manager.");
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
				+ "\n1. Add Hourly Employee"
				+ "\n2. Add Manager"
				+ "\n3. Add Bill"
				+ "\n4. List All Payables"
				+ "\n5. Exit");
	}
}
