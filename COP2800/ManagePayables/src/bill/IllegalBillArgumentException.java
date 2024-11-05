package bill;

public class IllegalBillArgumentException extends Exception{
	private static final long serialVersionUID = 1L;

	public IllegalBillArgumentException() {
		super("Illegal bill argument");
	}
	
	public IllegalBillArgumentException(String message) {
		super(message);
	}
}
