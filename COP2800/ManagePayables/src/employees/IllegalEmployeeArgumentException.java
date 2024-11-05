package employees;

public class IllegalEmployeeArgumentException extends Exception{
	private static final long serialVersionUID = 1L;

	public IllegalEmployeeArgumentException() {
		super("Illegal employee argument");
	}
	
	public IllegalEmployeeArgumentException(String message) {
		super(message);
	}
}
