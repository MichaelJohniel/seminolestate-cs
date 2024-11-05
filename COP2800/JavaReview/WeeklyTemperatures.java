//Name: Michael Maldonado
//Date: 5-20-24

import java.util.Scanner;

public class WeeklyTemperatures {
	public static void main(String[] args) {
		//Declare scanner to record temperatures
		Scanner tempScanner = new Scanner(System.in);
		//Declare Array to store temperatures
		float [] temps = new float[7];
		int aboveFreezing = 0;
		int belowFreezing = 0;
		float highestTemp = Float.NaN;
		float lowestTemp = Float.NaN;
		float avgTemp = 0f;
		
		//For loop prompting for 7 temperatures and storing in the temps array
		for (int tempCounter = 0; tempCounter < 7; tempCounter++) {
			System.out.print("Enter temperature #" + (tempCounter+1) + " ");
			temps[tempCounter] = tempScanner.nextFloat();
			
			//Check if freezing
			if (temps[tempCounter] >= 32f)
				aboveFreezing++;
			else
				belowFreezing++;
			
			//Check if high or low
			if (Float.isNaN(highestTemp) || temps[tempCounter] > highestTemp)
				highestTemp = temps[tempCounter];
			if (Float.isNaN(lowestTemp) || temps[tempCounter] < lowestTemp)
				lowestTemp = temps[tempCounter];
			
			//Add temp to the average
			avgTemp += temps[tempCounter];
		}
		
		//Print statistics
		String roundedAverage = String.format("%.2f", (avgTemp/7));
		System.out.println("Number At or Above Freezing: " + aboveFreezing);
		System.out.println("Number Below Freezing: " + belowFreezing);
		System.out.println("Highest Temperature: " + highestTemp);
		System.out.println("Lowest Temperature: " + lowestTemp);
		System.out.println("Average Temperature: " + roundedAverage);
		
		//Close the scanner
		tempScanner.close();
	}
}
