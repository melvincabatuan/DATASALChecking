package ph.edu.dlsu.datasal.ocampo.mygraph;
import java.util.*;
import ph.edu.dlsu.datasal.ocampo.mystack.*;
import ph.edu.dlsu.datasal.ocampo.myqueue.*;

/**
 * A weighted undirected graph. <br> <br>
 * This implementation does not tolerate duplicated data. That is, it does not 
 * allow adding nodes with the same data as existing ones. Nodes are marked by
 * integer indices beginning with 0. As a flaw, it might not exceed the integral
 * limit of about 2 billion (<tt>Integer.MAX_VALUE</tt>).
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
 * @param <E> The data the nodes of this graph contain.
 */
public class MyWeightedUGraph<E> {
    private LinkedList<GraphNode<E>> graphNodes;
    private LinkedList<GraphEdge<E>> graphEdges;
    private LinkedList<E> graphNodeData;
    
    public MyWeightedUGraph() {
        graphNodes = new LinkedList();
        graphEdges = new LinkedList();
        graphNodeData = new LinkedList();
    }
    
    // Condition check
    public boolean hasNode(E data) {
        return graphNodeData.contains(data);
    }
    
    public boolean hasEdge(int nodeIndex1, int nodeIndex2) {
        GraphNode<E> node1 = graphNodes.get(nodeIndex1);
        GraphNode<E> node2 = graphNodes.get(nodeIndex2);
        return (node1.isConnectedTo(node2) && node2.isConnectedTo(node1));
    }
    
    // Get and set
    public GraphNode<E> getNode(int nodeIndex) {
        return graphNodes.get(nodeIndex);
    }
    
    public E getNodeData(int nodeIndex) {
        return graphNodes.get(nodeIndex).getData();
    }
    
    public LinkedList<GraphNode<E>> getAdjacentNodes(int nodeIndex) {
        return graphNodes.get(nodeIndex).getAdjacentNodes();
    }
    
    public int getNodeIndex(E data) {
        return findIndex(data);
    }
    
    public GraphEdge<E> getEdge(int startNodeIndex, int endNodeIndex) {
        return findEdgeContaining(getNode(startNodeIndex), getNode(endNodeIndex));
    }
    
    public double getEdgeWeight(int startNodeIndex, int endNodeIndex) {
        return getEdge(startNodeIndex, endNodeIndex).getWeight();
    }
    
    public void setEdgeWeight(int startNodeIndex, int endNodeIndex, double edgeWeight) {
        GraphEdge<E> edge = getEdge(startNodeIndex, endNodeIndex);
        edge.setWeight(edgeWeight);
        graphEdges.set(graphEdges.indexOf(edge), edge);
    }
    
    // Add / Remove
    public void addNode(E data) {
        if(!hasNode(data)) {
            graphNodeData.add(data);
            graphNodes.add(new GraphNode(data));
        }
    }
    
    public void removeNode(int nodeIndex) {
        for(int i=0; i<graphNodes.get(nodeIndex).getAdjacentNodes().size(); i++) {
            removeEdge(graphNodes.get(nodeIndex), graphNodes.get(nodeIndex).getAdjacentNodes().get(i));
        }
        graphNodes.remove(nodeIndex);
    }
    
    public void addEdge(int nodeIndex1, int nodeIndex2, double weight) {
        if(!hasEdge(nodeIndex1, nodeIndex2)) {
            GraphNode<E> node1 = graphNodes.get(nodeIndex1);
            GraphNode<E> node2 = graphNodes.get(nodeIndex2);
            node1.connectTo(node2);
            node2.connectTo(node1);
            graphNodes.set(nodeIndex2, node2);
            graphNodes.set(nodeIndex1, node1);
            graphEdges.add(new GraphEdge(node1, node2, weight));
            graphEdges.add(new GraphEdge(node2, node1, weight));
        }
    }
    
    public void removeEdge(int nodeIndex1, int nodeIndex2) {
        if(hasEdge(nodeIndex1, nodeIndex2)) {
            GraphEdge<E> toDelete1 = findEdgeContaining(getNode(nodeIndex1), getNode(nodeIndex2));
            GraphEdge<E> toDelete2 = findEdgeContaining(getNode(nodeIndex1), getNode(nodeIndex2));
            if(toDelete1!=null) graphEdges.remove(toDelete1);
            if(toDelete2!=null) graphEdges.remove(toDelete2);
            GraphNode<E> node1 = graphNodes.get(nodeIndex1);
            GraphNode<E> node2 = graphNodes.get(nodeIndex2);
            node1.disconnectFrom(node2);
            node2.disconnectFrom(node1);
            graphNodes.set(nodeIndex1, node1);
            graphNodes.set(nodeIndex2, node2);
        }
    }
    
    public LinkedList<GraphNode<E>> depthFirstSearch(int startNodeIndex) {
        LinkedList<GraphNode<E>> result = new LinkedList();
        MyDynamicStack<GraphNode<E>> stack = new MyDynamicStack();
        GraphNode<E> current;
        
        stack.push(graphNodes.get(startNodeIndex));
        while(!stack.isEmpty()) {
            current = stack.top();
            if(!result.contains(current)) result.add(current);
            boolean hasAdjacentUnvisited = false;
            for(GraphNode<E> v : current.getAdjacentNodes()) {
                if(!result.contains(v)) {
                    stack.push(v);
                    hasAdjacentUnvisited = true;
                    break;
                }
            }
            if(!hasAdjacentUnvisited) stack.pop();
        }
        return result;
    }
    
    public LinkedList<GraphNode<E>> breadthFirstSearch(int startNodeIndex)  {
        LinkedList<GraphNode<E>> result = new LinkedList();
        MyDynamicQueue<GraphNode<E>> queue = new MyDynamicQueue();
        queue.enQueue(graphNodes.get(startNodeIndex));
        result.add(graphNodes.get(startNodeIndex));
        while(!queue.isEmpty()) {
            GraphNode<E> current = queue.deQueue();
            for(GraphNode<E> v : current.getAdjacentNodes()) {
                if(!result.contains(v)) {
                    result.add(v);
                    queue.enQueue(v);
                }
            }
        }
        return result;
    }
    
    private GraphEdge<E> findEdgeContaining(GraphNode<E> startNode, GraphNode<E> endNode) {
        GraphEdge<E> resEdge = null;
        for(GraphEdge<E> e : graphEdges) {
            if(e.getStartNode()==startNode && e.getEndNode()==endNode) {
                resEdge = e; break;
            }
        }
        return resEdge;
    }
    
    private int findIndex(E data) {
        int i=0;
        for(i=0; i<graphNodes.size(); i++) {
            if(graphNodes.get(i).getData()==data) break;
        }
        return (i<graphNodes.size())?i:(-1);
    }
    
    private void removeEdge(GraphNode<E> node1, GraphNode<E> node2) {
        GraphEdge<E> toDelete1 = findEdgeContaining(node1, node2);
        GraphEdge<E> toDelete2 = findEdgeContaining(node2, node1);
        if(toDelete1!=null) graphEdges.remove(toDelete1);
        if(toDelete2!=null) graphEdges.remove(toDelete2);
        node1.disconnectFrom(node2);
        node2.disconnectFrom(node1);
    }
}
