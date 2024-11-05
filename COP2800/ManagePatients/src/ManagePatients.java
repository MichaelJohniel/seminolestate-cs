//Name: Michael Maldonado
//Date: 06-16-2024

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import patients.*;

public class ManagePatients {
	
	// Define input options
	private static final int INPUT_ADD_INPATIENT = 1;
	private static final int INPUT_ADD_OUTPATIENT = 2;
	private static final int INPUT_LIST_PATIENTS = 3;
	private static final int INPUT_EXIT = 4;

	public static void main(String[] args) {
		// Initialize input 
		Scanner input = new Scanner(System.in);
		int intUserInput = 0;
		DateTimeFormatter formatted = DateTimeFormatter.ofPattern("M/d/yyyy");
		
		// Initialize Patient ArrayList
		ArrayList<Patient> patientList = new ArrayList<>();
		
		// Menu
		do {
			// Display menu options & capture user input
			displayMenu();
			intUserInput = Integer.parseInt(input.nextLine());
			
			// Declare default patient constructor variables
			String firstName;
			String lastName;
			int patientID;
			
			switch (intUserInput) {
				case INPUT_ADD_INPATIENT: // Add Inpatient info
					System.out.println("Enter the first name: ");
					firstName = input.nextLine(); // Store first name
					
					System.out.println("Enter the last name: ");
					lastName = input.nextLine(); // Store last name
					
					System.out.println("Enter the patient ID: ");
					patientID = Integer.parseInt(input.nextLine()); // Store patient ID
					
					System.out.println("Enter the date admitted (mm/dd/yyyy): ");
					LocalDate dateAdmitted = LocalDate.parse(input.nextLine(), formatted); // Store date admitted
					
					LocalDate dateReleased = null; // Default date released
					
					System.out.println("Has the patient been released? (y/n): ");
					String strUserInput = input.nextLine();
					if (strUserInput.equals("y")) {
						System.out.println("Enter the date released (mm/dd/yyyy): ");
						dateReleased = LocalDate.parse(input.nextLine(), formatted); // Store date released
					}
					
					System.out.println("Enter the room number: ");
					int roomNumber = Integer.parseInt(input.nextLine()); // Store room number
	
					// Instantiate and add InPatient to list
					patientList.add(new InPatient(firstName, lastName, patientID, dateAdmitted, dateReleased, roomNumber));
					break;
					
				case INPUT_ADD_OUTPATIENT: // Add Outpatient info
					System.out.println("Enter the first name: ");
					firstName = input.nextLine(); // Store first name
					
					System.out.println("Enter the last name: ");
					lastName = input.nextLine(); // Store last name
					
					System.out.println("Enter the patient ID: ");
					patientID = Integer.parseInt(input.nextLine()); // Store patient ID
					
					System.out.println("Enter the clinic number: ");
					int clinicNumber = Integer.parseInt(input.nextLine()); // Store clinic number
					
					System.out.println("Enter the procedure date (mm/dd/yyyy): ");
					LocalDate procedureDate = LocalDate.parse(input.nextLine(), formatted); // Store procedure date
					
					System.out.println("Enter the visit number: ");
					int visitNumber = Integer.parseInt(input.nextLine()); // Store visit number
					
					// Instantiate and add OutPatient to list
					patientList.add(new OutPatient(firstName, lastName, patientID, visitNumber, clinicNumber, procedureDate));
					break;
				
				case INPUT_LIST_PATIENTS: // List all patients and their info
					if (patientList.size() > 0) {
						for (Patient patient: patientList) {
							System.out.println(patient.toString());
							System.out.println("Length of stay is " + patient.lengthOfStay() + " day(s).");
						}
					} else
						System.out.println("Currently no patients are on record. ");
					break;
			}
			
		} while (intUserInput != INPUT_EXIT);
		
		// Print exit message
		System.out.println("Thank you for using patient management!");
		
		// Close Scanner
		input.close();
	}

	private static void displayMenu() {
		// Print Menu Options
		System.out.println("Enter your choice: "
				+ "\n1. Add Inpatient"
				+ "\n2. Add Outpatient"
				+ "\n3. List all patients"
				+ "\n4. Exit");
	}

}
