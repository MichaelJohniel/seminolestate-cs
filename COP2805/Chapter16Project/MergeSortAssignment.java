// Name: Michael Maldonado
// Date: 11-25-2024
package chapter16;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;


public class MergeSortAssignment {
	
	public static void main(String[] args) {
		// Welcome message
		out.println("Week 13 Project - Merge Sort Assignment");
		out.println("----------------------------------------");
		
		// Find largest integer
		List<Integer> intList = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			intList.add((int) (Math.random() * 20 + 1));
		out.println("Floats before searching: " + intList);
		out.println("Largest Integer: " + findLargest(intList));
		
		// Find largest string
		List<String> names = new ArrayList<>();
		names.add("Preita");
		names.add("Elisa");
		names.add("Cindy");
		names.add("Sam");
		names.add("Michael");
		out.println("Names before searching: " + names);
		out.println("Largest String: " + findLargest(names));
	}
	
	public static <E extends Comparable<E>> E findLargest(List<E> list) {
	    // Base case
	    if (list.size() == 1) {
	        return list.get(0);
	    }
	    
	    // Divide the list into two halves
	    int mid = list.size() / 2;
	    List<E> half1 = new ArrayList<>(list.subList(0, mid));
	    List<E> half2 = new ArrayList<>(list.subList(mid, list.size()));
	    
	    // Recurse
	    E largestLeft = findLargest(half1);
	    E largestRight = findLargest(half2);
	    
	    // Return the larger of the two results
	    return (largestLeft.compareTo(largestRight) > 0) ? largestLeft : largestRight;
	}

	
	public static <E extends Comparable<E>> List<E> mergeSort(List<E> list) {
		return mergeSort(list, 0, list.size() - 1);
	}
	
	public static <E extends Comparable<E>> List<E> mergeSort(List<E> list, int start, int end) {
		
		// base case
		if (end == start - 1)
			return new ArrayList<>();
		
		// base case, list with one item
		if (end == start) {
			List<E> result = new ArrayList<>();
			result.add(list.get(start));
			return result;
		}
			
		// parition into two halves, and recurse
		int mid = (end + start) / 2;
		
		// sort first half
		List<E> half1 = mergeSort(list, start, mid);
		
		// sort second half
		List<E> half2 = mergeSort(list, mid + 1, end);
		
		// merge
		return merge(half1, half2);
	}
	
	public static <E extends Comparable<E>> List<E> merge(List<E> half1, List<E> half2) {
		
		List<E> result = new ArrayList<>();
		int index1 = 0, index2 = 0;
		
		//loop until both halves are depleted
		while (index1 < half1.size() || index2 < half2.size()) {
			
			if (index1 == half1.size())
				result.add(half2.get(index2++));
			else if (index2 == half2.size())
				result.add(half1.get(index1++));
			else {
				if (half1.get(index1).compareTo(half2.get(index2)) > 0)
					result.add(half2.get(index2++));
				else
					result.add(half1.get(index1++));
			}
		}
		
		return result;
	}
}