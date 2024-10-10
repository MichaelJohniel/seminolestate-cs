// Name: Michael Maldonado
// Date: 09-09-2024
package Chapter10;

public class Item {
	
	// Declare variables
	private String name;
	private String barcode;
	private int quantity;
	private int amtSold;
	private double price;
	
	// Constructor
	public Item(String newName, String newBarcode, int newQuantity, int newAmtSold, double newPrice) throws IllegalItemArgumentException {
		setName(newName);
		setBarcode(newBarcode);
		setQuantity(newQuantity);
		setAmtSold(newAmtSold);
		setPrice(newPrice);
	}
	
	// Define getters & setters
	public String getName() {
		return name;
	}
	public void setName(String newName) throws IllegalItemArgumentException {
		if (newName != null && !newName.trim().isEmpty()){
			this.name = newName;
		}
		else 
			throw new IllegalItemArgumentException("Item name cannot be null or empty");
	}
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String newBarcode) throws IllegalItemArgumentException {
		if (newBarcode != null && newBarcode.matches("\\d{12}")) {
			this.barcode = newBarcode;
		}
		else
			throw new IllegalItemArgumentException("Item barcode must be exactly 12 digits");
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int newQuantity) throws IllegalItemArgumentException {
		if (newQuantity >= 0) {
			this.quantity = newQuantity;
		}
		else
			throw new IllegalItemArgumentException("Item quantity must be greater than or equal to zero");
	}
	
	public int getAmtSold() {
		return amtSold;
	}
	public void setAmtSold(int newAmtSold) throws IllegalItemArgumentException {
		if (newAmtSold >= 0) {
			this.amtSold = newAmtSold;
		}
		else
			throw new IllegalItemArgumentException("Item amount sold must be greater than or equal to zero");
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double newPrice) throws IllegalItemArgumentException {
		if (newPrice >= 0.0) {
			this.price = newPrice;
		}
		else
			throw new IllegalItemArgumentException("Item price must be greater than or equal to zero");
	}
	
	@Override
	public String toString() {
		return "[name=" + getName()
		+ ", barcode=" + getBarcode()
		+ ", quantity=" + getQuantity()
		+ ", amtSold=" + getAmtSold()
		+ ", price=" + getPrice()
		+ "]";
	}
}
