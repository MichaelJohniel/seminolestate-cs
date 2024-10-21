// Name: Michael Maldonado
// Date: 10-21-2024

package chapter14;
import java.util.HashMap;
import java.util.Objects;
import static java.lang.System.out;

public class Book {
	
	// Properties
	private String title;
	private String author;
	private String publisher;
	private int yearPublished;
	
	// Constructor
	public Book(String title, String author, String publisher, int yearPublished) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.yearPublished = yearPublished;
	}
	
	// Main
	public static void main(String[] args) {
		
		// Create book HashMap
		HashMap<Book, String> library = new HashMap<>();
		
		// Create Books
		Book book1 = new Book("The Hunger Games", "Suzanne Collins", "Scholastic", 2008);
		Book book2 = new Book("Catching Fire", "Suzanne Collins", "Scholastic", 2009);
		Book book3 = new Book("Mockingjay", "Suzanne Collins", "Scholastic", 2010);
		Book book4 = new Book("The Hunger Games", "Suzanne Collins", "Scholastic", 2008);
		
		// Add books to library
		library.put(book1, "Dystopian Novel");
		library.put(book2, "Dystopian Novel");
		library.put(book3, "Dystopian Novel");
		library.put(book4, "Bestseller");
		
		out.println("The size of the library is " + library.size());
		out.println("The 1st book is " + book1.getTitle() + " and has a value of: " + library.get(book1));
		out.println("The 4th book is " + book4.getTitle() + " and has a value of: " + library.get(book4));
	}
	
	// Getters
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public int getYearPublished() {
		return yearPublished;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		
		if (other == null || getClass() != other.getClass())
			return false;
		
		Book book = (Book)other;
		return yearPublished == book.yearPublished &&
				Objects.equals(title, book.title) &&
				Objects.equals(author,  book.author) &&
				Objects.equals(publisher, book.publisher);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(title, author, publisher, yearPublished);
	}
	
	@Override
	public String toString() {
		return title + " by " + author +  " (" + yearPublished + ")";
	}
}
