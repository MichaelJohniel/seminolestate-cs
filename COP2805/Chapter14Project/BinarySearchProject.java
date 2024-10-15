package chapter14;
import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BinarySearchProject {
    public static void main(String[] args) throws FileNotFoundException {
        List<Asteroid> asteroids = new ArrayList<>();
        
        File file = new File("src/chapter14/lc_summary_pub.txt");
        Scanner input = new Scanner(file);
        
        for (int i = 0; i < 5; i++) {
            input.nextLine(); // Skip header lines
        }
        
        while (input.hasNext()) {
            String line = input.nextLine();
            
            int number = Integer.parseInt(line.substring(0, 8).trim());
            String name = line.substring(10, 40).trim();
            float diameter = readFloat(line, 89, 96);
            float reflectivity = readFloat(line, 136, 140);
            float period = readFloat(line, 148, 156);
            Asteroid asteroid = new Asteroid(number, name, diameter, reflectivity, period);
            asteroids.add(asteroid);
        }
        
        out.println("Read " + asteroids.size() + " asteroids...");
        
        Collections.sort(asteroids);
        input.close();
        
        // Linear search
        Asteroid asteroid = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            asteroid = linearSearch(asteroids, "Pluto");
        }
        long end = System.currentTimeMillis();
        out.println("Linear search: Pluto's record is " + asteroid);
        out.println("Linear search: Time to find Pluto: " + (end - start) + " milliseconds");
        
        // Binary search using generic method and returning boolean
        boolean found = false;
        start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            found = binarySearch(asteroids, new Asteroid(0, "Pluto", 0, 0, 0));
        }
        end = System.currentTimeMillis();
        out.println("Binary search: Pluto found? " + found);
        out.println("Binary search: Time to check Pluto: " + (end - start) + " milliseconds");
        
        // Test with integers
        List<Integer> intList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            intList.add(i);
        }
        
        boolean foundInt = binarySearch(intList, 50);
        out.println("Binary search for integer 50 found? " + foundInt);
    }
    
    private static Asteroid linearSearch(List<Asteroid> asteroids, String asteroidName) {
        for (int i = 0; i < asteroids.size(); i++) {
            if (asteroids.get(i).name.equals(asteroidName)) {
                return asteroids.get(i);
            }
        }
        return null;
    }
    
    // Modified binary search to return a boolean
    private static <E extends Comparable<E>> boolean binarySearch(List<E> list, E data) {
        int index = Collections.binarySearch(list, data);
        return index >= 0;  // Return true if found, false if not
    }
    
    private static float readFloat(String line, int startIndex, int endIndex) {
        float num = 0;
        String str = line.substring(startIndex, endIndex).trim();
        if (!str.isEmpty()) {
            try {
                num = Float.parseFloat(str);
            } catch (Exception ex) {
                out.println("Error parsing float: '" + str + "' at indices [" + startIndex + ", " + endIndex + "]");
            }
        }
        return num;
    }

}

class Asteroid implements Comparable<Asteroid> {
    int number;
    String name;
    float diameter;
    float reflectivity;
    float period;
    
    public Asteroid(int number, String name, float diameter, float reflectivity, float period) {
        super();
        this.number = number;
        this.name = name;
        this.diameter = diameter;
        this.reflectivity = reflectivity;
        this.period = period;
    }
    
    @Override
    public String toString() {
        return name + " [ID=" + number + ", diameter=" + diameter + " km, reflectivity=" + reflectivity + ", period=" + period + " hours]";
    }
    
    @Override
    public int compareTo(Asteroid other) {
        return this.name.compareToIgnoreCase(other.name);  // Case-insensitive comparison
    }
}
