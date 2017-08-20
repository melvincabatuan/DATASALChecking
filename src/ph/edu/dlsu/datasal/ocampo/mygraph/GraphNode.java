package ph.edu.dlsu.datasal.ocampo.mygraph;
import java.util.*;

/**
 * A node in the graph.
 * @param <E> The data the node carries.
 */
public class GraphNode<E> {
    private E data;
    private LinkedList<GraphNode<E>> adjacentNodes;
    private double distance;
    private GraphNode<E> predecessor;
    
    // Constructor
    /**
     * Create an empty graph node. That is, it does not have any data.
     */
    public GraphNode() {
        data = null;
        adjacentNodes = new LinkedList();
        distance = 0;
        predecessor = null;
    }
    
    /**
     * Create a node with data.
     * @param newData The data to be placed in the node.
     */
    public GraphNode(E newData) {
        data = newData;
        adjacentNodes = new LinkedList();
        distance = 0;
        predecessor = null;
    }
    
    // Get and set
    /**
     * Returns the data carried by the node.
     * @return The data carried by rhe node.
     */
    public E getData() {
        return data;
    }
    
    /**
     * Returns the nodes adjacent to this node as a linked list.
     * @return A linked list containing the nodes adjacent to this node.
     */
    public LinkedList<GraphNode<E>> getAdjacentNodes() {
        return adjacentNodes;
    }
    
    /**
     * Returns the distance value of this node. This is usually used in 
     * shortest path and minimum spanning tree algorithms.
     * @return The distance value of the node
     */
    public double getDistance() {
        return distance;
    }
    
    /**
     * Returns the predecessor of the node. This is usually used in 
     * shortest path and minimum spanning tree algorithms.
     * @return The predecessor of the node.
     */
    public GraphNode<E> getPredecessor() {
        return predecessor;
    }
    
    /**
     * Sets the new data of the node.
     * @param newData The new data of the node.
     */
    public void setData(E newData) {
        data = newData;
    }
    
    /**
     * Sets the new distance value of the node.This is usually used in 
     * shortest path and minimum spanning tree algorithms.
     * @param newDistance The new distance value of the node.
     */
    public void setDistance(double newDistance) {
        distance = newDistance;
    }
    
    /**
     * Sets the new predecessor of the node. This is usually used in 
     * shortest path and minimum spanning tree algorithms.
     * @param newPredecessor The new predecessor of the node.
     */
    public void setPredecessor(GraphNode<E> newPredecessor) {
        predecessor = newPredecessor;
    }
    
    // Condition checkers
    /**
     * Checks if this node is connected to the specified node.
     * @param anotherNode The node to be checked.
     * @return True if the nodes are connected.
     */
    public boolean isConnectedTo(GraphNode<E> anotherNode) {
        return adjacentNodes.contains(anotherNode);
    }
    
    // Connection and disconnection
    /**
     * Connects this node to the specified node. If the nodes are already connected
     * it is unchanged.
     * @param anotherNode The node to connect to this node.
     */
    public void connectTo(GraphNode<E> anotherNode) {
        if(!isConnectedTo(anotherNode)) adjacentNodes.add(anotherNode);
    }
    
    /**
     * Disconnects this node from the specified node. More formally, it removes
     * the specified node from the adjacent nodes. If the node to disconnect from
     * is not available, the adjacent nodes are unchanged.
     * @param anotherNode The node to disconnect from.
     */
    public void disconnectFrom(GraphNode<E> anotherNode) {
        if(isConnectedTo(anotherNode)) adjacentNodes.remove(anotherNode);
    }
}
