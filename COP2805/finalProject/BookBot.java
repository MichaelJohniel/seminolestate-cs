package finalProject;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class BookBot {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> openBook();
                case "2" -> runTests();
                case "3" -> exitProgram();
                default -> System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("""
                      P.G. eBook Reader
                ---------------------------------
                	1. Open a book
                	2. Run tests
                	3. Exit
                ---------------------------------
                Advanced Java Final by Michael M.""");
    }

    private static void exitProgram() {
        System.out.println("Goodbye.");
        System.exit(0);
    }

    private static String getBookText(String name) {
        Path filePath = Path.of("books", name.toLowerCase() + ".txt");
        try {
            System.out.println("Looking for book at: " + filePath.toAbsolutePath());
            return Files.readString(filePath);
        } catch (IOException e) {
            System.out.printf("Error: The book '%s' was not found.%nReturning to main menu...%n", capitalize(name));
            return null;
        }
    }

    private static int getWordCount(String text) {
        return (text == null || text.isBlank()) ? 0 : text.split("\\s+").length;
    }

    private static Map<Character, Integer> getCharFrequency(String text) {
        if (text == null || text.isBlank()) return Collections.emptyMap();

        Map<Character, Integer> charCount = new HashMap<>();
        text.replaceAll("[^a-zA-Z]", "").toLowerCase()
            .chars()
            .forEach(c -> charCount.put((char) c, charCount.getOrDefault((char) c, 0) + 1));

        return charCount;
    }

    private static List<String> getChapters(String text) {
        if (text == null || text.isBlank()) return Collections.emptyList();

        List<String> chapters = new ArrayList<>();
        StringBuilder currentChapter = new StringBuilder();

        // Matching chapter titles
        String chapterRegex = "(?i)^\\s*(Chapter\\s+[IVXLCDM]+|CHAPTER\\s+[\\d]+)\\.?\\s*$";

        for (String line : text.split("\n")) {

            if (line.matches(chapterRegex)) {
                if (currentChapter.length() > 0) {
                    chapters.add(currentChapter.toString().trim());
                }

                currentChapter.setLength(0);  // Start new chapter

            } else {
                currentChapter.append(line).append("\n");
            }
        }

        if (currentChapter.length() > 0) {
            chapters.add(currentChapter.toString().trim());
        }

        return chapters;
    }

    private static void openBook() {
        System.out.println("Enter 'view options' to see available books or enter a book title: ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("view options")) {
            List<String> availableBooks = findBooks();
            if (availableBooks.isEmpty()) {
                System.out.println("No books found.");
                return;
            }

            // Display available books
            for (int i = 0; i < availableBooks.size(); i++) {
                System.out.println((i + 1) + ". " + availableBooks.get(i));
            }

            // Allow user to select a book
            int bookChoice = getValidBookChoice(availableBooks);
            String selectedBook = availableBooks.get(bookChoice - 1);
            String text = getBookText(selectedBook);
            if (text != null) {
                openBookFromText(text, selectedBook);
            }

        } else {
            String text = getBookText(input);
            if (text != null) {
                openBookFromText(text, input);
            }
        }
    }

    private static int getValidBookChoice(List<String> availableBooks) {
        int bookChoice = -1;
        while (bookChoice < 1 || bookChoice > availableBooks.size()) {
            try {
                System.out.print("Enter the number of the book you want to open: ");
                bookChoice = Integer.parseInt(scanner.nextLine().trim());
                if (bookChoice < 1 || bookChoice > availableBooks.size()) {
                    System.out.println("Invalid selection. Please choose a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return bookChoice;
    }

    private static List<String> findBooks() {
        List<String> books = new ArrayList<>();
        try {
            Files.walk(Paths.get("books"))
                    .filter(Files::isRegularFile)
                    .map(path -> path.getFileName().toString().replaceAll("\\.txt$", ""))
                    .forEach(books::add);
        } catch (IOException e) {
            System.out.println("Error reading books directory.");
        }
        return books;
    }

    private static void openBookFromText(String text, String title) {
        int numWords = getWordCount(text);
        if (numWords == 0) {
            System.out.printf("%s contains no words or readable content.%nReturning to main menu...%n", capitalize(title));
            return;
        }

        Map<Character, Integer> charFrequency = getCharFrequency(text);

        while (true) {
            System.out.printf("""
                    \nWhat would you like to do with %s?
                    ------------------
                    1. Read the book
                    2. Generate report
                    3. Close the book
                    ------------------
                    """, capitalize(title));

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> readBook(text);
                case "2" -> generateReport(title, numWords, charFrequency);
                case "3" -> {
                    System.out.println("Closing the book and returning to main menu...");
                    return; // Exit to main menu
                }
                default -> System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private static void readBook(String text) {
        List<String> chapterTexts = getChapters(text);
        if (chapterTexts.isEmpty()) {
            System.out.println("No chapters found in the book.");
            return;
        }

        // Create nodes for each chapter
        List<ChapterNode> chapterNodes = new ArrayList<>();
        for (String chapterText : chapterTexts) {
            chapterNodes.add(new ChapterNode(chapterText));
        }

        // BFS Chapter traversal
        for (int i = 0; i < chapterNodes.size() - 1; i++) {
            chapterNodes.get(i).addNeighbor(chapterNodes.get(i + 1));
        }

        // Select the starting chapter
        int startingChapterIndex = getValidChapterChoice(chapterNodes);
        ChapterNode currentChapterNode = chapterNodes.get(startingChapterIndex); // Start at the chosen chapter
        navigateChapters(chapterNodes, currentChapterNode);
    }

    private static int getValidChapterChoice(List<ChapterNode> chapterNodes) {
        int startingChapterIndex = -1;
        while (startingChapterIndex < 0 || startingChapterIndex >= chapterNodes.size()) {
            try {
                System.out.print("Enter the chapter number to start from (0 to " + (chapterNodes.size() - 1) + "): ");
                startingChapterIndex = Integer.parseInt(scanner.nextLine().trim());
                if (startingChapterIndex < 0 || startingChapterIndex >= chapterNodes.size()) {
                    System.out.println("Invalid chapter number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid chapter number.");
            }
        }
        return startingChapterIndex;
    }

    private static void navigateChapters(List<ChapterNode> chapterNodes, ChapterNode currentChapterNode) {
        while (true) {
            System.out.println("\n--- Reading Chapter ---");
            readChapter(currentChapterNode);

            // Prompt user after the chapter ends
            System.out.printf("""
                    
                    What would you like to do next?
                    ------------------
                    1. Go back a chapter
                    2. Reread the current chapter
                    3. Go to the next chapter
                    4. Skip to a chapter
                    5. Exit to main menu
                    ------------------
                    """);
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    if (currentChapterNode.getPrevious() != null) {
                        currentChapterNode = currentChapterNode.getPrevious();
                    } else {
                        System.out.println("You're already at the first chapter.");
                    }
                }
                case "2" -> System.out.println("Rereading current chapter...");
                case "3" -> {
                    if (currentChapterNode.getNeighbors().isEmpty()) {
                        System.out.println("You've reached the last chapter.");
                        return;
                    }
                    currentChapterNode = currentChapterNode.getNeighbors().get(0); // Move to the next chapter
                }
                case "4" -> {
                    // Skip to a chapter
                    int skipChapterIndex = getValidChapterChoice(chapterNodes);
                    currentChapterNode = chapterNodes.get(skipChapterIndex);
                }
                case "5" -> {
                    System.out.println("Returning to main menu...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void readChapter(ChapterNode chapterNode) {
        String chapter = chapterNode.getText();
        StringBuilder currentText = new StringBuilder();

        try {
            for (char c : chapter.toCharArray()) {
                System.out.print(c);
                currentText.append(c);

                // Check if user pressed Enter to skip the rest of the chapter
                if (System.in.available() > 0) {
                    int key = System.in.read();
                    if (key == '\n') {
                        System.out.println(chapter.substring(currentText.length())); // Display the rest of the chapter
                        break;
                    }
                }

                // Animate text
                Thread.sleep(50); 
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("\nAn error occurred while reading the chapter.");
        }

        System.out.println();
    }


    private static void generateReport(String title, int numWords, Map<Character, Integer> charFrequency) {
        System.out.println("Sort character frequency report by:");
        System.out.println("1. Descending");
        System.out.println("2. Ascending");
        System.out.print("Your choice: ");
        String sortOrder = scanner.nextLine().trim();

        boolean descending = sortOrder.equals("1");
        List<Map.Entry<Character, Integer>> sortedFrequency = quickSort(charFrequency, descending);

        System.out.printf("""
        		
                --- Begin report of %s ---
                %d words found in %s.
                """, capitalize(title), numWords, capitalize(title));

        sortedFrequency.forEach(entry ->
                System.out.printf("The '%c' character appears %d times.%n", entry.getKey(), entry.getValue()));

        System.out.printf("--- End report of %s ---%n", capitalize(title));
    }

    private static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
    
    private static List<Map.Entry<Character, Integer>> quickSort(Map<Character, Integer> charFrequency, boolean descending) {
        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(charFrequency.entrySet());
        quickSortHelper(entries, 0, entries.size() - 1, descending);
        return entries;
    }

    private static void quickSortHelper(List<Map.Entry<Character, Integer>> list, int low, int high, boolean descending) {
        if (low < high) {
            int pivot = partition(list, low, high, descending);
            quickSortHelper(list, low, pivot - 1, descending);
            quickSortHelper(list, pivot + 1, high, descending);
        }
    }

    private static int partition(List<Map.Entry<Character, Integer>> list, int low, int high, boolean descending) {
        Map.Entry<Character, Integer> pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condition = descending
                    ? list.get(j).getValue() > pivot.getValue()
                    : list.get(j).getValue() < pivot.getValue();
            if (condition) {
                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    // BFS
    private static void bfsTraverse(List<ChapterNode> chapterNodes) {
        if (chapterNodes.isEmpty()) return;

        Queue<ChapterNode> queue = new LinkedList<>();
        Set<ChapterNode> visited = new HashSet<>();
        queue.add(chapterNodes.get(0));

        while (!queue.isEmpty()) {
            ChapterNode node = queue.poll();
            if (!visited.contains(node)) {
                visited.add(node);
                System.out.println("Visiting chapter: " + node.getText());
                queue.addAll(node.getNeighbors());
            }
        }
    }

    // Test methods for BFS and Quicksort
    private static void runTests() {
        testQuickSort();
        testBFS();
        System.out.println("\nTests completed.\n");
    }

    private static void testQuickSort() {
        System.out.println("\nTesting QuickSort...\n");
        Map<Character, Integer> charFrequency = new HashMap<>();
        charFrequency.put('a', 5);
        charFrequency.put('b', 2);
        charFrequency.put('c', 8);
        charFrequency.put('d', 3);

        List<Map.Entry<Character, Integer>> sorted = quickSort(charFrequency, true);
        sorted.forEach(entry ->
                System.out.printf("Character '%c' appeared %d times.%n", entry.getKey(), entry.getValue()));
    }

    
    private static void testBFS() {
        System.out.println("\nTesting BFS...\n");

        // Creating nodes and linking them
        ChapterNode chapter1 = new ChapterNode("Chapter 1");
        ChapterNode chapter2 = new ChapterNode("Chapter 2");
        ChapterNode chapter3 = new ChapterNode("Chapter 3");
        chapter1.addNeighbor(chapter2);
        chapter2.addNeighbor(chapter3);

        List<ChapterNode> chapters = List.of(chapter1, chapter2, chapter3);
        
        
        // Starting the BFS performance test
        long startTime = System.currentTimeMillis();

        bfsTraverse(chapters);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("BFS traversal took: " + duration + " ms.");
    }
}

class ChapterNode {
    private final String text;
    private ChapterNode previous;
    private final List<ChapterNode> neighbors;

    public ChapterNode(String text) {
        this.text = text;
        this.neighbors = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public void addNeighbor(ChapterNode neighbor) {
        this.neighbors.add(neighbor);
        neighbor.previous = this;
    }

    public ChapterNode getPrevious() {
        return previous;
    }

    public List<ChapterNode> getNeighbors() {
        return neighbors;
    }
}
