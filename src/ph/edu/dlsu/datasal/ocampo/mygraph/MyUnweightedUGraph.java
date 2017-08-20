package ph.edu.dlsu.datasal.ocampo.mygraph;
import java.util.*;
import ph.edu.dlsu.datasal.ocampo.mystack.*;
import ph.edu.dlsu.datasal.ocampo.myqueue.*;

/**
 * Unweighted undirected graph. <br> <br>
 * This implementation does not tolerate duplicated data. That is, it does not 
 * allow adding nodes with the same data as existing ones. Nodes are marked by
 * integer indices beginning with 0. As a flaw, it might not exceed the integral
 * limit of about 2 billion (Integer.MAX_VALUE).
 * <br> <br>
 * In this implementation,a graph node is a separate class that contains the 
 * following
 * <ul>
 * <li>Data contained in the node</li>
 * <li>A list of adjacent nodes.</li>
 * <li>Current distance and predecessor typically used in minimum spanning tree 
 * and shortest path algorithms</li>
 * </ul>
 * <br>
 * A graph edge is also a separate class containing the following.
 * <ul>
 * <li>Start and end nodes. An edge cannot be implemented as two sided at once.</li>
 * <li>Edge weight. The weight of an unweighted edge is 0</li>
 * </ul>
 * @param <E> The data carried in its nodes.
 */
public class MyUnweightedUGraph<E> {
    private LinkedList<GraphNode<E>> graphNodes;
    private LinkedList<GraphEdge<E>> graphEdges;
    private LinkedList<E> graphNodeData;
    
    // Constructors
    /**
     * Create an unweighted undirected graph.
     */
    public MyUnweightedUGraph() {
        graphNodes = new LinkedList();
        graphEdges = new LinkedList();
        graphNodeData = new LinkedList();
    }
    
    // Get and set
    // Nodes
    /**
     * Returns the graph node of this index.
     * @param nodeIndex The marking index of the node.
     * @return The graph node representation with this index.
     */
    public GraphNode<E> getNode(int nodeIndex) {
        return graphNodes.get(nodeIndex);
    }
    
    /**
     * Returns the data contained in the node with the specified index.
     * @param nodeIndex The marking index of the node.
     * @return The data contained in the node with the specified index.
     */
    public E getNodeData(int nodeIndex) {
        return getNode(nodeIndex).getData();
    }
    
    public int getNodeIndex(E data) {
        return graphNodeData.indexOf(data);
    }
    /**
     * Returns the adjacent nodes of the node with the specified index.
     * @param nodeIndex The marking index of the node.
     * @return The adjacent nodes of this node as a linked list of nodes.
     */
    public LinkedList<GraphNode<E>> getAdjacentNodes(int nodeIndex) {
        return getNode(nodeIndex).getAdjacentNodes();
    }
    
    public void setNodeData(int nodeIndex, E newData) {
        GraphNode<E> node = graphNodes.get(nodeIndex);
        node.setData(newData);
        graphNodes.set(nodeIndex, node);
    }
    
    // Condition checking
    public boolean containsNode(E data) {
        return graphNodeData.contains(data);
    }
    
    public boolean hasEdge(int nodeIndex1, int nodeIndex2) {
        GraphNode<E> node1 = graphNodes.get(nodeIndex1);
        GraphNode<E> node2 = graphNodes.get(nodeIndex2);
        return (node1.isConnectedTo(node2) && node2.isConnectedTo(node1));
    }
    
    public boolean hasNodes() {
        return !graphNodes.isEmpty();
    }
    
    public boolean hasEdges() {
        return !graphEdges.isEmpty();
    }
    
    // Add / Remove
    // Nodes
    public void addNode(E data) {
        if(!graphNodeData.contains(data)) {
            graphNodes.add(new GraphNode(data));
            graphNodeData.add(data);
        }
    }
    
    public void removeNode(int nodeIndex) {
        {
            // Disconnect all edges
            for(int i=0; i<getNode(nodeIndex).getAdjacentNodes().size(); i++) {
                removeEdge(getNode(nodeIndex), getNode(nodeIndex).getAdjacentNodes().get(i));
            }
            
            graphNodes.remove(nodeIndex);
            graphNodeData.remove(nodeIndex);
        }
    }
    
    
    
