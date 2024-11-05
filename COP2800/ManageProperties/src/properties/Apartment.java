//Name: Michael Maldonado
//Date: 07-15-2024
package properties;

import java.io.Serializable;

/**
 *
 * @author grantd
 * @version 1 March 2017
 * THIS ASSIGNMENT IS COPYRIGHTED MATERIAL AND THE INTELLECTUAL
 * PROPERTY OF THE AUTHOR. DO NOT POST ANY PART OF THE ASSIGNMENT
 * TO THE INTERNET (OR ANYWHERE ELSE). DO NOT POST ANY PART OF
 * A SOLUTION PROVIDED BY YOUR INSTRUCTOR OR YOUR OWN SOLUTION
 * TO THE INTERNET (OR ANYWHERE ELSE). POSTING OF ANY PART OF
 * THIS MATERIAL IS COPYRIGHT INFRINGEMENT AND THEFT OF INTELLECTUAL
 * PROPERTY. ALL LEGAL AND COLLEGE REMEDIES WILL BE PURSUED TO
 * PROSECUTE THOSE IN VIOLATION.
 */
public class Apartment extends Property implements Serializable {
	private static final long serialVersionUID = 1L;
	private double unitRent;

	public Apartment(int newPropertyID, String newAddress,
			double newNumberOfBaths, int newNumberOfBedrooms, double newUnitRent)
			throws IllegalPropertyArgumentException {
		super(newPropertyID, newAddress, newNumberOfBaths, newNumberOfBedrooms);
		this.setUnitRent(newUnitRent);
	}

	public double getUnitRent() {
		return unitRent;
	}

	public void setUnitRent(double newUnitRent) throws IllegalPropertyArgumentException {
		if (newUnitRent > 0) {
			this.unitRent = newUnitRent;
		} else {
			throw new IllegalPropertyArgumentException("Ret must be > 0. Value received : " + newUnitRent);
		}
	}

	@Override
	public String toString() {
		return super.toString() +  " Apartment [unitRent=" + unitRent + "]";
	}
}
