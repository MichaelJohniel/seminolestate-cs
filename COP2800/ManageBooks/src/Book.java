//Name: Michael Maldonado
//Date: 05-28-24

import java.time.LocalDate;

public class Book {
	
	//Declare local variables
	private String title;
	private String ISBN;
	private LocalDate datePublished;
	private Double price;
	
	//Declare Constants
	public final String DEFAULT_TITLE = "no title assigned";
	public final String DEFAULT_ISBN = "no ISBN assigned";
	public final LocalDate DEFAULT_DATE_PUBLISHED = LocalDate.now();
	public final Double DEFAULT_PRICE = 0.0;
	
	public Book(String title, String ISBN, LocalDate datePublished, Double price) {
		this.title = title;
		this.ISBN = ISBN;
		this.datePublished = datePublished;
		this.price = price;
	}

	public Book() {
		this.title = DEFAULT_TITLE;
		this.ISBN = DEFAULT_ISBN;
		this.datePublished = DEFAULT_DATE_PUBLISHED;
		this.price = DEFAULT_PRICE;
	}
	
	public Book(String newTitle, String newISBN) {
		this.title = newTitle;
		this.ISBN = newISBN;
		this.datePublished = DEFAULT_DATE_PUBLISHED;
		this.price = DEFAULT_PRICE;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String newTitle) {
		//Check if new title is valid
		if (newTitle != null && newTitle.length() > 0) {
			title = newTitle; //Replace title
		} else {
			System.out.println("Invalid title entered."); //Keep old title
		}
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setISBN(String newISBN) {
		//Check if new ISBN is valid
		if (newISBN != null && newISBN.length() > 0) {
			ISBN = newISBN; //Replace ISBN
		} else {
			System.out.println("Invalid ISBN entered."); //Keep old ISBN
		}
	}
	
	public LocalDate getDatePublished() {
		return datePublished;
	}
	
	public void setDatePublished (LocalDate newDatePublished) {
		if (newDatePublished != null) {
			datePublished = newDatePublished; //Replace date
		} else {
			System.out.println("Invalid date entered."); //Keep old date
		}
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double newPrice) {
		if (newPrice >= 0.0) {
			price = newPrice; //Replace price
		} else {
			System.out.println("Invalid price entered."); //Keep old price
		}
	}
	
	public String toString() {
		return "[title=" + title +
				", ISBN=" + ISBN +
				", datePublished=" + datePublished + 
				", price=" + price + "]\n";
	}
}
