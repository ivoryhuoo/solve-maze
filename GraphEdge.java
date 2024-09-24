/**
 * This class represents an edge of the graph.
 * 
 * @author Ivory Huo 
 *
 */
public class GraphEdge {
	
	// Instance variables to be implemented 
	private GraphNode origin; // First endpoint of the edge (origin)
    private GraphNode destination; // Second endpoint of the edge (destination) 
    private int type; // Type of the edge
    private String label; // Label of the edge

	/**
	 * Constructor for the class
	 * 
	 * @param u
	 * @param v
	 * @param type
	 * @param label
	 */
	public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
		// Initialize everything
		this.origin = u;
        this.destination = v;
        this.type = type;
        this.label = label;
	}
	
	/**
	 * Getter method returns the first endpoint of the edge
	 * 
	 * @return first endpoint 
	 */
	public GraphNode firstEndpoint() {
		return this.origin;
	}
	
	/**
	 * Getter method returns the second endpoint of the edge
	 * 
	 * @return second endpoint
	 */
	public GraphNode secondEndpoint() {
		return this.destination;
	}
	
	/**
	 * Getter method returns the type of the edge
	 * 
	 * @return type of edge
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * Setter method sets the type of the edge to the specified value
	 * 
	 * @param type
	 */
	public void setype(int type) {
		this.type = type;
	}
	
	/**
	 * Getter method returns the label of the edge
	 * 
	 * @return edge label
	 */
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * Setter method to set the label of the edge to the specified value
	 * 
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
}
