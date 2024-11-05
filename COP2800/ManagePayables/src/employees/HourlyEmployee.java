//Name: Michael Maldonado
//Date: 07-01-2024

package employees;

/*
* Modify the HourlyEmployee class per the requirements in the assignment.
*/
import payable.*;

public class HourlyEmployee extends Employee implements Payable {
	private double hoursWorked;
	private double hourlyRate;
	
	// Define Constructor
	public HourlyEmployee(String firstName, String lastName, int employeeID, double hoursWorked, double hourlyRate)
			throws IllegalEmployeeArgumentException {
		super(firstName, lastName, employeeID);
		this.setHoursWorked(hoursWorked);
		this.setHourlyRate(hourlyRate);
	}
	
	@Override
	public String toString() {
		String formattedAmount = formatter.format(computeAmountToPay());
		
		return super.toString() + " [hoursWorked=" + hoursWorked + ", hourlyRate=" + hourlyRate + "]"
				+ "\nThe amount to pay is " + formattedAmount;
	}
	
	// Implement computeAmountToPay from Payable
	@Override
	public double computeAmountToPay() {
		return getHoursWorked() * getHourlyRate();
	}
	
	// Define Getters & Setters
	public double getHoursWorked() {
		return hoursWorked;
	}
	public void setHoursWorked(double newHoursWorked) throws IllegalEmployeeArgumentException {
		if (newHoursWorked > 0)
			this.hoursWorked = newHoursWorked;
		else
			throw new IllegalEmployeeArgumentException("Hours worked must be greater than zero.");
	}
	
	public double getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(double newHourlyRate) throws IllegalEmployeeArgumentException {
		if (newHourlyRate > 0)
			this.hourlyRate = newHourlyRate;
		else
			throw new IllegalEmployeeArgumentException("Hourly rate must be greater than zero.");
	}
}
