package ph.edu.dlsu.datasal.ocampo.mygraph;
import java.util.*;

/**
 * The edge of a graph.
 * @param <E> The data the nodes contain.
 */
public class GraphEdge<E> {
    private GraphNode<E> startNode;
    private GraphNode<E> endNode;
    private double weight;
    
    // Constructor
    /**
     * Create an unweighted edge. The weight value of this edge is 0.
     * @param newStartNode The starting node of the edge.
     * @param newEndNode The ending node of the edge.
     */
    public GraphEdge(GraphNode<E> newStartNode, GraphNode<E> newEndNode) {
        startNode = newStartNode;
        endNode = newEndNode;
        weight = 0;
    }
    
    /**
     * Create a weighted edge.
     * @param newStartNode The starting node of the edge
     * @param newEndNode The ending node of the edge
     * @param newEdgeWeight The edge weight.
     */
    public GraphEdge(GraphNode<E> newStartNode, GraphNode<E> newEndNode, double newEdgeWeight) {
        startNode = newStartNode;
        endNode = newEndNode;
        weight = newEdgeWeight;
    }
    
    // Get and set
    /**
     * Returns the edge's weight. An weighted edge will return 0.
     * @return The edge's weight or 0 if unweighted.
     */
    public double getWeight() {
        return weight;
    }
    
    /**
     * Returns the edge's start node.
     * @return The edge's start node.
     */
    public GraphNode<E> getStartNode() {
        return startNode;
    }
    
    /**
     * Returns the edge's end node.
     * @return The edge's end node.
     */
    public GraphNode<E> getEndNode() {
        return endNode;
    }
    
    /**
     * Sets a new weight for the edge.
     * @param newEdgeWeight The new edge weight.
     */
    public void setWeight(double newEdgeWeight) {
        if(newEdgeWeight>=0) weight = newEdgeWeight;
    }
    
    /**
     * Sets a new start node for the edge.
     * @param newStartNode The new start node for the edge.
     */
    public void setStartNode(GraphNode<E> newStartNode) {
        startNode = newStartNode;
    }
    
    /**
     * Sets a new end node for the edge.
     * @param newEndNode The new end node for the edge.
     */
    public void setEndNode(GraphNode<E> newEndNode) {
        endNode = newEndNode;
    }
}
