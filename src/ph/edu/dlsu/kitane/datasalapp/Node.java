/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.datasalapp;

import java.util.*;

/**
 *
 * @author ChristophJohnEric
 */
public class Node<E> {
    private E vertex;

    private List<Edge<E>> edges;

    private Node<E> parent;

    private boolean isVisited;

    public Node(E vertex) {
        this.vertex = vertex;
        this.edges = new ArrayList<>();
    }

    public E vertex() {
        return vertex;
    }

    public boolean addEdge(Node<E> node, int weight) {
        if (hasEdge(node)) {
            return false;
        }
        Edge<E> newEdge = new Edge<>(this, node, weight);
        return edges.add(newEdge);
    }

    public boolean removeEdge(Node<E> node) {
        Optional<Edge<E>> optional = findEdge(node);
        if (optional.isPresent()) {
            return edges.remove(optional.get());
        }
        return false;
    }

    public boolean hasEdge(Node<E> node) {
        return findEdge(node).isPresent();
    }

    private Optional<Edge<E>> findEdge(Node<E> node) {
        return edges.stream()
                .filter(edge -> edge.isBetween(this, node))
                .findFirst();
    }

    public List<Edge<E>> edges() {
        return edges;
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public Node<E> parent() {
        return parent;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }
}
