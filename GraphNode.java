/**
 * This class represent a node of the graph. 
 * 
 * @author Ivory Huo
 *
 */
public class GraphNode {

    //	Two Variables: Name and Mark
	private int name; // Name of the node
    private boolean marked; // Mark indicating whether the node is marked or not

	/**
	 * Constructor for the class: Creates a node with the given name
	 * 
	 * @param name : of a node is a node is an integer value between 0 and n − 1, where n is the number of nodes in the graph. 
	 * A node can be marked with a value that is either true or false using the following method.
	 */
	public GraphNode(int name) {
		this.name = name;
        this.marked = false; // By default, the node is not marked
	}

	/**
	 * Setter method that marks the node with the specified value
	 * 
	 * @param mark
	 */
	public void mark(boolean mark) {
		this.marked = mark; 
	}
	
	/**
	 * Getter method that returns the value with which the node has been marked
	 * 
	 * @return marked node 
	 */
	public boolean isMarked() {
		return this.marked;
	}
	
	/**
	 * Getter method that returns the name of the node
	 * 
	 * @return node name
	 */
	public int getName() {
		return this.name;
	}
	
}
