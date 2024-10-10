// Name: Michael Maldonado
// Date: 09-09-2024
package Chapter10;

public class IllegalItemArgumentException extends Exception{
	private static final long serialVersionUID = 1L;

	public IllegalItemArgumentException() {
		super("Illegal item argument");
	}
	
	public IllegalItemArgumentException(String message) {
		super(message);
	}

}
