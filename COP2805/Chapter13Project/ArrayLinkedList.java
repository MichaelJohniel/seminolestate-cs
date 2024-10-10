// Name: Michael Maldonado
// Date: 09-30-24
package chapter13;

public class ArrayLinkedList {

	public static void main(String[] args) throws Exception {
	    // Create an instance of MyArrayList to hold String type album names
	    MyList<String> albums = new MyLinkedList<>();

	    // Adding album names to the MyArrayList
	    albums.add("Twenty Twenty by Djo");
	    albums.add("Sour by Olivia Rodrigo");
	    albums.add("Seven + Mary by RKS");

	    // Print the contents of the albums list
	    System.out.println("Album Collection: " + albums);

	    // Getting an album by index
	    try {
	        String firstAlbum = albums.get(0);
	        System.out.println("First Album: " + firstAlbum);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }

	    // Find the index of an album
	    String searchAlbum = "Sour by Olivia Rodrigo";
	    int index = albums.indexOf(searchAlbum);
	    System.out.println("Index of '" + searchAlbum + "': " + index);

	    // Removing an album
	    try {
	        String removedAlbum = albums.remove(index);
	        System.out.println("Removed Album: " + removedAlbum);
	        System.out.println("Updated Album Collection: " + albums);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }

	    // Checking if an album exists in the list
	    String checkAlbum = "Swimming by Mac Miller";
	    boolean contains = albums.contains(checkAlbum);
	    System.out.println("Contains '" + checkAlbum + "': " + contains);

	    // addAll to add new albums to the original list
	    MyList<String> newAlbums = new MyLinkedList<>();
	    newAlbums.add("Cleopatra by The Lumineers");
	    newAlbums.add("Dark Corners and Alchemy by Mehro");
	    newAlbums.add("Disappear Here by Bad Suns");
	    try {
	        albums.addAll(newAlbums);
	        System.out.println("Updated Album Collection: " + albums);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }

	    // Check if album exists (should be true)
	    checkAlbum = "Dark Corners and Alchemy by Mehro";
	    contains = albums.contains(checkAlbum);
	    System.out.println("Contains '" + checkAlbum + "': " + contains);

	    // Adding more new albums
	    newAlbums.clear(); // Clear newAlbums list
	    newAlbums.add("ZABA by Glass Animals");
	    try {
	        albums.addAll(newAlbums);
	        System.out.println("Updated Album Collection: " + albums);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }

	    // containsAll method
	    MyList<String> someAlbums = new MyLinkedList<>();
	    someAlbums.add("ZABA by Glass Animals");
	    someAlbums.add("Cleopatra by The Lumineers");

	    // Clear the list
	    albums.clear();
	    System.out.println("Album Collection after clearing: " + albums);
	    
	    // Create two lists of albums for comparison
        MyList<String> albums1 = new MyLinkedList<>();
        albums1.add("Twenty Twenty by Djo");
        albums1.add("Sour by Olivia Rodrigo");
        albums1.add("Seven + Mary by RKS");

        MyList<String> albums2 = new MyLinkedList<>();
        albums2.add("Seven + Mary by RKS");
        albums2.add("Sour by Olivia Rodrigo");
        albums2.add("Twenty Twenty by Djo");

        // Compare the two lists
        boolean areEquivalent = areEquivalentSets(albums1, albums2);
        System.out.println("Are the two album lists equivalent sets? " + areEquivalent);
	}
	
	public static <E> boolean areEquivalentSets(MyList<E> list1, MyList<E> list2) throws Exception {
        if (list1.size() != list2.size()) {
            return false;
        }

        boolean[] matchedIndices = new boolean[list2.size()]; // To track which elements from list2 have been matched

        // Loop through list1 and compare to list2
        for (int i = 0; i < list1.size(); i++) {
            E item1 = list1.get(i);
            boolean foundMatch = false;

            for (int j = 0; j < list2.size(); j++) {
                E item2 = list2.get(j);

                if (item1.equals(item2) && !matchedIndices[j]) {
                    matchedIndices[j] = true;
                    foundMatch = true;
                    break;
                }
            }

            if (!foundMatch) {
                return false;
            }
        }

        return true;
    }
}

interface MyList<E> {
	
	public void insert (int index, E object) throws Exception;
	public void add (E object);
	public E get (int index) throws Exception;
	public int indexOf (E object);
	public int lastIndexOf (E object);
	public E remove (int index) throws Exception;
	public E set (int index, E object) throws Exception;
	public void addAll(MyList<E> newItems) throws Exception;
	public void clear();
	public boolean contains(E object);
	public int size();
}

class Node<E> {
	E element;
	Node<E> next;
	
	public Node (E element) {
		this.element = element;
	}
}

class MyLinkedList<E> implements MyList<E> {
	Node<E> head = null;
	Node<E> tail = null;
	int size = 0;
	@Override
	public void insert(int index, E object) throws Exception {
		
		if (index < 0 || index > size - 1)
			throw new Exception("Invalid index");
		
		Node<E> newNode = new Node<E> (object);
		
		Node<E> current = head;
		int counter = 0;
		Node<E> previous = null;
		while (counter < index) {
			previous = current;
			current = current.next;
			counter++;
		}
		if (previous.next != null)
			previous.next = newNode;
		newNode.next = current;
		
		size ++;
		
		if (index == 0)
			head = newNode;
		if (index == size - 1)
			tail = newNode;
		
	}

