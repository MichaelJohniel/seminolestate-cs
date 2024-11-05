//Name: Michael Maldonado
//Date: 06-09-2024

import java.util.Scanner;

public class TextAnalyzerApplication {
	private static final int MAX_TEXT_BLOCKS = 100;

	public static void main(String[] args) {
		//Initialize input
		Scanner scanner = new Scanner(System.in);
		String strUserInput = null;
		int intUserInput = 0;
		
		//Initialize TextAnalyzer Object Array
		TextAnalyzer[] analyzer = new TextAnalyzer[MAX_TEXT_BLOCKS];
		int textID = 0;
		
		//Menu shell
		do {
			//Prompt user's input for menu
			System.out.println("Enter your choice: ");
			System.out.println("1. Add text");
			System.out.println("2. Display text statistics");
			System.out.println("3. List all texts");
			System.out.println("4. Exit");
			strUserInput = scanner.nextLine();
			intUserInput = Integer.parseInt(strUserInput);
			
			switch (intUserInput) {
				case 1:	//Add text if there's space in the analyzer object array
					if (textID < MAX_TEXT_BLOCKS) {
						//Prompt for text and set user input
						System.out.println("Enter the text: ");
						strUserInput = scanner.nextLine();
						
						//Create a new text block at analyzer index of textID
						analyzer[textID] = new TextAnalyzer(strUserInput);
						textID++;
					} else
						System.out.println("Text limit reached");
					break;
				case 2: //Display stats for each text block
					if (textID > 0) {
						for (int ctr = 0; ctr < textID; ctr++) {
							//Display text with statistics
							System.out.println(analyzer[ctr].getTextBlock());
							System.out.println("Number of consonants: " + analyzer[ctr].getNumberOfConsonants());
							System.out.println("Number of vowels: " + analyzer[ctr].getNumberOfVowels());
							System.out.println("Number of words: " + analyzer[ctr].getNumberOfWords());
							System.out.println("");
						}
					} else
						System.out.println("No text blocks have been added yet");
					break;
				case 3:
					if (textID > 0) {
						for (int ctr = 0; ctr < textID; ctr++) {
							//Display text
							System.out.println(analyzer[ctr].getTextBlock());
						}
					} else
						System.out.println("There is no text to display.");
					break;
				case 4:
					System.out.println("Exiting the TextAnalyzer Application");
					scanner.close();
					break;
				default:
					System.out.println("Invalid menu option entered. Try again.");
			}
		} while (intUserInput != 4);
	}

}
