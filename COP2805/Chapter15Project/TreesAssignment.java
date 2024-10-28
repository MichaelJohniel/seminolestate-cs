// Name: Michael Maldonado
// Date: 10-28-2024

package chapter15;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreesAssignment {
	public static void main(String[] args) {
		// Creating tissue nodes for each organ
		AnatomyNode heartMuscle = new AnatomyNode("Heart Muscle Tissue", 2000000000L);
		AnatomyNode heartConnective = new AnatomyNode("Heart Connective Tissue", 1000000000L);
		AnatomyNode bloodEpithelial = new AnatomyNode("Blood Vessel Epithelial Tissue", 5000000000000L);
		AnatomyNode bloodConnective = new AnatomyNode("Blood Vessel Connective Tissue", 5000000000000L);
		AnatomyNode brainNervous = new AnatomyNode("Brain Nervous Tissue", 86000000000L);
		AnatomyNode brainConnective = new AnatomyNode("Brain Connective Tissue", 1000000000L);
		AnatomyNode lungEpithelial = new AnatomyNode("Lung Epithelial Tissue", 200000000L);
		AnatomyNode lungConnective = new AnatomyNode("Lung Connective Tissue", 200000000L);

		// Creating organ nodes with tissues as children
		AnatomyNode heart = new AnatomyNode("Heart", 0, heartMuscle, heartConnective);
		AnatomyNode bloodVessels = new AnatomyNode("Blood Vessels", 0, bloodEpithelial, bloodConnective);
		AnatomyNode brain = new AnatomyNode("Brain", 0, brainNervous, brainConnective);
		AnatomyNode lungs = new AnatomyNode("Lungs", 0, lungEpithelial, lungConnective);

		// Creating system nodes with organs as children
		AnatomyNode circulatorySystem = new AnatomyNode("Circulatory System", 0, heart, bloodVessels);
		AnatomyNode nervousSystem = new AnatomyNode("Nervous System", 0, brain);
		AnatomyNode respiratorySystem = new AnatomyNode("Respiratory System", 0, lungs);

		// Root node "Human Body" with all systems as children
		AnatomyNode humanBody = new AnatomyNode("Human Body", 0, circulatorySystem, nervousSystem, respiratorySystem);

		// Printing the tree structure
		printTreeIterative(humanBody);

		// Calculating and printing the total cell count
		out.println("\nTotal number of cells in the human body is approximately: " + totalCellCount(humanBody));

	}
	
	// Tree Structure 
	public static void printTreeIterative(AnatomyNode root) {
	    Map<AnatomyNode, Integer> nodeLevelMap = new HashMap<>();
	    List<AnatomyNode> stack = new ArrayList<>();

	    stack.add(root);
	    nodeLevelMap.put(root, 0);

	    while (!stack.isEmpty()) {
	        AnatomyNode currentNode = stack.remove(stack.size() - 1);
	        int currentNodeLevel = nodeLevelMap.get(currentNode);
	        
	        // Indentation for hierarchy levels using characters
	        for (int i = 0; i < currentNodeLevel; i++) {
	        	out.print("|   "); // Spaces for last child
	        }
	        
	        if (currentNodeLevel == 0) {
	            out.println(currentNode.name);
	        } else {
	        out.println("└─ " + currentNode.name);
	        }

	        // Push children to the stack
	        for (int i = currentNode.children.size() - 1; i >= 0; i--) {
	            stack.add(currentNode.children.get(i));
	            nodeLevelMap.put(currentNode.children.get(i), currentNodeLevel + 1);
	        }
	    }
	}
	
	private static long totalCellCount(AnatomyNode root) {
		List<AnatomyNode> stack = new ArrayList<>();
		stack.add(root);
		
		long totalCells = 0;
		
		while (!stack.isEmpty()) {
			AnatomyNode currentNode = stack.remove(stack.size() - 1);
			totalCells += currentNode.cells;
			
			for (AnatomyNode child : currentNode.children) {
				stack.add(child);
			}
		}
		return totalCells;
	}
}

class AnatomyNode {
	String name;
	long cells;
	List<AnatomyNode> children = new ArrayList<>();
	
	public AnatomyNode(String name, long cells, AnatomyNode... children) {
		this.name = name;
		this.cells = cells;
		for (AnatomyNode child : children)
			this.children.add(child);
	}
}
