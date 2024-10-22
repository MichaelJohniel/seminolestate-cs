// Name: Michael Maldonado
// Date: 10-21-2024

package chapter14;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.lang.System.out;

public class Book {

    // Properties
    private String title;
    private String author;
    private String publisher;
    private int yearPublished;
    private Set<String> category = new HashSet<>();

    // Constructor with categories
    public Book(String title, String author, String publisher, int yearPublished, String... categories) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        for (String category : categories) {
            this.category.add(category);
        }
    }

    // Main
    public static void main(String[] args) {

        // Use custom BookLibrary instead of a regular HashMap
        BookLibrary library = new BookLibrary();

        // Create Books with categories
        Book book1 = new Book("The Hunger Games", "Suzanne Collins", "Scholastic", 2008, "Dystopian");
        Book book2 = new Book("Catching Fire", "Suzanne Collins", "Scholastic", 2009, "Dystopian");
        Book book3 = new Book("Mockingjay", "Suzanne Collins", "Scholastic", 2010, "Dystopian");

        // Add books to library
        library.put(book1, book1.category);
        library.put(book2, book2.category);
        library.put(book3, book3.category);

        // Output library
        out.println("The size of the library is " + library.size());
        out.println("The 1st book is '" + book1.getTitle() + "' and has categories: " + library.get(book1));
        
        // Create same book but new category
        out.println("\nCreating a duplicate book w/ new category...\n");
        Book book4 = new Book("The Hunger Games", "Suzanne Collins", "Scholastic", 2008, "Bestseller");
        library.put(book4, book4.category);
        
        // Output library
        out.println("The 1st book is '" + book1.getTitle() + "' and has categories: " + library.get(book1));
        out.println("The 4th book is '" + book4.getTitle() + "' and has categories: " + library.get(book4));
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

        Book book = (Book) other;
        return yearPublished == book.yearPublished &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publisher, yearPublished);
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + yearPublished + ")";
    }
}

// Custom HashMap for managing books and categories
class BookLibrary extends HashMap<Book, Set<String>> {

    private static final long serialVersionUID = 9216909016546456895L;

	@Override
    public Set<String> put(Book book, Set<String> categories) {
       
        if (this.containsKey(book)) {
            // If it exists, merge the new categories with the existing ones
            this.get(book).addAll(categories);
            return this.get(book);
        } else {
            // If it doesn't exist, just add it
            return super.put(book, categories);
        }
    }
}