	@Override
	public void add(E object) {
		Node<E> newNode = new Node<E>(object);
		
		size++;
		if (head == null)
			head = newNode;
		else
			tail.next = newNode;
		
		tail = newNode;
	}
	@Override
	public E get(int index) throws Exception {
		if (index < 0 || index > size - 1)
			throw new Exception("Invalid index");
		
		Node<E> current = head;
		int counter = 0;
		while (counter < index) {
			current = current.next;
			counter ++;
		}
		return current.element;
	}
	@Override
	public int indexOf(E object) {
		Node<E> current = head;
		int index = 0;
		while (current != null) {
			if (object.equals(current.element))
				return index;
			current = current.next;
			index ++;
		}
		return -1;
	}
	@Override
	public int lastIndexOf(E object) {
		int result = -1;
		
		Node<E> current = head;
		int index = 0;
		while (current != null) {
			if (object.equals(current.element))
				result = index;
			current = current.next;
			index++;
		}
		return result;
	}
	@Override
	public E remove(int index) throws Exception {
		
		if (index < 0 || index > size - 1)
				throw new Exception("Invalid index");
		
		Node<E> current = head;
		int counter = 0;
		Node<E> previous = null;
		while (counter < index) {
			previous = current;
			current = current.next;
			counter++;
		}
		if (previous != null)
			previous.next = current.next;
		E result = current.element;
		
		size--;
		
		if (index == 0)
			head = current.next;
		if (index == size - 1)
			tail = previous;
		return result;
	}
	@Override
	public E set(int index, E object) throws Exception {
		if (index < 0 || index > size - 1)
			throw new Exception("Invalid index");
		
		Node<E> current = head;
		int counter = 0;
		while (counter < index) {
			current = current.next;
			counter ++;
		}
		
		E result = current.element;
		current.element = object;
		return result;
	}
	@Override
	public void addAll(MyList<E> newItems) throws Exception {
		for (int i = 0; i < newItems.size(); i++)
			add(newItems.get(i));
		
	}
	@Override
	public void clear() {
		size = 0;
		head = tail = null;
	}
	@Override
	public boolean contains(E object) {
	    Node<E> current = head;
	    while (current != null) {
	        if (object == null ? current.element == null : object.equals(current.element)) {
	            return true;
	        }
	        current = current.next; 
	    }
	    return false;
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public String toString() {
		String result = "[";
		Node<E> current = head;
		while (current != null) {
			result += current.element;
			if (current.next != null)
				result += ", ";
			current = current.next;
		}
		
		return result;
	}
}

class MyArrayList<E> implements MyList<E> {
	private E[] array = null;
	private int size = 0;
	private int capacity = 0;
	
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		capacity = 10;
		array = (E[]) new Object[capacity];
	}
	
	private void expandIfNeeded() {
		if (size == capacity) {
			
			int oldCapacity = capacity;
			capacity *= 2;
			
			@SuppressWarnings("unchecked")
			E[] newArray = (E[]) new Object[capacity];
			
			for (int i = 0; i < oldCapacity; i++)
				newArray[i] = array[i];
			
			array = newArray;
		}
	}
	
	@Override
	public void insert(int index, E object) throws Exception {
		if (index < 0 || index > size) 
			throw new Exception("Invalid index in MyArrayList.insert()");
			
		expandIfNeeded();
		
		for (int i = size - 1; i >= index; i--)
			array[i+1] = array[i];
		
		size++;
	}
	
	@Override
	public void add(E object) {
		expandIfNeeded();
		array[size++] = object;
	}
	
	@Override
	public E get(int index) throws Exception {
		
		if (index < 0 || index > size - 1)
			throw new Exception("Invalid index in MyArrayList.get()");
		
		return array[index];
	}
	
	@Override
	public int indexOf(E object) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(object))
				return i;
		}
		return -1;
	}
	
	@Override
	public int lastIndexOf(E object) {
		
		for (int i = size - 1; i >= 0; i--) {
	        if (array[i].equals(object)) {
	            return i;
	        }
	    }
	    return -1;
	}
	
	@Override
	public E remove(int index) throws Exception {
		
		if (index < 0 || index > size - 1)
			throw new Exception("Invalid index in MyArrayList.remove()");
		
		E result = array[index];
		
		for (int i = index; i < size - 1; i ++)
			array[i] = array[i+1];
		
		size--;
		
		return result;
	}
	
	@Override
	public E set(int index, E object) throws Exception {
		
		if (index < 0 || index > size - 1)
			throw new Exception("Invalid index in MyArrayList.set()");
		
		E result = array[index];
		
		array[index] = object;
		
		return result;
	}
	
	@Override
	public void addAll(MyList<E> newItems) throws Exception {
		
		int newSize = this.size + newItems.size();
	    
	    while (this.capacity < newSize) {
	        expandIfNeeded();
	    }

		for (int i = 0; i < newItems.size(); i++)
			add(newItems.get(i));
	}

	@Override
	public void clear() {
		
		for (int i = 0; i < size; i++) {
	        array[i] = null;
	    }
	    size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean contains(E object) {
		for (int i = 0; i < size; i++) {
	        if (object == null ? array[i] == null : object.equals(array[i])) {
	            return true;
	        }
	    }
	    return false;
    }
	
	public boolean containsAll(MyList<E> otherList) {
		for (int i = 0; i < otherList.size(); i++) {
			try {
				if (indexOf(otherList.get(i)) < 0)
					return false;
			} catch (Exception ex) {
				return false;
			}
		}
		
		return true;
	}
	 
	@Override
	public String toString() {
		String result = "[";
		for (int i = 0; i < size; i++) {
			result += array[i];
			if (i < size-1)
				result += ", ";
		}
		return result += "]";
	}
}