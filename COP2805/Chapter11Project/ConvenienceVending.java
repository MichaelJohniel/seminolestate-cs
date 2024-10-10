package chapter11;

import static java.lang.System.out;
import java.util.Scanner;

public class ConvenienceVending {
	
	public static void main(String[] args) {
		
		// Instantiate machines
		VendingMachine machine = null;
		
		// Initialize Scanner
		Scanner input = new Scanner(System.in);
		
		// Welcome message
		out.println("\nIt's 11:09 pm and you stumble upon an alley of vending machines in Tokyo.");
		
		while (true) {
			out.print("\nWould you like a (G)umball or a (S)oda?... (W)alk away... ");
			String machineChoice = input.nextLine();
			
			switch (machineChoice.toLowerCase()) {
			case "g": 
				machine = new GumballMachine(); 
				out.println("\nYou walk up to the shiny red gumball machine."); 
				break;
			case "s": 
				machine = new SodaMachine(); 
				out.println("\nYou walk up to the glowing soda machine."); 
				break;
			case "w": 
				out.println("You walk away."); 
				input.close(); 
				return;
			default: out.println("Invalid choice");
			}
			
			while (machine != null) {
				out.print("(V)end, (R)efill or (E)xit: ");
				String choice = input.nextLine();
				
				switch (choice.toLowerCase()) {
				case "v": 
					out.println(machine.dispense()); break;
				case "r": 
					out.print("Enter amount to refill: ");
                    try {
                        int amount = Integer.parseInt(input.nextLine());
                        machine.refill(amount);
                        out.println("Refilled...");
                    } catch (NumberFormatException e) {
                        out.println("Invalid number format");
                    }
                    break;
				case "e": machine = null; break;
				default: out.println("Invalid choice");
				}
			}
		}
	}
}        

interface VendingMachine {
	void refill(int count);
	String dispense();
	boolean isEmpty();
}

class GumballMachine implements VendingMachine {
	
	// Initialize product amount
	int productCount = 5;
	
	// Descriptors
	String[] descriptors = {"Yummy", "Delicious", "Tasty", "Sweet", "Mouthwatering"};
    String[] colors = {"Red", "Blue", "Green", "Yellow", "Purple"};
	
	@Override
	public void refill(int amount) {
		productCount += amount;
	}
	
	@Override
	public String dispense() {
		// Verify product is available and decrement count
		if (this.isEmpty())
			return "GumballMachine is empty, no product dispensed";
		productCount --;

		
		// Pick descriptor and color
        String randomDescriptor = descriptors[((int) (Math.random() * descriptors.length))];
        String randomColor = colors[((int) (Math.random() * colors.length))];
		
		return "You got a: " + randomDescriptor + " " + randomColor + " gumball!";
	}
	
	@Override
	public boolean isEmpty() {
		return productCount == 0;
	}
}

class SodaMachine implements VendingMachine {
	
	// Initialize product amount
	int productCount = 5;
	
	// Descriptors
	String[] descriptors = {"Yummy", "Delicious", "Tasty", "Fizzy", "Refreshing"};
    String[] flavors = {"C.C. Lemon", "Pepsi", "Strawberry Ramune", "Grape Ramune", "Orange Ramune"};
    
    @Override
    public void refill(int amount) {
    	productCount += amount;
    }
    
    @Override
    public String dispense() {
    	// Verify product is available and decrement count
    	if (this.isEmpty())
    		return "SodaMachine is empty, no product dispensed";
    	productCount --;
    	
    	// Pick descriptor and flavor
    	String randomDescriptor = descriptors[((int) (Math.random() * descriptors.length))];
    	String randomFlavor = flavors[((int) (Math.random() * flavors.length))];
    	
    	return "You got a: " + randomDescriptor + " " + randomFlavor + "!";
    }
    
    @Override
    public boolean isEmpty() {
    	return productCount == 0;
    }
}