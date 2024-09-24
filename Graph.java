import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class represents an undirected graph.
 * 
 * @author Ivory Huo
 *
 */
public class Graph implements GraphADT {
	
	private List<GraphNode> nodes; // List to store nodes of the graph
    private Map<GraphNode, List<GraphEdge>> adjacencyList; // Adjacency list to store edges 

	/**
	 * Constructor creates an empty graph with n nodes and no edges
	 * The names of the nodes are 0, 1, . . . , n−1
	 * 
	 * @param n
	 */
	public Graph(int n) {
		
		// Initialize with empty adjacency lists
        nodes = new ArrayList<>();
        adjacencyList = new HashMap<>();

        // Create n nodes with names 0 to n-1
        for (int i = 0; i < n; i++) {
            GraphNode node = new GraphNode(i);
            nodes.add(node);
            // Initialize adjacency list for each node with an empty list
            adjacencyList.put(node, new ArrayList<>());
        }
        
	}
	
	/**
	 * Method that adds to the graph an edge connecting nodes u and v
	 * 
	 * @param nodeu: first endpoint
	 * @param nodev: second endpoint
	 * @param type: type of edge
	 * @param label: label of edge 
	 */
	@Override
	public void insertEdge(GraphNode nodeu, GraphNode nodev, int type, String label) throws GraphException {
		
		// Check if nodes exist
        if (!nodes.contains(nodeu) || !nodes.contains(nodev)) {
        	// Throws a GraphException if either node does not exist 
            throw new GraphException("One or both of the nodes do not exist.");
        }
        // Check if an edge already exists between the given nodes
        if (getEdge(nodeu, nodev) != null) {
        	// Throws a GraphException if there is already an edge connecting the given nodes
            throw new GraphException("Edge already exists between the given nodes."); 
        }
        // Create edge and add to adjacency list of both nodes
        GraphEdge edge = new GraphEdge(nodeu, nodev, type, label);
        adjacencyList.get(nodeu).add(edge);
        adjacencyList.get(nodev).add(edge);
	}

	/**
	 * Method that returns the node with the specified name
	 * 
	 * @param u: name of node to be retrieved 
	 * @return the node with the specified name
	 * @throws GraphException if no node exists with the specified name
	 */
	@Override
	public GraphNode getNode(int u) throws GraphException {
		
		// Check if node with specified name exists
        if (u < 0 || u >= nodes.size()) {
        	// If no node with this name exists, the method should throw a GraphException
            throw new GraphException("Node does not exist.");
        }
        return nodes.get(u); // Return node with appropriate name
        
	}

	/**
	 * Method that returns a Java Iterator storing all the edges incident on node u
	 * 
	 * @param u : node for which incident edges are to be retrieved
	 * @return An iterator over the incident edges of node u, or null if u has no incident edges
	 * @throws GraphException if u is not a node of the graph
	 */
	@Override
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {
		
		// Check if u is a node of the graph
	    if (!adjacencyList.containsKey(u)) {
	        // If u is not a node of the graph, throw a GraphException
	        throw new GraphException("Node is not part of the graph.");
	    }
	 
	    // Retrieve the list of incident edges associated with node u
	    List<GraphEdge> incidentEdges = adjacencyList.get(u);
	    
	    // Check if there are incident edges for node u
	    if (incidentEdges.isEmpty()) {
	        // If u has no incident edges, return null
	        return null;
	    } else {
	        // If u has incident edges, return an iterator over the incident edges collection
	        return incidentEdges.iterator(); // Call .iterator()
	    }
	    
	}

	/**
	 * Method that returns the edge connecting nodes u and v
	 * 
	 * @param u : first node
	 * @param v : second node
	 * @return the edge connecting nodes u and v, or null if no such edge exists
	 * @throws GraphException if there is no edge between u and v or if u or v are not nodes of the graph.
	 */
	@Override
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
		
		// Check if u and v are nodes of the graph (check if they exist)
	    if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
	        throw new GraphException("One or both of the nodes are not part of the graph.");
	    }
	    
	    // Find edge between u and v (check if they have edges)
	    List<GraphEdge> edgesFromU = adjacencyList.get(u); // Retrieve edges incident on node u
	    List<GraphEdge> edgesFromV = adjacencyList.get(v); // Retrieve edges incident on node v
	    
	    GraphEdge foundEdge = null; // Initialize variable to store the found edge
	    int minEdgesCount = Integer.MAX_VALUE; // Initialize to maximum value to track minimum edge count
	    
	    // Iterate over edges from u to find the appropriate edge
	    for (GraphEdge edge : edgesFromU) {
	        // Check if the edge connects u and v
	        if ((edge.firstEndpoint() == u && edge.secondEndpoint() == v) ||
	                (edge.firstEndpoint() == v && edge.secondEndpoint() == u)) {
	            // Check if the current edge has the least number of incident edges seen so far
	            int edgeCount = Math.min(edgesFromU.size(), edgesFromV.size()); // Calculate incident edges count
	            if (edgeCount < minEdgesCount) { // Compare with the minimum edge count
	                minEdgesCount = edgeCount; // Update minimum edge count
	                foundEdge = edge; // Update foundEdge to current edge
	            }
	        }
	    }
	    
	    // If no edge between u and v is found, return null
	    return foundEdge; // Return the found edge or null if no such edge exists
	    
	}

	/**
	 * Method that returns true if nodes u and v are adjacent; returns false otherwise
	 * 
	 * @param u One of the nodes
	 * @param v The other node
	 * @return True if u and v are adjacent, false otherwise
	 * @throws GraphException If u or v are not nodes of the graph
	 */
	@Override
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
		
		// Check if u and v are nodes of the graph
	    if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
	        // Throw GraphException if one or both of the nodes are not part of the graph
	        throw new GraphException("One or both of the nodes are not part of the graph.");
	    }
	    
	    // Check if there exists an edge between u and v
	    for (GraphEdge edge : adjacencyList.get(u)) {
	        // Iterate over edges incident on node u
	        if ((edge.firstEndpoint() == u && edge.secondEndpoint() == v) ||
	                (edge.firstEndpoint() == v && edge.secondEndpoint() == u)) {
	            // If an edge connecting u and v is found, return true
	            return true;
	        }
	    }
	    
	    // If no edge connecting u and v is found, return false
	    return false;
	}

}
