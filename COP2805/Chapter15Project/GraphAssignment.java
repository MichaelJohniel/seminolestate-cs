//Name: Michael Maldonado
//Date: 11-04-24

package chapter15;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;

public class GraphAssignment {

    public static void main(String[] args) {
    	// Create Graph and connect nodes
        List<GraphNode> nodes = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            nodes.add(new GraphNode(i+1));
        connect(nodes, 1, 2);
        connect(nodes, 2, 3);
        connect(nodes, 3, 4);
        connect(nodes, 4, 5);
        connect(nodes, 5, 6);
        connect(nodes, 6, 1);   // Cycle 1-6
        connect(nodes, 3, 7);
        connect(nodes, 7, 8);
        connect(nodes, 5, 9);
        connect(nodes, 9, 10);
        connect(nodes, 10, 11);
        connect(nodes, 11, 5);  // Cycle 5-11
        connect(nodes, 8, 12);
        connect(nodes, 12, 13);
        connect(nodes, 13, 10);
        
        // Get Flights
        GraphNode firstCity = nodes.get(0);
        GraphNode secondCity = nodes.get(12);
        firstCity.name = "Orlando (MCO)";
        secondCity.name = "Chicago (ORD)";
        
        getFlights( firstCity, secondCity);
    }
    
    private static void getFlights( GraphNode start, GraphNode destination) {
    	// Setting up display for flight paths
        String display = "Routes from " + start.name + " to " + destination.name + ":";
        StringBuilder banner = new StringBuilder();
        for (int c = 0; c < display.length(); c++)
        	banner.append("-");
        
        out.println(display);
        out.println(banner);
        
        // Gather list of Routes and Print
        List<List<GraphNode>> routes = new ArrayList<>();
        findAllRoutes(start, destination, new ArrayList<>(), new ArrayList<>(), routes);
        sortRoutes(routes);
        printRoutes(routes);
    }
    
    private static void findAllRoutes(GraphNode start, GraphNode destination, 
                                      List<GraphNode> visited, List<GraphNode> path, List<List<GraphNode>> routes) {
        visited.add(start);
        path.add(start);

        if (start.equals(destination)) {
            routes.add(new ArrayList<>(path));
        } else {
            // Continue 
            for (GraphNode neighbor : start.children) {
                if (!visited.contains(neighbor)) {
                    findAllRoutes(neighbor, destination, visited, path, routes);
                }
            }
        }

        // Backtrack
        visited.remove(start);
        path.remove(path.size() - 1);
    }

    private static void connect(List<GraphNode> nodes, int nodeId1, int nodeId2) {
        nodes.get(nodeId1 - 1).children.add(nodes.get(nodeId2 - 1));
        nodes.get(nodeId2 - 1).children.add(nodes.get(nodeId1 - 1));
    }
    
    private static void sortRoutes(List<List<GraphNode>> routes) {
        routes.sort((route1, route2) -> Integer.compare(route1.size(), route2.size()));
    }
    
    private static void printRoutes(List<List<GraphNode>> routes) {
    	for (int i = 0; i < routes.size(); i++) {
        	
        	// Format Route Number
        	String routeNum = (i < 9) ? "0" + (i+1) : Integer.toString(i+1);
        	
        	// Build Route String
            StringBuilder routeOutput = new StringBuilder("Route " + routeNum + " - ");
            for (GraphNode node : routes.get(i))
                routeOutput.append(node.id).append(" ");
                
            out.println(routeOutput);
        }
    }

}

class GraphNode {
    int id;
    String name = "";
    Boolean isExit = false;
    List<GraphNode> children = new ArrayList<>();

    public GraphNode(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
