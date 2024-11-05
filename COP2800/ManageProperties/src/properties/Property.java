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
public abstract class Property implements Serializable, Comparable<Property> {
	private static final long serialVersionUID = 1L;
	private int propertyID;
	private String address;
	private double numberOfBaths;
	private int numberOfBedrooms;

	public Property(int newPropertyID, String newAddress,
			double numberOfBaths, int numberOfBedrooms)
			throws IllegalPropertyArgumentException {
		this.setPropertyID( newPropertyID );
		this.setAddress( newAddress );
		this.setNumberOfBaths( numberOfBaths );
		this.setNumberOfBedrooms( numberOfBedrooms );
	}

	public int getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(int newPropertyID) throws IllegalPropertyArgumentException {
		if (newPropertyID > 0) {
			this.propertyID = newPropertyID;
		} else {
			throw new IllegalPropertyArgumentException("Property ID must be > 0. Value: " + newPropertyID);
		}
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String newAddress) throws IllegalPropertyArgumentException {
		if (newAddress != null && newAddress.length() > 0) {
			this.address = newAddress;
		} else {
			throw new IllegalPropertyArgumentException("Address cannot be null or empty");
		}
	}

	public double getNumberOfBaths() {
		return numberOfBaths;
	}

	public void setNumberOfBaths(double newNumberOfBaths) throws IllegalPropertyArgumentException {
		if (newNumberOfBaths > 0) {
			this.numberOfBaths = newNumberOfBaths;
		} else {
			throw new IllegalPropertyArgumentException("Number of bathrooms must be > 0.");
		}
	}

	public int getNumberOfBedrooms() {
		return numberOfBedrooms;
	}

	public void setNumberOfBedrooms(int newNumberOfBedrooms) throws IllegalPropertyArgumentException {
		if (newNumberOfBedrooms > 0) {
			this.numberOfBedrooms = newNumberOfBedrooms;
		} else {
			throw new IllegalPropertyArgumentException("Number of bedrooms must be > 0.");
		}
	}
	
	public int compareTo(Property other) {
        // Compare houses based on their value
        return Double.compare(this.propertyID, other.propertyID);
    }

	@Override
	public String toString() {
		return "Property [propertyID=" + propertyID + ", address=" + address + ", numberOfBaths=" + numberOfBaths
				+ ", numberOfBedrooms=" + numberOfBedrooms + "]";
	}
}

