//Name: Michael Maldonado
//Date: 5-28-24

import java.util.Scanner;

public class ReviewProject {
	public static void main(String[] args) {
		//Declare scanner for user input
		Scanner gradeScanner = new Scanner(System.in);
		
		//Declare Variables
		int [] grades = new int[8];			//Integer array to store inputed grades
		
		int passingScores = 0; 				//Count passing scores
		int failingScores = 0; 				//Count failing scores
		
		int highScore = Integer.MIN_VALUE; 	//Highest score- default to min so any score will be higher
		int amtHighWasEarned = 0; 			//# of times high score was earned
		int lowScore = Integer.MAX_VALUE; 	//Lowest score - default to max so any score will be lower
		int amtLowWasEarned = 0; 			//# of times low score was earned
		
		float avgScore = 0f;				//store the average score
		
		
		
		
		//For loop prompting for 8 grades
		for (int i = 0; i < 8; i++) {
			System.out.print("Enter exam score #" + (i+1) + " ");
			grades[i] = gradeScanner.nextInt(); //Store grade input in grades array at index i
			
			
			//Check if grade is passing and count passing/failing grades
			if (grades[i] >= 70)
				passingScores++; //Mark score as passing
			else
				failingScores++; //Mark score as failing
			
			//Check if grade is high or low
			if (grades[i] >= highScore) {
				if (grades[i] == highScore) //Check if score was previously earned
					amtHighWasEarned++;
				else {
					amtHighWasEarned = 1; 	//Reset earned counter
					highScore = grades[i]; 	//Set new high
				}
			}
			if (grades[i] <= lowScore) {
				if (grades[i] == lowScore) 	//Check if score was previously earned
					amtLowWasEarned++;
				else {
					amtLowWasEarned = 1; 	//Reset earned counter
					lowScore = grades[i]; 	//Set new low
				}
			}
			
			//Add grade to average as a float
			avgScore += (float)grades[i];
		}

		//Calculate the average grade
		String roundedAverage = String.format("%.2f", (avgScore/grades.length));
		
		//Print score statistics
		System.out.println("Passing: " + passingScores);
		System.out.println("Failing: " + failingScores);
		//Check if high/low was earned more than once, then Print accordingly
		if (amtHighWasEarned > 1)
			System.out.println("Highest score: " + highScore + " [" + amtHighWasEarned + " Scores]");
		else
			System.out.println("Highest score: " + highScore);
		if (amtLowWasEarned > 1)
			System.out.println("Lowest score: " + lowScore + " [" + amtLowWasEarned + " Scores]");
		else
			System.out.println("Lowest score: " + lowScore);
		//Print average score
		System.out.println("Average score: " + roundedAverage);
		
		
		//Close the scanner
		gradeScanner.close();
	}
}