    // Add / Remove Edges
    public void addEdge(int nodeIndex1, int nodeIndex2) {
        if(!hasEdge(nodeIndex1, nodeIndex2)) {
            GraphNode<E> node1 = getNode(nodeIndex1);
            GraphNode<E> node2 = getNode(nodeIndex2);
            node1.connectTo(node2);
            node2.connectTo(node1);
            graphNodes.set(nodeIndex1, node1);
            graphNodes.set(nodeIndex2, node2);
            graphEdges.add(new GraphEdge(getNode(nodeIndex1), getNode(nodeIndex2)));
            graphEdges.add(new GraphEdge(getNode(nodeIndex2), getNode(nodeIndex1)));
        }
    }
    
    public void removeEdge(int nodeIndex1, int nodeIndex2) {
        if(hasEdge(nodeIndex1, nodeIndex2)) {
            GraphNode<E> node1 = getNode(nodeIndex1);
            GraphNode<E> node2 = getNode(nodeIndex2);
            node1.disconnectFrom(node2);
            node2.disconnectFrom(node1);
            graphNodes.set(nodeIndex1, node1);
            graphNodes.set(nodeIndex2, node2);
            
            GraphEdge<E> toDelete1 = findEdge(getNode(nodeIndex1), getNode(nodeIndex2));
            GraphEdge<E> toDelete2 = findEdge(getNode(nodeIndex2), getNode(nodeIndex1));
            if(toDelete1!=null) graphEdges.remove(toDelete1);
            if(toDelete2!=null) graphEdges.remove(toDelete2);
        }
    }
    
	// DFS Method
    public LinkedList<GraphNode<E>> depthFirstSearch(int startNodeIndex) {
        LinkedList<GraphNode<E>> result = new LinkedList();
        MyDynamicStack<GraphNode<E>> stack = new MyDynamicStack();
        
        stack.push(graphNodes.get(startNodeIndex));
        while(!stack.isEmpty()) {
            GraphNode<E> current = stack.top();
            if(!result.contains(current)) result.add(current);
            boolean hasAdjacentUnvisited = false;
            for(GraphNode<E> adjNode : current.getAdjacentNodes()) {
                if(!result.contains(adjNode)) {
                    stack.push(adjNode);
                    hasAdjacentUnvisited = true;
                    break;
                }
            }
            if(!hasAdjacentUnvisited) stack.pop();
        }
        return result;
    }
	
    // Private methods
    private GraphEdge<E> findEdge(GraphNode<E> startNode, GraphNode<E> endNode) {
        GraphEdge<E> edgeOut = null;
        
        for(int i=0; i<graphEdges.size(); i++) {
            if(graphEdges.get(i).getStartNode()==startNode && graphEdges.get(i).getEndNode()==endNode) {
                edgeOut = graphEdges.get(i); break;
            }
        }
        return edgeOut;
    }
    
    private GraphNode<E> findNode(E data) {
        GraphNode<E> resNode = null;
        
        for(int i=0; i<graphNodes.size(); i++) {
            if(graphNodes.get(i).getData()==data) {
                resNode = graphNodes.get(i); break;
            }
        }
        
        return resNode;
    }
    
    private void removeEdge(GraphNode<E> startNode, GraphNode<E> endNode) {
        if(graphNodes.contains(startNode) && graphNodes.contains(endNode)) {
            startNode.disconnectFrom(endNode);
            endNode.disconnectFrom(startNode);
            graphNodes.set(graphNodes.indexOf(startNode), startNode);
            graphNodes.set(graphNodes.indexOf(endNode), endNode);
            GraphEdge<E> toDelete1 = findEdge(startNode, endNode);
            GraphEdge<E> toDelete2 = findEdge(endNode, startNode);
            if(toDelete1!=null) graphEdges.remove(toDelete1);
            if(toDelete2!=null) graphEdges.remove(toDelete2);
        }
            
    }
}
