// Name: Michael Maldonado
// Date: 09-23-24
package chapter13;

public class ArrayListAssignment {

	public static void main(String[] args) {
		// Create an instance of MyArrayList to hold String type album names
        MyList<String> albums = new MyArrayList<>();

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
        MyList<String> newAlbums = new MyArrayList<>();
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
        
        // Clear the list
        albums.clear();
        System.out.println("Album Collection after clearing: " + albums);

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

class MyArrayList<E> implements MyList<E> {
	private E[] array = null;
	private int size = 0;
	private int capacity = 0;
	
	public MyArrayList() {
		capacity = 10;
		array = (E[]) new Object[capacity];
	}
	
	private void expandIfNeeded() {
		if (size == capacity) {
			
			int oldCapacity = capacity;
			capacity *= 2;
			
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
