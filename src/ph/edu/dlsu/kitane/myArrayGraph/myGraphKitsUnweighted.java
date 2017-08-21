/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.myArrayGraph;

import acm.program.*;
import java.util.*;

/**
 *
 * @author ChristophJohnEric
 */
public class myGraphKitsUnweighted<E> {

    private ArrayList<E> vertex;
    private ArrayList<ArrayList<E>> adjacency;
    public myGraphKitsUnweighted(int size) {
        vertex = new ArrayList<>(size);
        adjacency = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            adjacency.add(new ArrayList<>());
        }
    }

    public void addVertex(E item) {
        if (!containsVertex(item)) {
            vertex.add(item);
            System.out.println("Graph has " + item + "? " + vertex.contains(item));
        } else {
            System.out.println("Vertex " + item + " exists.");
            throw new RuntimeException("Vertex exists!");
        }
    }

    public void addEdge(E item1, E item2) {
        if (!containsEdge(item1, item2)) {
            adjacency.get(vertex.indexOf(item1)).add(item2);
            adjacency.get(vertex.indexOf(item2)).add(item1);
            System.out.println("Edge added: (" + item1 + "," + item2 + ") unweighted.");
        } else {
            System.out.println("Edge: (" + item1 + "," + item2 + ") exists.");
        }
    }

   

    public E getVertex(int index) {
        if (containsVertex(vertex.get(index))) {
            return vertex.get(index);
        }
        return null;
    }

    public String getAdjacency(E item) {
        if (adjacency.get(vertex.indexOf(item)) != null) {
            String result = "" + adjacency.get(vertex.indexOf(item));
            return result;
        }
        return null;
    }

    public void removeVertex(E item) {
        if (vertex.contains(item)) {
            for(E element: adjacency.get(vertex.indexOf(item))){
                if(containsEdge(item,element)){
                    removeEdge(item,element);
                }
            }
            vertex.remove(item);
        } else {
            System.out.println("Vertex " + item + " doesnt exist or has already been removed");
        }

    }

    public void removeEdge(E item1, E item2) {
        System.out.println("Before: " + vertex.get(vertex.indexOf(item1)) + ":" + adjacency.get(vertex.indexOf(item1)));
        System.out.println("Before: " + vertex.get(vertex.indexOf(item2)) + ":" + adjacency.get(vertex.indexOf(item2)));
        if (adjacency.get(vertex.indexOf(item1)).contains(item2)) {
            adjacency.get(vertex.indexOf(item1)).remove(item2);
            adjacency.get(vertex.indexOf(item2)).remove(item1);
        }
        System.out.println("After: " + vertex.get(vertex.indexOf(item1)) + ":" + adjacency.get(vertex.indexOf(item1)));
        System.out.println("After: " + vertex.get(vertex.indexOf(item2)) + ":" + adjacency.get(vertex.indexOf(item2)));
    }

    public boolean containsVertex(E item) {
        if (vertex.contains(item)) {
            return true;
        }
        return false;
    }

    public boolean containsEdge(E item1, E item2) {
        if (adjacency.get(vertex.indexOf(item1)).contains(item2)) {
            return true;
        }
        return false;
    }

    public int size() {
        return vertex.size();
    }

}
