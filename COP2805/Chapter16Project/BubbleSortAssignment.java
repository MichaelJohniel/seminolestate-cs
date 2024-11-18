// Name: Michael Maldonado
// Date: 11-18-2024
package chapter16;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;


public class BubbleSortAssignment {
	
	public static void main(String[] args) {
		// Welcome message
		out.println("Week 12 Project - Bubble Sort Assignment");
		out.println("----------------------------------------");
		
		// Bubble sort random numbers
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			list.add((int) (Math.random() * 20 + 1));
		
		out.println("Integers Before sorting: " + list);
		bubbleSort(list);
		out.println("Integers After sorting.: " + list);
		
		// Bubble sort floats
		List<Float> floats = new ArrayList<Float>();
		floats.add(3.14f);
		floats.add(2.7f);
		floats.add(11.11f);
		floats.add(32.38f);
		floats.add(-10000f);
		out.println("Floats before sorting..: " + floats);
		bubbleSort(floats);
		out.println("Floats after sorting...: " + floats);
		
		// Bubble sort strings - These are my friends & i!
		List<String> names = new ArrayList<>();
		names.add("Preita");
		names.add("Elisa");
		names.add("Cindy");
		names.add("Sam");
		names.add("Michael");
		out.println("Names before sorting...: " + names);
		bubbleSort(names);
		out.println("Names after sorting....: " + names);
		
	}
	
	public static <E extends Comparable<E>> void bubbleSort(List<E> list) {
		for (int i = 0; i < list.size(); i++) {

			boolean swapOccurred = false;
			
			for (int j = 0; j < list.size() - i - 1; j++) {
				// Swap 2 elements if out of order
				if (list.get(j).compareTo(list.get(j + 1)) > 0) {
					E temp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
					swapOccurred = true;
				}
			}
			
			// Stop swapping since it's already sorted
			if (!swapOccurred) {
				break;
			}
		}
	}
}