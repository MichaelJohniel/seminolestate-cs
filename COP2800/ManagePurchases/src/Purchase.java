//Name: Michael Maldonado
//Date: 06/24/2024

import java.time.LocalDate;

public class Purchase {
	
	// Declare variables
	private String productName;
	private String storeName;
	private LocalDate purchaseDate;
	private double cost;
	
	// Constructor
	public Purchase(String newProduct, String newStore, LocalDate newDate,
			double newCost) throws IllegalPurchaseArgumentException {
		setProductName(newProduct);
		setStoreName(newStore);
		setPurchaseDate(newDate);
		setCost(newCost);
	}
	
	// Define getters & setters
	public String getProductName() {
		return productName;
	}
	public void setProductName(String newProduct) throws IllegalPurchaseArgumentException {
		if (newProduct != null && newProduct.length() > 0)
			productName = newProduct;
		else
			throw new IllegalPurchaseArgumentException("Product name is an empty string.");
	}
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String newStore) throws IllegalPurchaseArgumentException {
		if (newStore != null && newStore.length() > 0)
			storeName = newStore;
		else
			throw new IllegalPurchaseArgumentException("Store name is an empty string.");
	}
	
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate newDate) throws IllegalPurchaseArgumentException {
		if (newDate != null && newDate.isBefore(LocalDate.now()))
			purchaseDate = newDate;
		else
			throw new IllegalPurchaseArgumentException("Purchase date cannot be in the future.");
	}
	
	public double getCost() {
		return cost;
	}
	public void setCost(double newCost) throws IllegalPurchaseArgumentException {
		if (newCost >= 0)
			cost = newCost;
		else
			throw new IllegalPurchaseArgumentException("Cost is = " + newCost + "; Cost must be >= 0.");
	}
	
	@Override
	public String toString() {
		return "[productName=" + productName
				+ ", storeName=" + storeName
				+ ", purchaseDate=" + purchaseDate
				+ ", cost=" + cost + "]";
	}
}
