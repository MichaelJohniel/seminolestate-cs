//Name: Michael Maldonado
//Date: 06-16-2024
package patients;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OutPatient extends Patient {
	// Declare class variables
	private int visitNumber;
	private int clinicNumber;
	private LocalDate procedureDate;
	
	// Declare constants
	protected static final LocalDate DEFAULT_PROCEDURE_DATE = LocalDate.now();
	protected static final int DEFAULT_VISIT_NUMBER = Integer.MAX_VALUE;
	protected static final int DEFAULT_CLINIC_NUMBER = Integer.MAX_VALUE;
	
	// Constructors
	public OutPatient(String newFirstName, String newLastName, int newPatientID,
			int newVisitNumber, int newClinicNumber, LocalDate newProcedureDate) {
		super(newFirstName, newLastName, newPatientID);
		setVisitNumber(newVisitNumber);
		setClinicNumber(newClinicNumber);
		setProcedureDate(newProcedureDate);
	}
	
	public OutPatient(String newFirstName, String newLastName, int newPatientID) {
		this(newFirstName, newLastName, newPatientID, DEFAULT_VISIT_NUMBER, DEFAULT_CLINIC_NUMBER, DEFAULT_PROCEDURE_DATE);
	}
	
	// Return length of stay
	@Override
	public int lengthOfStay() {
		long numberOfDays = 0;
		
		// Get days between procedure and present
		numberOfDays = ChronoUnit.DAYS.between(procedureDate, LocalDate.now());
		
		return (int) numberOfDays; // Cast number of days as integer and return
	}
	
	// Define Getters & Setters
	public int getVisitNumber() {
		return visitNumber;
	}
	public void setVisitNumber(int newVisitNumber) {
		if (newVisitNumber > 0) {
			visitNumber = newVisitNumber;
		} else {
			System.out.println("Invalid visit number"); // Notify invalid number
		}
	}
	
	public int getClinicNumber() {
		return clinicNumber;
	}
	public void setClinicNumber(int newClinicNumber) {
		if (newClinicNumber > 0) {
			clinicNumber = newClinicNumber;
		} else {
			System.out.println("Invalid clinic number"); // Notify invalid number
		}
	}
	
	public LocalDate getProcedureDate() {
		return procedureDate;
	}
	public void setProcedureDate(LocalDate newProcedureDate) {
		if (newProcedureDate != null) {
			procedureDate = newProcedureDate;
		} else {
			System.out.println("Invalid procedure date"); // Notify invalid date
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + " OutPatient ["
				+ "visitNumber=" + visitNumber
				+ ", clinicNumber=" + clinicNumber
				+ ", procedureDate=" + procedureDate + "]";
	}
}
