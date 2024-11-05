//Name: Michael Maldonado
//Date: 06-16-2024
package patients;

public class Patient {
	// Declare class variables
	private String firstName;
	private String lastName;
	private int patientID;
	
	//D efine constants
	protected static final String DEFAULT_FIRST_NAME = "No first name assigned";
	protected static final String DEFAULT_LAST_NAME = "No last name assigned";
	protected static final int DEFAULT_PATIENT_ID = Integer.MAX_VALUE;
	
	// Define Constructors
	public Patient(String newFirstName, String newLastName, int newPatientID) {
		setFirstName(newFirstName);
		setLastName(newLastName);
		setPatientID(newPatientID);
	}
	
	public Patient() {
		this(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_PATIENT_ID);
	}

	// Return length of stay
	public int lengthOfStay() {
		return 0;
	}
	
	// Define Getters & Setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String newFirstName) {
		if (newFirstName != null && newFirstName.length() > 0) {
			firstName = newFirstName;
		} else {
			System.out.println("Invalid first name entered."); // Notify invalid name
		}
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String newLastName) {
		if (newLastName != null && newLastName.length() > 0){
			lastName = newLastName;
		} else {
			System.out.println("Invalid last name entered."); // Notify invalid name
		}
	}
	
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int newPatientID) {
		if (newPatientID > 0) {
			patientID = newPatientID;
		} else {
			System.out.println("Invalid ID entered."); // Notify invalid ID
		}
	}
	
	@Override
	public String toString() {
		return "Patient [firstName=" + firstName
				+ ", lastName=" + lastName
				+ ", patientID=" + patientID +"]";
	}
}
