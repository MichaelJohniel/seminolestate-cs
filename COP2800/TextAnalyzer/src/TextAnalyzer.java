//Name: Michael Maldonado
//Date: 06-09-2024

public class TextAnalyzer {
	
	//Declare variables
	public final String DEFAULT_TEXT_BLOCK = "no text";
	private String textBlock;
	
	//Constructs
	public TextAnalyzer(String newText) {
		setTextBlock(newText);
	}
	
	//Default Construct
	public TextAnalyzer() {
		this.textBlock = DEFAULT_TEXT_BLOCK;
	}
	
	//Getters
	public int getNumberOfConsonants() {
		//Initialize consonants count
		int consonantCount = 0;
		
		//Validate string
		if (textBlock.length() > 0) {
			//Make everything lowercase
			String text = textBlock.toLowerCase();
			
			//Use regex to remove non-consonants
			text = text.replaceAll("[^bcdfghjklmnpqrstvwxyz]", "");
			
			//count the length of the remaining string
			consonantCount = text.length();
		}
		return consonantCount;
	}
	
	public int getNumberOfVowels() {
		//Initialize vowel count
		int vowelCount = 0;
		
		//Check if string has words
		if (textBlock.length() > 0) {
			//Make everything lowercase for easier analysis
			String text = textBlock.toLowerCase();
			
			//Use regex to remove all non-vowels from the text
			text = text.replaceAll("[^aeiou]", "");
			
			//Count the length of the remaining string
			vowelCount = text.length();
		}
		return vowelCount;
	}
	
	public int getNumberOfWords() {
		//Initialize word count
		int wordCount = 0;
		
		//Check if string has words
		if (textBlock.length() > 0) {
			wordCount = 1;
			
			//Don't break encapsulation
			String text = textBlock.trim();
			
			//If there's a space, assume a new word is being added and increment the word count
			for (int i = 1; i < text.length(); i++) {
				if (text.charAt(i) == ' ' && text.charAt(i-1) != ' ' )
					wordCount++;
			}
		}
		return wordCount;
	}
	
	public String getTextBlock() {
		return textBlock;
	}
	
	//Setters
	public void setTextBlock(String newText) {
		if (newText != null && newText.length() > 0)
			textBlock = newText;
	}
}
