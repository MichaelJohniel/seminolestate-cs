//Name: Michael Maldonado
//Date: 07-01-2024

package employees;

/*
* Modify the Manager class per the requirements in the assignment.
*/
import payable.*;

public class Manager extends Employee implements Payable {
	private double annualSalary;
	
	// Define Constructor
	public Manager(String firstName, String lastName, int employeeID, double annualSalary)
			throws IllegalEmployeeArgumentException {
		super(firstName, lastName, employeeID);
		this.setAnnualSalary(annualSalary);
	}
	
	@Override
	public String toString() {
		String formattedAmount = formatter.format(computeAmountToPay());
		
		return super.toString() + " [annualSalary=" + annualSalary + "]"
				+ "\nThe amount to pay is " + formattedAmount;
	}
	
	// Implement computeAmountToPay from Payable
	@Override
	public double computeAmountToPay() {
		return getAnnualSalary() / 12;
	}
	
	// define Getters & Setters
	public double getAnnualSalary() {
		return annualSalary;
	}
	public void setAnnualSalary(double newAnnualSalary) throws IllegalEmployeeArgumentException {
		if (newAnnualSalary > 0)
			this.annualSalary = newAnnualSalary;
		else
			throw new IllegalEmployeeArgumentException("Annual salary must be grester than zero");
	}
}