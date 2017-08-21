/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.myArrayGraph;

/**
 *
 * @author ChristophJohnEric
 */
public interface ArrayGraphADT<E> {

    public void addVertex(E item);
    //precondition: ArrayGraph must exist and must have space.
    //postcondition: Adds a vertex into the graph, the index of
    //the vertex is the order in which it was placed.
    //If the ArrayGraph doesn't exist throw NullPointerException.
    //If the ArrayGraph is full, throw RuntimeException.

    public void addEdge(E item1, E item2);
    //precondition: ArrayGraph must exist with item1 and item2 existing as well.
    //postcondition: Adds an edge that connects item1 and item2 together,
    //unweighted.
    //If the ArrayGraph doesn't exist throw NullPointerException.
    //If the ArrayGraph has neither item1 nor item 2 nor both, throw
    //NullPointerException.

    public void addEdge(E item1, E item2, int weight);
    //precondition: ArrayGraph must exist with item1 and item2 existing as well.
    //postcondition: Adds an edge that connects item1 and item2 together,
    //unweighted.
    //If the ArrayGraph doesn't exist throw NullPointerException.
    //If the ArrayGraph has neither item1 nor item 2 nor both, throw
    //NullPointerException.

    public E getVertex(int index);
    //precondition: ArrayGraph Must exist.
    //postcondition: returns the vertex value at the specific index.
    //If there is no vertex at that index, then throw NullPointerException.

    public String getAdjacency(E item);
    //precondition: ArrayGraph Must exist.
    //postcondition: returns the adjacency list of the vertex.
    //If the vertex does not exist, return null

    public void removeVertex(E item);
    //precondition: ArrayGraph Must exist. item must exist.
    //postcondition: removes the vertex from the graph, and deleting any edges
    //that it has with other vertices.
    //If the ArrayGraph doesn't exist, throw NullPointerException.
    //If the vertex doesn't exist in the graph, throw NullpointerException.

    public void removeEdge(E item1, E item2);
    //precondition: item1 and item2 must exist.
    //postcondition: removes the edge that connects item1 and item2.
    //If neither item1 nor item2 nor both, throw NullPointerException.

    public boolean containsVertex(E item);
    //precondition: none.
    //postcondition: checks whether the graph has the certain item as vertex.
    //if not, then it will return false.

    public boolean containsEdge(E item1, E item2);
    //precondition: none.
    //postcondition: checks whether the graph has the certain edge between
    //item1 and item2. If there is none then return false.

    public int getEdge(E item1, E item2);
    //precondition: must have edge between item1 and item2.
    //postcondition: gets the weight between item1 and item2, if edge is
    //unweighted, the value will be zero.
    //If there is no edge then return null.

}
