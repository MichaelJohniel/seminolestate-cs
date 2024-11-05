//Name: Michael Maldonado
//Date: 06/24/2024

public class IllegalPurchaseArgumentException extends Exception{
	private static final long serialVersionUID = 1L; // I don't understand this but eclipse had me generate it

	public IllegalPurchaseArgumentException(){
		super("Illegal purchase argument");
	}
	
	public IllegalPurchaseArgumentException(String message) {
		super(message);
	}
}