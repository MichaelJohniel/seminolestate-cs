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
public class House extends Property implements Serializable {
	private static final long serialVersionUID = 1L;
	private double lotSize;
	private double value;

	public House(int newPropertyID, String newAddress,  double newNumberOfBaths,
				int newNumberOfBedrooms, double newLotSize, double newValue)
					throws IllegalPropertyArgumentException {
		super(newPropertyID, newAddress, newNumberOfBaths, newNumberOfBedrooms);
		this.setLotSize( newLotSize );
		this.setValue( newValue );
	}

	public double getLotSize() {
		return lotSize;
	}

	public void setLotSize(double newLotSize) throws IllegalPropertyArgumentException {
		if (newLotSize > 0) {
			this.lotSize = newLotSize;
		} else {
			throw new IllegalPropertyArgumentException("Lot size must be > zero. Value received: " + newLotSize);
		}
	}

	public double getValue() {
		return value;
	}

	public void setValue(double newValue) throws IllegalPropertyArgumentException {
		if (newValue > 0) {
			this.value = newValue;
		} else {
			throw new IllegalPropertyArgumentException("Value must be > zero: " + newValue);
		}
	}

	@Override
	public String toString() {
		return super.toString() + " House [lotSize=" + lotSize + ", value=" + value + "]";
	}
}
