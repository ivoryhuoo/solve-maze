import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.io.*;

/**
 * This class represents the maze.
 * 
 * @author Ivory Huo
 *
 */
public class Maze {
	
	// Instance Variables 
	private Graph graph; 
	private GraphNode exit; 
	private GraphNode entrance;
	private int width;
	private int length;
	private int coins;
	
	/**
	 * Constructor that reads the input file and builds the graph representing the maze
	 * 
	 * @param inputFile : the path to the input file containing maze information
	 * @throws MazeException if the input file does not exist, or the format of the input file is incorrect this method should throw a MazeException
	 */
	public Maze(String inputFile) throws MazeException {
		
	    try {
	        // Reads in the text from the text file
	        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	        
	        reader.readLine(); // Skips the scale (S)

	        // Read width, length, and number of coins
	        width = Integer.parseInt(reader.readLine().trim());
	        length = Integer.parseInt(reader.readLine().trim());
	        coins = Integer.parseInt(reader.readLine().trim());

	        // Initialize the graph and its nodes
	        graph = new Graph(width * length);
	        
	        // Process the file to build the graph
	        for (int row = 0; row < length; row++) {
	            // Gets the different directions of the graph
	            String horizontalLine = reader.readLine();
	            String verticalLine = reader.readLine();

	            for (int col = 0; col < width; col++) {
	                // Calculate 1D index for a node in a 2D grid
	                int nodeIndex = row * width + col;
	                // Gets the node from the graph at the calculated index.
	                GraphNode currentNode = graph.getNode(nodeIndex);

	                // For the horizontal edges
	                if (col < width - 1) {
	                    char horizontalPath = horizontalLine.charAt(col * 2 + 1);
	                    // Checks to see what to mark the edge 
	                    if (horizontalPath != 'w') {
	                        int edgeType = (horizontalPath == 'c' || horizontalPath == 'o') ? 0 : Character.getNumericValue(horizontalPath);
	                        // Inserts the edge and adds in the correct edge type
	                        graph.insertEdge(currentNode, graph.getNode(nodeIndex + 1), edgeType, String.valueOf(horizontalPath));
	                    }
	                }

	                // For the vertical edges
	                if (row < length - 1) {
	                    char verticalPath = verticalLine.charAt(col * 2);
	                    // Checks to see what to mark the edge
	                    if (verticalPath != 'w') {
	                        int edgeType = (verticalPath == 'c' || verticalPath == 'o') ? 0 : Character.getNumericValue(verticalPath);
	                        // Inserts the edge and adds in the correct edge type
	                        graph.insertEdge(currentNode, graph.getNode(nodeIndex + width), edgeType, String.valueOf(verticalPath));
	                    }
	                }

	                // Creates the special nodes such as entrance 's' and exit 'x'
	                char roomChar = horizontalLine.charAt(col * 2);
	                if (roomChar == 's') {
	                    entrance = currentNode;
	                } else if (roomChar == 'x') {
	                    exit = currentNode;
	                }
	            }
	        }
	        // Closes the reader
	        reader.close();
	    }
	    // Throws an exception if there was a problem reading in the input
	    catch (Exception e) {
	        throw new MazeException("Error reading input file: " + e.getMessage());
	    }
	    
	}

	
	/**
	 * Getter method that returns a reference to the Graph object representing the maze
	 * 
	 * @return the graph representing the maze
	 * @throws MazeException thrown if the graph is null
	 */
	public Graph getGraph() throws MazeException {
		
	    // If the graph is null, throw an exception
	    if (graph == null) {
	        throw new MazeException("The graph is null");
	    } else {
	        // Returns a reference to the Graph object
	        return graph;
	    }
	    
	}
	
	/**
	 * Returns a java Iterator containing the nodes of the path from the entrance to the exit of the maze, if such a path exists
	 * 
	 * If the path does not exist, this method returns the value null
	 * 
	 * For example for the maze described below the Iterator returned by this method should contain the nodes 0, 1, 5, 6, and 10
	 * 
	 * @return an Iterator containing the nodes of the path from the entrance to the exit of the maze, or null if no path is found
	 */
	public Iterator<GraphNode> solve() {
		
	    // Creates a stack to find the solution
	    Stack<GraphNode> path = new Stack<>();
	    Set<GraphNode> visited = new HashSet<>();
	    
	    // Pushes the entrance on the stack
	    path.push(entrance);
	    
	    // Calls the private helper method and if it's true, it will return the iterator
	    if (dfs(entrance, path, visited, coins)) {
	        return path.iterator();
	    }
	    
	    // If it's not true, it will return null meaning that there was no path found
	    return null;
	}

	/**
	 * DFS algorithm to find a path from the current node to the exit
	 * 
	 * @param current : the current node being explored
	 * @param path : the stack containing the nodes in the current path
	 * @param visited : the set containing visited nodes
	 * @param coinsLeft : the remaining number of coins
	 * @return true if a path to the exit is found, false otherwise
	 */
	private boolean dfs(GraphNode current, Stack<GraphNode> path, Set<GraphNode> visited, int coinsLeft) {
		
	    visited.add(current); // Add current to visited 

	    // If the current node is the exit 
	    if (current.equals(exit)) {
	        // Returns true if the exit was found
	        return true;
	    }

	    // Use a try catch statement to catch exceptions as well
	    try {
	    	
	        // Iterate over all edges incident to the current node
	        Iterator<GraphEdge> edges = graph.incidentEdges(current);
	        // While there is a next edge 
	        while (edges.hasNext()) {
	            GraphEdge edge = edges.next();
	            // Checks to see if the adjacent node on the other end of the edge
	            GraphNode adjacent = edge.firstEndpoint().equals(current) ? edge.secondEndpoint() : edge.firstEndpoint();
	            // Gets the cost of going through this edge
	            int cost = edge.getType();
	            
	            // Check if the adjacent node is unvisited and can be reached with the remaining coins
	            if (!visited.contains(adjacent) && coinsLeft >= cost) {
	                // Adds the adjacent node to the path
	                path.push(adjacent);
	                if (dfs(adjacent, path, visited, coinsLeft - cost)) {
	                    // Returns true if a path was found
	                    return true;
	                }
	                // Removes the last node from the path
	                path.pop();
	            }
	        }
	    }
	    
	    // Catches any exceptions that were found
	    catch (GraphException e) {
	        return false;
	    }
	    
	    // Remove all the current nodes from the visited set
	    visited.remove(current);
	    // Returns false if there was no path found
	    return false;
	}

}
