//Name: Michael Maldonado
//Date: 07-01-2024

package bill;

/*
* Modify the Bill class per the requirements in the assignment.
* - implement (code) the computeAmountToPay method.
*/
import payable.*;
import java.time.LocalDate;

public class Bill implements Payable {
	// Declare Constructor variables
	private String vendorName;
	private double amountOwed;
	private LocalDate dueDate;
	
	// Define Constructor
	public Bill(String vendor, double amount, LocalDate dueDate)
			throws IllegalBillArgumentException {
		this.setVendor(vendor);
		this.setAmountOwed(amount);
		this.setDueDate(dueDate);
	}
	
	@Override
	public String toString() {
		String formattedAmount = formatter.format(computeAmountToPay());
		
		return "Bill [vendor=" + vendorName + ", amount=" + amountOwed + ", dueDate=" + dueDate + "]"
				+ "\nThe amount to pay is " + formattedAmount;
	}
	
	// Implement computeAmountToPay from Payable
	@Override
	public double computeAmountToPay() {
		return getAmountOwed();
	}
	
	// Define Getters & Setters
	public String getVendor() {
		return vendorName;
	}
	public void setVendor(String newVendor) throws IllegalBillArgumentException {
		if (newVendor != null && newVendor.length() > 0)
			this.vendorName = newVendor;
		else
			throw new IllegalBillArgumentException("Vendor name is an empty string.");
	}
	
	public double getAmountOwed() {
		return amountOwed;
	}
	public void setAmountOwed(double newAmount) throws IllegalBillArgumentException {
		if (newAmount > 0)
			this.amountOwed = newAmount;
		else
			throw new IllegalBillArgumentException("Amount must be greater than zero.");
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate newDueDate) throws IllegalBillArgumentException {
		if (newDueDate != null)
			this.dueDate = newDueDate;
		else
			throw new IllegalBillArgumentException("Invalid due date.");
	}
}
