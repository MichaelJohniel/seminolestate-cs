//Name: Michael Maldonado
//Date: 07-15-2024
package properties;
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
public class IllegalPropertyArgumentException extends Exception {

	private static final long serialVersionUID = 1L;
	public IllegalPropertyArgumentException() {
		super("Illegal value sent to method for property.");
	}
	public  IllegalPropertyArgumentException(String msg) {
		super(msg);
	}
}
