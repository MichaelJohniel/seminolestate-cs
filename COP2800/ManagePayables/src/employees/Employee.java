//Name: Michael Maldonado
//Date: 07-01-2024

package employees;
import payable.*;

public abstract class Employee implements Payable {
	// Declare Constructor variables
	private String firstName;
	private String lastName;
	private int iD;
	
	// Define Constructor
	public Employee(String firstName, String lastName, int employeeID)
			throws IllegalEmployeeArgumentException {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setId(employeeID);
	}
	
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", employeeID=" + iD + "]";
	}
	
	// Define Getters & Setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String newFirstName) throws IllegalEmployeeArgumentException {
		if ( newFirstName != null && newFirstName.length() > 0 )
			this.firstName = newFirstName;
		else
			throw new IllegalEmployeeArgumentException("First name is an empty string.");
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String newLastName) throws IllegalEmployeeArgumentException {
		if ( newLastName != null && newLastName.length() > 0 )
			this.lastName = newLastName;
		else
			throw new IllegalEmployeeArgumentException("Last name is an empty string.");
	}
	
	public int getId() {
		return iD;
	}
	public void setId(int newEmployeeID) throws IllegalEmployeeArgumentException {
		if ( newEmployeeID > 0 )
			this.iD = newEmployeeID;
		else
			throw new IllegalEmployeeArgumentException("ID must be greater than zero.");
	}
}
