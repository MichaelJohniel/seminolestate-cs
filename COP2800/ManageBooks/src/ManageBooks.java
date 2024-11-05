//Name: Michael Maldonado
//Date: 05-29-24

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManageBooks {

	public static void main(String[] args) {
		//Declare Scanner for user input
		Scanner keyboard = new Scanner(System.in);
		String input = null;
		DateTimeFormatter formatted = DateTimeFormatter.ofPattern("M/dd/yyyy");
		
		//Prompt for book 1 information
		System.out.print("Enter title for book: ");
		String title = keyboard.nextLine();
		System.out.print("Enter ISBN for book: ");
		String ISBN = keyboard.nextLine();
		
		System.out.print("Enter the publish date (mm/dd/yyyy): ");
		input = keyboard.nextLine();
		LocalDate datePublished = LocalDate.parse(input, formatted);
		
		System.out.print("Enter price for the book: ");
		Double price = keyboard.nextDouble();
		keyboard.nextLine(); //Delete enter for future string inputs
		
		//Instatiate 2 books. One with inputted info and One with default constructor
		Book book1 = new Book(title, ISBN, datePublished, price);
		Book book2 = new Book();
		
		//Print information for Book 1 and 2
		System.out.println("Book 1 \nBook " + book1.toString());
		System.out.println("Book 2 \nBook" + book2.toString());
		
		//Prompt for book 3 info
		System.out.print("Enter title for Book 3: ");
		title = keyboard.nextLine();
		System.out.print("\nEnter ISBN for Book 3: ");
		ISBN = keyboard.nextLine();
		
		//Instantiate book 3
		Book book3 = new Book(title, ISBN);
		
		//Print information for Book 3
		System.out.println("\nBook 3 \nBook" + book3.toString());
		
		//Prompt for changes to Book 1
		System.out.println("Change the values for Book 1");
		System.out.print("Enter new title for Book 1: ");
		book1.setTitle(keyboard.nextLine());
		System.out.print("Enter new ISBN for Book 1: ");
		book1.setISBN(keyboard.nextLine());
		
		System.out.print("Enter new publish date (mm/dd/yyyy): ");
		input = keyboard.nextLine();
		book1.setDatePublished(LocalDate.parse(input, formatted));
		
		System.out.print("Enter new price for Book 1: ");
		book1.setPrice(keyboard.nextDouble());
		keyboard.nextLine(); //Delete enter for future string inputs
		
		//Print Book 1 changes using get methods
		System.out.println("\nNew Values for Book 1 using get methods" +
				"\n" + book1.getTitle() + 
				"\n" + book1.getISBN() +
				"\n" + book1.getDatePublished() +
				"\n" + book1.getPrice());
				
		//Close scanner
		keyboard.close();
	}

}
