// Name: Michael Maldonado
// Date: 11-03-24

package chapter15;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecursionAssignment {
	
	public static void main(String[] args) {
        // Create sample tree nodes
        DepartmentNode node1 = new DepartmentNode("Bonneville Power Administration", 21, 150000);
        DepartmentNode node2 = new DepartmentNode("Southeastern Power Administration", 11, 190000);
        DepartmentNode node3 = new DepartmentNode("Southwestern Power Administration", 15, 110000);
        DepartmentNode node4 = new DepartmentNode("Western Area Power Administration", 14, 120000);
        
        DepartmentNode node5 = new DepartmentNode("Assistant Secretary for Electricity", 12, 191000, node1, node2, node3, node4);
        DepartmentNode node6 = new DepartmentNode("Assistant Secretary for Fossil Energy", 10, 100000);
        DepartmentNode node7 = new DepartmentNode("Assistant Secretary for Nuclear Energy", 10, 100000);
        DepartmentNode node8 = new DepartmentNode("Assistant Secretary for Energy Efficiency and Renewable Energy", 10, 100000);
        
        DepartmentNode node9 = new DepartmentNode("Office of the Undersecretary of Energy", 10, 110000, node5, node6, node7, node8);
        DepartmentNode node10 = new DepartmentNode("Office of Science", 15, 110000);
        DepartmentNode node11 = new DepartmentNode("Office of Artificial Intelligence and Technology", 14, 125000);
        
        DepartmentNode node12 = new DepartmentNode("Office of the Undersecretary for Science", 12, 191000, node9, node10, node11);
        DepartmentNode node13 = new DepartmentNode("Chief of Staff", 1, 50000);
        DepartmentNode node14 = new DepartmentNode("Ombudsman", 1, 50000);
        
        DepartmentNode root = new DepartmentNode("Office of the Secretary", 12, 191000, node12, node9, node13, node14);
        
        // Calculate Average # of employees
        double totalEmployees = root.getTotalEmployees();
        double totalNodes = root.getTotalNodes();
        double averageEmployees = totalEmployees / totalNodes;
        
        printTreeIterative(root);
        System.out.printf("\nAverage number of employees: %.2f\n", averageEmployees);
    }
	
	private static void printTreeIterative (DepartmentNode root) {
		Map<DepartmentNode, Integer> nodeLevelMap = new HashMap<>();
		List<DepartmentNode> stack = new ArrayList<>();
		
		stack.add(root);
		nodeLevelMap.put(root, 0);
		
		while (!stack.isEmpty()) {
			DepartmentNode currentNode = stack.remove(stack.size() - 1);
			
			int currentNodeLevel = nodeLevelMap.get(currentNode);
			for (int i = 0; i < currentNodeLevel; i++)
				out.print("    ");
			out.println(currentNode.name);
			
			for (DepartmentNode child : currentNode.children) {
				stack.add(child);
				nodeLevelMap.put(child, currentNodeLevel + 1);
			}
		}
	}
}

class DepartmentNode {
	String name;
	int employees;
	int budget;
	List<DepartmentNode> children = new ArrayList<>();
	
	public DepartmentNode(String name, int employees, int budget, DepartmentNode... children) {
		this.name = name;
		this.employees = employees;
		this.budget = budget;
		for (DepartmentNode child : children)
			this.children.add(child);
	}
	
    public int getTotalEmployees() {
        int total = employees;
        for (DepartmentNode child : children) {
            total += child.getTotalEmployees();
        }
        return total;
    }

    public int getTotalNodes() {
        int total = 1;  
        for (DepartmentNode child : children) {
            total += child.getTotalNodes();
        }
        return total;
    }
}
