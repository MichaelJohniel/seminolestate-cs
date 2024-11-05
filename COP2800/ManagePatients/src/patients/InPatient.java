//Name: Michael Maldonado
//Date: 06-16-2024
package patients;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class InPatient extends Patient{
	// Declare class variables
	private LocalDate dateAdmitted;
	private LocalDate dateReleased;
	private int roomNumber;

	// Declare constants
	protected static final LocalDate DEFAULT_DATE_ADMITTED = LocalDate.now();
	protected static final LocalDate DEFAULT_DATE_RELEASED = null;
	protected static final int DEFAULT_ROOM_NUMBER = Integer.MAX_VALUE;
	
	// Define constructors
	public InPatient(String newFirstName, String newLastName, int newPatientID,
			LocalDate newDateAdmitted, LocalDate newDateReleased, int newRoomNumber) {
		super(newFirstName, newLastName, newPatientID);
		setDateAdmitted(newDateAdmitted);
		setDateReleased(newDateReleased);
		setRoomNumber(newRoomNumber);
	}
	
	public InPatient(String newFirstName, String newLastName, int newPatientID) {
		this(newFirstName, newLastName, newPatientID, DEFAULT_DATE_ADMITTED, DEFAULT_DATE_RELEASED, DEFAULT_ROOM_NUMBER);
	}
	
	// Calculate length of stay
	@Override
	public int lengthOfStay() {
		long numberOfDays = 0;
		
		// Get days between admission and release
		if (dateReleased != null) {
			numberOfDays = Math.abs(ChronoUnit.DAYS.between(dateAdmitted, dateReleased));
		} else {
			numberOfDays = Math.abs(ChronoUnit.DAYS.between(dateAdmitted, LocalDate.now()));
		}
		
		return (int) numberOfDays; // Cast number of days as integer and return
	}
	
	// Define Getters && Setters
	public LocalDate getDateAdmitted() {
		return dateAdmitted;
	}
	public void setDateAdmitted(LocalDate newDateAdmitted) {
		if (newDateAdmitted != null) {
			dateAdmitted = newDateAdmitted;
		} else {
			System.out.println("Invalid admission date."); // Notify invalid date
		}
	}
	
	public LocalDate getDateReleased() {
		return dateReleased;
	}
	public void setDateReleased(LocalDate newDateReleased) {
		dateReleased = newDateReleased;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int newRoomNumber) {
		if (newRoomNumber > 0) {
			roomNumber = newRoomNumber;
		} else {
			System.out.println("Invalid room number."); // Notify invalid room number
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + " InPatient ["
				+ "dateAdmitted=" + dateAdmitted
				+ ", dateReleased=" + dateReleased
				+ ", roomNumber=" + roomNumber + "]";
	}
}
